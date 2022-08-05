package uz.pdp.librarymanagementsystem.books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"", "/books"})
public class ViewBookServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String yol = (String) req.getAttribute("yol");
        String pageStr = req.getParameter("page");
        if (yol != null) {
            if (yol.equals("delete")) {

                int page = 1;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                int size = 10;
                List<Book> bookList = BookDao.getAllBooks(size, page);
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("deletebook.jsp").forward(req, resp);
                return;

            } else if (yol.equals("update")) {

                int page = 1;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                int size = 10;
                List<Book> bookList = BookDao.getAllBooks(size, page);
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("updatebook.jsp").forward(req, resp);
                return;

            }
        }


        int page = 1;
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int size = 10;

        List<Book> bookList = BookDao.getAllBooks(size, page);

        req.setAttribute("bookList", bookList);

        Boolean delet = Boolean.valueOf(req.getParameter("delet"));
        if (delet) {
            req.setAttribute("message", "Successfully delete ");
        }

        Boolean update = Boolean.valueOf(req.getParameter("update"));
        if (update) {
            req.setAttribute("message", "Successfully update ");
        }


        Boolean added = Boolean.valueOf(req.getParameter("added"));
        if (added) {
            req.setAttribute("message", "Successfully added!!!");
        }


        req.getRequestDispatcher("book.jsp").forward(req, resp);
    }
}
