package bookdetails.controller;

import javax.portlet.PortletSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import bookshop.dao.Book;

@Controller
@RequestMapping("view")
public class BookDeatilsController {

	@RenderMapping()
	public ModelAndView showBookDetails(PortletSession session) {
		ModelAndView mav = new ModelAndView("bookDetails/bookDetails");
		Book book = (Book) session.getAttribute("bookDetails", PortletSession.APPLICATION_SCOPE);
		if (book != null) {
			System.out.println(book.getIsbn());
			mav.addObject("book", book);
		}
		return mav;
	}
}
