package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user_view")
public class User_view extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter=resp.getWriter();
        List<User>userList = UserDAo.getAllUsers();
        req.setAttribute("list",userList);
        System.out.println(userList);
        req.getRequestDispatcher("List_user.jsp").forward(req,resp);
        resp.setContentType("text/html");
        printWriter.close();
    }
}
