package uz.pdp.librarymanagementsystem.user;

import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAo {


    public static List<User> getAllUsers() {

       List<User> userList = new ArrayList<>();

        try ( Connection connection = DbConnection.getConnection()){

            PreparedStatement ps  = connection.prepareStatement("select * from users");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                User user = new User();
               Long id = Long.valueOf(resultSet.getString("id"));
                String username = resultSet.getString("username");
                String pasword= resultSet.getString("password");
                String pasword1= resultSet.getString("lastname");
                user.setId(id);
                user.setUsername(username);
                user.setPassword(pasword);
                user.setLastname(pasword1);

           userList.add(user);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return userList;
    }
    public static int save_user(User e){
        int status=0;
        try{
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into users(username, password,lastname) values (?,?,?)");
            ps.setString(1,e.getUsername());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getLastname());
            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static int deleteuser(int id) {
        int status=0;
        Connection connection1=DbConnection.getConnection();
        try {
            PreparedStatement ps=connection1.prepareStatement("delete from users where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            connection1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;
    }

}
