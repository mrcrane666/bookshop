package bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import bookshop.dao.Book;
import bookshop.dao.Ebook;
import bookshop.dao.controller.EbookManager;

@Service("EbookService")
public class EbookService {

	private EbookManager ebookManager;
	
	@Autowired
	public EbookService(EbookManager ebookManager){
		this.ebookManager = ebookManager;
	}
	
	public Ebook createEbook(String title, String author, String isbn, String price, String link){
		Ebook ebook = new Ebook();
		ebook.setAuthor(author);
		ebook.setTitle(title);
		ebook.setIsbn(isbn);
		ebook.setPrice(price);	
		ebook.setLink(link);
		return ebook;
	}
	public Ebook createEbook(String title, String author, long isbn, int price, String link){
		Ebook ebook = new Ebook();
		ebook.setAuthor(author);
		ebook.setTitle(title);
		ebook.setIsbn(""+isbn);
		ebook.setPrice(""+price);	
		ebook.setLink(link);
		return ebook;
	}
	@CacheEvict(value="ebookList", allEntries=true)
	public void addNewEbookToDB(Ebook ebook) throws Exception{
		System.out.println("Trying to add e-book: "+ebook.getTitle()+ " "+ ebook.getAuthor()+" "+ ebook.getPrice()+ " "+ ebook.getIsbn() );
		ebookManager.createEbook(ebook);
	}
	
	@Cacheable(value="ebookList")
	public List<Ebook> getAllEbooks(){
		List<Ebook> ebookList = ebookManager.Ebook_findAll();
		System.out.println("Number of ebooks: "+ebookList.size());
		return ebookList;
	}
	@CacheEvict(value="ebookList", allEntries=true)
	public void deleteEbook(Ebook ebook){
		try {
			ebookManager.deleteEbook(ebook);
		} catch (Exception e) {
			System.err.println("Ebook with Title: "+ebook.getTitle()+" was not deleted!");
			e.printStackTrace();
		}
	}
	@CacheEvict(value="ebookList", allEntries=true)
	public void deleteEbooks(List<Ebook> ebookList){
		for(Ebook eb: ebookList){
			try {
				ebookManager.deleteEbook(eb);
			} catch (Exception e) {
				System.err.println("Ebook with Title: "+eb.getTitle()+" was not deleted!");
				e.printStackTrace();
			}
		}
		
	}
	
	public Ebook findEbookByIsbn(String isbn) {
		Ebook ebook = ebookManager.findEbookByIsbn(isbn);
		return ebook;
	}

	@CacheEvict(value = "ebookList", allEntries = true)
	public void deleteEbookByIsbn(String isbn) {
		Ebook ebook = this.checkCacheForEbook(isbn);
		if (ebook != null) {
			this.deleteEbook(ebook);
		} else {
			ebook = ebookManager.findEbookByIsbn(isbn);
			deleteEbook(ebook);
		}

	}
	@CacheEvict(value = "ebookList", allEntries = true)
	public void deleteMutipleEbooksByIsbn(String[] isbns){
		for(String s : isbns){
			Ebook ebook = checkCacheForEbook(s);
			if(ebook != null){
				deleteEbook(ebook);
			}
		}
		
	}
	
	public Ebook checkCacheForEbook(String isbn) {
		List<Ebook> ebooks = this.getAllEbooks();
		for (Ebook eb : ebooks) {
			if (eb.getIsbn().equals(isbn)) {
				return eb;
			}
		}
		return null;
	}
	
}
