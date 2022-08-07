package uz.pdp.librarymanagementsystem.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Savead")
public class Saveadmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String name = req.getParameter("username");
        String fullname = req.getParameter("email");
        String password = req.getParameter("password");
        Admin user=new Admin();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(fullname);
        AdminDao.save_admin(user);
        out.print("<div class><h1>The Admin saved</h1></div>");
        req.getRequestDispatcher("sup_panel.html").include(req, resp);
        out.close();
    }
}
