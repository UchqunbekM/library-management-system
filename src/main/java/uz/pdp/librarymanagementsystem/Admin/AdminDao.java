package uz.pdp.librarymanagementsystem.Admin;

import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {


    public static int save_admin(Admin e){
        int status=0;
        try{
            Connection con= DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into admins (name,password,email) values (?,?,?)");
            ps.setString(1,e.getName());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getEmail());
            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static List<Admin> getAdmins(){
        List<Admin> list=new ArrayList<Admin>();

        try{
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from admins");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Admin e=new Admin();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
    }
    public static int deleteadmin(int id) {
        int status=0;
        Connection con1=DbConnection.getConnection();
        try {
            PreparedStatement ps=con1.prepareStatement("delete from admins where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            con1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;
    }
}
