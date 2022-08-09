package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateuser")
public class Userupdate extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idd=req.getParameter("id");
        int  id= Integer.parseInt(idd);
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String lastname=req.getParameter("lastname");
        UserDAo.update(id,username,password, lastname);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        out.println("<h1 style=\"color: red\"> User has been updated!</h1>");
        req.getRequestDispatcher("Admin_panel.html").include(req, resp);
        out.close();
    }


}
