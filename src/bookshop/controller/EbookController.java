package bookshop.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import bookshop.service.EbookService;

@RequestMapping("view")
@Controller
public class EbookController {

	private EbookService ebookService;
	
	@Autowired
	public EbookController(EbookService ebookServ){
		this.ebookService = ebookServ;
	}
	//test
	
	@RenderMapping(params = "view=ebookList")
	public ModelAndView showeBookList(){
		ModelAndView mav = new ModelAndView("books/ebook/ebookList");
		mav.addObject("ebookList", ebookService.getAllEbooks());
		return mav;
	}
	
	
	@RenderMapping(params = "view=addEbook")
	public ModelAndView showAddEbook(){
		System.out.println("add ebook");
		ModelAndView mav = new ModelAndView("books/ebook/addEbook");
		return mav;
	}
	
	@ActionMapping(params="action=addNewEbook")
	public void addNewBook(ActionRequest req, ActionResponse res){
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		String isbn = req.getParameter("isbn");
		String link = req.getParameter("link");
		if(!"".equals(isbn) && !"".equals(title) && !"".equals(price) && !"".equals(author) && !"".equals(link)){
		try {
			ebookService.addNewEbookToDB(ebookService.createEbook(title, author, isbn, price, link));
		} catch (Exception e) {
			System.err.println("Ebook was not added to the database!");
			e.printStackTrace();
		}}
		res.setRenderParameter("view", "ebookList");
	}
	
	@ActionMapping(params="action=deleteEbook")
	public void deleteBook(ActionRequest req, ActionResponse res, @RequestParam("isbn") String isbn){
		ebookService.deleteEbookByIsbn(isbn);
		res.setRenderParameter("view", "ebookList");
	}
	
	@RenderMapping(params="view=deleteEbooks")
	public ModelAndView showEBooksDelPage(){
		ModelAndView mav = new ModelAndView("books/ebook/deleteEbooks");
		mav.addObject("ebookList", ebookService.getAllEbooks());
		return mav;
	}
	
	@ActionMapping(params="action=deleteEbooks")
	public void deleteEbooks(ActionRequest req, ActionResponse res){
		System.out.println("Starting to delete multiple ebooks");
		String[] isbns = req.getParameterValues("ebooksToDelete[]");
		for(String s : isbns){
			System.out.println(s);
		}
		ebookService.deleteMutipleEbooksByIsbn(isbns);
		res.setRenderParameter("view", "ebookList");
		
	}	
}
