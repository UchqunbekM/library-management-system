package uz.pdp.librarymanagementsystem.issuedReturnedBooks;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delissu")
public class DeleteIssu extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("id");
        int id;
        if (sid!=null) {
            id = Integer.parseInt(sid);

            Boolean delete = IssuedDao.deleteBook(id);

            if (delete) {
                resp.sendRedirect("/books?delet=true");
            }
            return;
        }
        req.setAttribute("to","delete");
        req.getRequestDispatcher("/books").forward(req,resp);
    }
}
