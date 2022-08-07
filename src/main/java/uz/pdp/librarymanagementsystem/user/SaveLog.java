package uz.pdp.librarymanagementsystem.user;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SaveLog")
public class SaveLog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String name = req.getParameter("username");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        User user=new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setLastname(lastname);
        UserDAo.save_user(user);
        out.print("<div class><h1>Successfully</h1></div>");
        req.getRequestDispatcher("userbook.jsp").include(req, resp);
       // resp.sendRedirect("User_panel.html");
        out.close();



    }
}
