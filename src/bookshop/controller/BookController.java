package bookshop.controller;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import bookshop.service.BookService;

@RequestMapping("view")
@Controller
public class BookController {
	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	//test
	@RenderMapping(params = "view=bookList")
	public ModelAndView showBookList(PortletPreferences pref) {
		ModelAndView mav = new ModelAndView("books/book/bookList");
		String height = pref.getValue("admin_height", "500");
		String width = pref.getValue("admin_width", "500");
		String color = pref.getValue("user_color", "black");
		System.out.println(color);
		mav.addObject("user_color", color);
		mav.addObject("momHeight", height);
		mav.addObject("momWidth", width);
		return mav;
	}
	
	@ActionMapping(params="action=getBooks")
	public void bookListToModel(PortletSession session, ActionResponse res){
		session.setAttribute("bookList", bookService.getAllBooks(), PortletSession.APPLICATION_SCOPE);
		res.setRenderParameter("view", "bookList");
	}

	@RenderMapping(params = "view=addBook")
	public ModelAndView showAddBook() {
		ModelAndView mav = new ModelAndView("books/book/addBook");
		return mav;
	}

	@ActionMapping(params = "action=addNewBook")
	public void addNewBook(ActionRequest req, ActionResponse res) {
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		String isbn = req.getParameter("isbn");
		String genre = req.getParameter("genre");
		String description = req.getParameter("description");
		String linkToImg = req.getParameter("linkToImg");
		if (!"".equals(isbn) && !"".equals(title) && !"".equals(price)
				&& !"".equals(author)) {
			try {
				bookService.addNewBookToDB(bookService.createBook(title,
						author, isbn, price, genre, description, linkToImg));
			} catch (Exception e) {
				System.err.println("Book was not added to the database!");
				e.printStackTrace();
			}
		}
		res.setRenderParameter("view", "bookList");
	}

	@ActionMapping(params = "action=deleteBook")
	public void deleteBook(ActionRequest req, ActionResponse res,
			@RequestParam("isbn") String isbn) {
		bookService.deleteBookByIsbn(isbn);
		res.setRenderParameter("view", "bookList");
	}

	@RenderMapping(params = "view=deleteBooks")
	public ModelAndView showDeleteButtons() {
		ModelAndView mav = new ModelAndView("books/book/deleteBooks");
		return mav;
	}

	@ActionMapping(params = "action=deleteBooks")
	public void deleteBooks(ActionRequest req, ActionResponse res) {
		System.out.println("Starting to delete multiple books");
		String[] isbns = req.getParameterValues("booksToDelete[]");
		for (String s : isbns) {
			System.out.println(s);
		}
		bookService.deleteMutipleBooksByIsbn(isbns);
		res.setRenderParameter("view", "bookList");
	}

	@ActionMapping(params = "action=setDetails")
	public void setDetails(ActionRequest req, ActionResponse res,
			@RequestParam("isbn") String isbn, PortletSession session) {
		session.setAttribute("bookDetails", bookService.findBookByIsbn(isbn),
				PortletSession.APPLICATION_SCOPE);
		
		res.setRenderParameter("view", "bookList");
	}

	@ActionMapping(params = "action=searchForBook")
	public void searchForBook(@RequestParam("searchBy") String searchBy,
			@RequestParam("searchParameter") String searchParameter, Model model, ActionResponse res, PortletSession session) {
		if ("isbn".equals(searchBy)) {
			session.setAttribute("bookList", bookService.findBookbyIsbn(searchParameter), PortletSession.APPLICATION_SCOPE);
		} else if ("title".equals(searchBy)) {
			session.setAttribute("bookList",bookService.findBookByTitle(searchParameter), PortletSession.APPLICATION_SCOPE);
		} else if ("author".equals(searchBy)) {
			session.setAttribute("bookList",bookService.findBookByAuthor(searchParameter), PortletSession.APPLICATION_SCOPE);
		}
		res.setRenderParameter("view", "bookList");

	}
	@ActionMapping(params = "action=searchForBookToDel")
	public void searchForBookToDel(@RequestParam("searchBy") String searchBy,
			@RequestParam("searchParameter") String searchParameter, Model model, ActionResponse res, PortletSession session) {
		if ("isbn".equals(searchBy)) {
			session.setAttribute("bookList", bookService.findBookbyIsbn(searchParameter), PortletSession.APPLICATION_SCOPE);
		} else if ("title".equals(searchBy)) {
			session.setAttribute("bookList",bookService.findBookByTitle(searchParameter), PortletSession.APPLICATION_SCOPE);
		} else if ("author".equals(searchBy)) {
			session.setAttribute("bookList",bookService.findBookByAuthor(searchParameter), PortletSession.APPLICATION_SCOPE);
		}
		res.setRenderParameter("view", "deleteBooks");

	}
	
	@ActionMapping(params="action=goToDelBooks")
	public void goToDelBooks(PortletSession session, ActionResponse res){
		
		session.setAttribute("bookList", bookService.getAllBooks(), PortletSession.APPLICATION_SCOPE);
		res.setRenderParameter("view", "deleteBooks");
	}
}
