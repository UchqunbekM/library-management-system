package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/updateeuser")
public class updateee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idd=req.getParameter("id");
        int id= Integer.parseInt(idd);
        List<User> list=UserDAo.getAllByIDUsers(id);
        req.setAttribute("list",list);
        req.getRequestDispatcher("updateform.jsp").forward(req,resp);
    }
}
