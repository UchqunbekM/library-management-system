package uz.pdp.librarymanagementsystem.issuedReturnedBooks;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/issu_list")
public class ListIssu  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<isBook> list=IssuedDao.getissue();
        System.out.println(list);
        req.setAttribute("list",list);
        req.getRequestDispatcher("issui_return.jsp").forward(req,resp);
    }
}
