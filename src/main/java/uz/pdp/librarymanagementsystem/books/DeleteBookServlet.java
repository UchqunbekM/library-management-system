package uz.pdp.librarymanagementsystem.books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/delete")


public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sid = req.getParameter("id");
        int id=0;
        if (sid!=null) {
         id = Integer.parseInt(sid);

          Boolean delete = BookDao.deleteBook(id);

            if (delete) {
                resp.sendRedirect("/books?delet=true");
            }
            return;
        }
        req.setAttribute("yol","delete");
        req.getRequestDispatcher("/books").forward(req,resp);
    }
}
