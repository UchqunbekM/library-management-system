package uz.pdp.librarymanagementsystem.books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.librarymanagementsystem.authors.Author;
import uz.pdp.librarymanagementsystem.authors.AuthorDao;
import uz.pdp.librarymanagementsystem.category.Category;
import uz.pdp.librarymanagementsystem.category.CategoryDao;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static uz.pdp.librarymanagementsystem.utils.Util.UPLOAD_DIRECTORY;

@WebServlet("/update")
@MultipartConfig(maxFileSize = 10_000_000)
public class UpdateBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("id");
        int id = 0;
        if (sid != null) {
            id = Integer.parseInt(sid);

            Book book = BookDao.getBookInfo(id);
            List<Author> authors = AuthorDao.getAllAuthors();
            List<Category> categoryList = CategoryDao.getAllCategories();

            req.setAttribute("book", book);
            req.setAttribute("authors", authors);
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("updatepagebook.jsp").forward(req, resp);
        }


        req.setAttribute("yol", "update");
        req.getRequestDispatcher("/books").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id= Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String[] authorsIdsStr = req.getParameterValues("authorsIds");

        Set<Long> authorsIds = getAuthorIdsFromStrArr(authorsIdsStr);
        Long categoryId = Long.valueOf(req.getParameter("categoryId"));
        String isbn = req.getParameter("isbn");
        String url = req.getParameter("url");
        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));
        Part imagePart = req.getPart("image");
        boolean empty = imagePart.getSubmittedFileName().isEmpty();


        Book book = Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .year(year)
                .isbn(isbn)
                .authorsIds(authorsIds)
                .categoryId(categoryId)
                .quantity(quantity)
                .build();

        if (!empty) {
            book.setImgUrl(uploadAndGetImageUrl(imagePart));
        }else {
            book.setImgUrl(url);
        }


        Boolean update = BookDao.updateBook(book);

        if (update) {
            resp.sendRedirect("/books?update=true");
        }
    }
    private Set<Long> getAuthorIdsFromStrArr(String[] authorsIdsStr) {
        Set<Long> authorIds = new HashSet<>();
        for (String authorId : authorsIdsStr) {
            authorIds.add(Long.parseLong(authorId));
        }
        return authorIds;
    }

    private String uploadAndGetImageUrl(Part imagePart) {
        try {

            // TODO: 03/08/22 fix upload image path
            String contextPath = getServletContext().getContextPath();
            File uploadDir = new File(UPLOAD_DIRECTORY, URLDecoder.decode(contextPath, "UTF-8"));
            if (!uploadDir.exists())
                uploadDir.mkdir();
            int index = imagePart.getSubmittedFileName().lastIndexOf('.');
            String extension = imagePart.getSubmittedFileName().substring(index + 1);
            System.out.println("File extension is " + extension);

            String uploadDirPath = uploadDir.getPath();
            long longtime = System.currentTimeMillis();


            String imgPath = "D:\\library-management-system\\src\\main\\webapp\\images"+ "\\" + longtime + "." + extension;
            imagePart.write(imgPath);
            uploadDirPath = uploadDirPath.substring(1);
            uploadDirPath += "\\" + longtime + "." + extension;

            return uploadDirPath;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

