package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.books.Book;
import uz.pdp.librarymanagementsystem.books.BookDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class Userjsp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = BookDao.getAllBooks(10,1);
        req.setAttribute("bookList", bookList);

        req.getRequestDispatcher("userbook.jsp").forward(req, resp);
    }
}
