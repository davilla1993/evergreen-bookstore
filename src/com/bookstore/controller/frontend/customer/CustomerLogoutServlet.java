package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entities.Book;


@WebServlet("/logout")
public class CustomerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CustomerLogoutServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("loggedCustomer");
		
		CategoryDao categoryDao = new CategoryDao();
		BookDao bookDao = new BookDao();
		
		List<Book> listNewBooks = bookDao.listNewBooks();
		List<Book> listBestSellingBooks = bookDao.listBestSellingBooks();
		List<Book> listFavoredBooks = bookDao.listMostFavoredBooks();
		
		request.setAttribute("listNewBooks", listNewBooks);
		request.setAttribute("listBestSellingBooks",listBestSellingBooks);
		request.setAttribute("listFavoredBooks", listFavoredBooks);
		  
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
		}

}
