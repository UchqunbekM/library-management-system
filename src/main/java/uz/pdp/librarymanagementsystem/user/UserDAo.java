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

            PreparedStatement ps  = connection.prepareStatement("select * from users;");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {


                User user = new User();
               Long id = Long.valueOf(resultSet.getString("id"));
                String username = resultSet.getString("username");
                user.setId(id);
                user.setUsername(username);

           userList.add(user);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return userList;
    }
}
