package uz.pdp.librarymanagementsystem.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin_view")
public class Admin_view extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter=resp.getWriter();
        List<Admin> adminlist= AdminDao.getAdmins();
        System.out.println(adminlist);
        req.setAttribute("list",adminlist);
        req.getRequestDispatcher("List_admin.jsp").forward(req,resp);
        resp.setContentType("text/html");
        printWriter.close();

    }
}
