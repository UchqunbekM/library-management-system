package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.Admin.Admin;
import uz.pdp.librarymanagementsystem.Admin.AdminDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     resp.setContentType("text/html");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        if (name.equals("supadmin") && password.equals("supadmin")) {
            resp.sendRedirect("sup_panel.html");
            return;
        }
        List<Admin> list = AdminDao.getAdmins();
        List<User> userList = UserDAo.getAllUsers();
        for (Admin admin : list) {
            if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
                resp.sendRedirect("/admin");
                return;
            }
        }
                for (User user : userList) {
                if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                    resp.sendRedirect("/user");
                    return;
                }
            }

        resp.setContentType("text/html");
        out.println("<h1 style=\"color: red\"> Login or Password incorrect!</h1>");
        req.getRequestDispatcher("demo.html").include(req, resp);
        out.close();
    }
}