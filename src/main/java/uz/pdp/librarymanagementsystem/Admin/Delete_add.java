package uz.pdp.librarymanagementsystem.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteadmin")
public class Delete_add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        PrintWriter out=resp.getWriter();
        int  idd= Integer.parseInt(id);
       AdminDao.deleteadmin(idd);
        resp.setContentType("text/html");
        out.println("<h1 style=\"color: red\"> Admin has been deleted!</h1>");
        req.getRequestDispatcher("sup_panel.html").include(req, resp);

out.close();
    }
}
