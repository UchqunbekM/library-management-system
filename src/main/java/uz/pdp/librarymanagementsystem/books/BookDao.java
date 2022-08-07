package uz.pdp.librarymanagementsystem.books;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.pdp.librarymanagementsystem.authors.Author;
import uz.pdp.librarymanagementsystem.category.Category;
import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookDao {

    public static List<Book> getAllBooks(int size, int page) {
        try {
            ArrayList<Book> bookList = new ArrayList<>();

//          1. CONNECTION OCHAMIZ
            Connection connection = DbConnection.getConnection();

//        2. GET PREPARED STATEMENT

            String sql = "select b.id,\n" +
                    "       b.title,\n" +
                    "       b.\"imgUrl\",\n" +
                    "       json_agg(\n" +
                    "               json_build_object(\n" +
                    "                       'id', a.id,\n" +
                    "                       'fullName', a.fullname)) as authors,\n" +
                    "    json_build_object('id', c.id, 'name', c.name) as category\n" +
                    "--        c.id                                     as categoryId,\n" +
                    "--        c.name                                   as categoryName\n" +
                    "from books b\n" +
                    "         join books_authors ba on b.id = ba.bookid\n" +
                    "         join authors a on a.id = ba.authorid\n" +
                    "         join categories c on c.id = b.categoryid\n" +
                    "group by b.id, c.id, c.name, b.title\n" +
                    "limit ? offset ? * (? - 1)";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, page);
            preparedStatement.setInt(3, page);


//            3. GET RESULTSET

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long bookId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Array array = resultSet.getArray("authors");
                Object categoryObj = resultSet.getObject("category");
                String imgUrl = resultSet.getString("imgUrl");
                Type listType = new TypeToken<Set<Author>>() {
                }.getType();
                Set<Author> list = new Gson().fromJson(array.toString(), listType);

                Category category = new Gson().fromJson(categoryObj.toString(), Category.class);


                Book book = Book.builder()
                        .id(bookId)
                        .title(title)
                        .authors(list)
                        .category(category)
                        .imgUrl(imgUrl)
                        .build();

                bookList.add(book);


            }
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean addNewBook(Book book) {

        try {

            Connection connection = DbConnection.getConnection();

            String insertBook = "insert into books (title, description, categoryId, \"imgUrl\") VALUES " +
                    "(?, ?, ?, ?)";
            // TODO: 03/08/22 add isbn, year

            PreparedStatement preparedStatement = connection.prepareStatement(insertBook);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setLong(3, book.getCategoryId());
            preparedStatement.setString(4, book.getImgUrl());


            String insertBooksAuthors = "insert into books_authors VALUES ((select currval('book_id_seq')), ?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertBooksAuthors);

            int executeUpdate1 = preparedStatement.executeUpdate();

            int executeUpdate2 = 0;
            for (Long authorId : book.getAuthorsIds()) {
                preparedStatement2.setLong(1, authorId);
                executeUpdate2 = preparedStatement2.executeUpdate();
            }

            return executeUpdate1 == 1 && executeUpdate2 == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static Book getBookInfo(int id) {
        try {

//          1. CONNECTION OCHAMIZ
            Connection connection = DbConnection.getConnection();

//        2. GET PREPARED STATEMENT

            String sql = "select b.id,\n" +
                    "       b.title,\n" +
                    "       b.\"imgUrl\",\n" +
                    "       b.year,\n" +
                    "       b.quantity,\n" +
                    "       b.description,\n" +
                    "       b.isbn,\n" +
                    "       json_agg(\n" +
                    "               json_build_object(\n" +
                    "                       'id', a.id,\n" +
                    "                       'fullName', a.fullname)) as authors,\n" +
                    "    json_build_object('id', c.id, 'name', c.name) as category\n" +
                    "--        c.id                                     as categoryId,\n" +
                    "--        c.name                                   as categoryName\n" +
                    "from books b\n" +
                    "         join books_authors ba on b.id = ba.bookid\n" +
                    "         join authors a on a.id = ba.authorid\n" +
                    "         join categories c on c.id = b.categoryid where b.id = ?" +
                    "group by b.id, c.id, c.name, b.title\n";



            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);



//            3. GET RESULTSET

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long bookId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Array array = resultSet.getArray("authors");
                Object categoryObj = resultSet.getObject("category");
                String imgUrl = resultSet.getString("imgUrl");
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("year");
                int quantity = resultSet.getInt("quantity");
                String description = resultSet.getString("description");

                Type listType = new TypeToken<Set<Author>>() {
                }.getType();
                Set<Author> list = new Gson().fromJson(array.toString(), listType);

                Category category = new Gson().fromJson(categoryObj.toString(), Category.class);


                Book book = Book.builder()
                        .id(bookId)
                        .title(title)
                        .authors(list)
                        .category(category)
                        .imgUrl(imgUrl)
                        .isbn(isbn)
                        .year(year)
                        .quantity(quantity)
                        .description(description)
                        .build();

                return book;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Boolean deleteBook(int id) {
        int excute1 = 0;
        int excute2 = 0;
        PreparedStatement ps = null;
        try (Connection connection = DbConnection.getConnection()){

            ps = connection.prepareStatement("delete from books_authors where bookid = ?");
            ps.setInt(1,id);
            excute1 =  ps.executeUpdate();
            ps.close();

            ps = connection.prepareStatement("delete from books where id = ?");
            ps.setInt(1,id);
            excute2=ps.executeUpdate();
            ps.close();
            return excute1 == 1 && excute2 == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean updateBook(Book book) {

        try {

            Connection connection = DbConnection.getConnection();

            String updateBook = "update books set " +
                    "title=?, " +
                    "description=?, " +
                    "categoryId=?, " +
                    "\"imgUrl\"=?," +
                    "year=?," +
                    "isbn=?," +
                    "quantity=? where id= ?";
            // TODO: 03/08/22 add isbn, year

            PreparedStatement preparedStatement = connection.prepareStatement(updateBook);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setLong(3, book.getCategoryId());
            preparedStatement.setString(4, book.getImgUrl());
            preparedStatement.setInt(5, book.getYear());
            preparedStatement.setString(6, book.getIsbn());
            preparedStatement.setInt(7, book.getQuantity());
            preparedStatement.setLong(8, book.getId());

            int executeUpdate = preparedStatement.executeUpdate();


            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from books_authors where bookid = ?");
            preparedStatement1.setLong(1,book.getId());
            int executeUpdate1 = preparedStatement1.executeUpdate();


            String insertBooksAuthors = "insert into books_authors VALUES (?, ?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertBooksAuthors);


            int executeUpdate2 = 0;
            for (Long authorId : book.getAuthorsIds()) {
                preparedStatement2.setLong(1, book.getId());
                preparedStatement2.setLong(2, authorId);
                executeUpdate2 = preparedStatement2.executeUpdate();
            }

            return executeUpdate == 1 && executeUpdate2 == 1 && executeUpdate1==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Book> getAllBookList() {
        Connection connection = DbConnection.getConnection();
        List<Book> bookList=new ArrayList<>();
        try {

            String sql = "select * from books";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long bookId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Book book=new Book();
                book.setId(bookId);
                book.setTitle(title);

                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
