package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteuser")
public class Deleteuser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        int  idd= Integer.parseInt(id);
        UserDAo.deleteuser(idd);
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        out.println("<h1 style=\"color: red\"> User has been deleted!</h1>");
      //  resp.sendRedirect("Admin_panel.html");
        req.getRequestDispatcher("Admin_panel.html").include(req, resp);
        out.close();
    }
}
