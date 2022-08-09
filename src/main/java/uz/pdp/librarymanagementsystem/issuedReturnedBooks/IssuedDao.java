package uz.pdp.librarymanagementsystem.issuedReturnedBooks;

import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class IssuedDao {

    public static boolean add(IssuedReturnedBooks issuedReturnedBooks) {

        Connection connection = DbConnection.getConnection();
        int status = 0;

        try {
            PreparedStatement ps  = connection.prepareStatement("insert into issued_returned (user_id, book_id, issued) " +
                    "values (?,?,?) ");

            ps.setLong(1,issuedReturnedBooks.getUserId());
            ps.setLong(2,issuedReturnedBooks.getBookId());
            ps.setBoolean(3, issuedReturnedBooks.isIssued());


             status = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status > 0;
    }
    public static List<isBook> getissue(){
        List<isBook> list=new ArrayList<isBook>();

        try{
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("select  ir.issued, ir.date, u.username, b.title from   issued_returned ir join  users u on\n" +
                    "        ir.user_id=u.id join  books b on b.id=ir.book_id");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               isBook e=new isBook();

               e.setUsername(rs.getString(3));
               e.setTitle(rs.getString(4));
               e.setDate(rs.getString(2));
                if(rs.getBoolean(1)){
                    e.setIssued("Issued");
                }else{
                    e.setIssued("Returned");
                }

                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
    }

    public static Boolean deleteBook(int id) {
        PreparedStatement ps = null;
        try (Connection connection = DbConnection.getConnection()){

            ps = connection.prepareStatement("delete from issued_returned where id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
