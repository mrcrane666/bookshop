package bookshop.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.AssertTrue;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bookshop.dao.Book;
import bookshop.dao.controller.BookManager;
import bookshop.service.BookService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "BookShop-portlet.xml" })

public class BookShopTest {

	@Autowired 
	ApplicationContext context;
	@Mock
	private BookManager bookManager;
	BookService bookService;

	@Before
	public void setup(){
		bookService = new BookService(bookManager);
	}
	
	@Test
	public void testCreateBook(){
		Book book = new Book();
		book = bookService.createBook("", "", "", "", "", "", "");
		assertNotNull(book);
		assertNotNull(book.getAuthor());
		assertNotNull(book.getDescription());
		assertNotNull(book.getGenre());
		assertNotNull(book.getIsbn());
		assertNotNull(book.getLinkToImg());
		assertNotNull(book.getPrice());
		assertNotNull(book.getTitle());
	}
	
	@Test 
	public void priceDifferenceShouldBePositiv(){
		Book book1 = new Book();
		Book book2 = new Book();
		book1.setPrice("10");
		book2.setPrice("20");
		Float result = Float.parseFloat(bookService.comparePrices(book1, book2));
		assertFalse(result+" is not positiv",result<0);
	}
}
