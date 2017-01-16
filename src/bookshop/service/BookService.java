package bookshop.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import bookshop.dao.Book;
import bookshop.dao.controller.BookManager;

@Service("BookService")
public class BookService {

	private BookManager bookManager;

	@Autowired
	public BookService(BookManager bookManager) {
		this.bookManager = bookManager;
	}

	public Book createBook(String title, String author, String isbn,
			String price, String genre, String description, String linkToImg) {
		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setGenre(genre);
		book.setDescription(description);
		book.setLinkToImg(linkToImg);
		return book;
	}

	public Book createBook(String title, String author, long isbn, int price,
			String genre, String description, String linkToImg) {
		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(title);
		book.setIsbn("" + isbn);
		book.setPrice("" + price);
		book.setGenre(genre);
		book.setDescription(description);
		book.setLinkToImg(linkToImg);
		return book;
	}

	@CacheEvict(value = "bookList", allEntries = true)
	public void addNewBookToDB(Book book) throws Exception {
		System.out.println("Trying to add book: " + book.getTitle() + " "
				+ book.getAuthor() + " " + book.getPrice() + " "
				+ book.getIsbn());
		bookManager.createBook(book);
	}

	@Cacheable(value = "bookList")
	public List<Book> getAllBooks() {
		List<Book> bookList = bookManager.Book_findAll();
		System.out.println("Number of books: " + bookList.size());
		return bookList;
	}

	private void deleteBook(Book book) {
		try {
			bookManager.deleteBook(book);
		} catch (Exception e) {
			System.err.println("Book with Title: " + book.getTitle()
					+ " was not deleted!");
			e.printStackTrace();
		}
	}

	@CacheEvict(value = "bookList", allEntries = true)
	public void deleteBooks(List<Book> bookList) {
		for (Book b : bookList) {
			try {
				bookManager.deleteBook(b);
			} catch (Exception e) {
				System.err.println("Book with Title: " + b.getTitle()
						+ " was not deleted!");
				e.printStackTrace();
			}
		}
	}

	public Book findBookByIsbn(String isbn) {
		Book book = this.checkCacheForBook(isbn);
		return book;

	}

	@CacheEvict(value = "bookList", allEntries = true)
	public void deleteBookByIsbn(String isbn) {
		Book book = this.checkCacheForBook(isbn);
		if (book != null) {
			this.deleteBook(book);
		} else {
			book = bookManager.findBookByIsbn(isbn);
			deleteBook(book);
		}

	}

	@CacheEvict(value = "bookList", allEntries = true)
	public void deleteMutipleBooksByIsbn(String[] isbns) {
		for (String s : isbns) {
			Book book = checkCacheForBook(s);
			if (book != null) {
				deleteBook(book);
			}
		}

	}

	public Book checkCacheForBook(String isbn) {
		List<Book> books = this.getAllBooks();
		for (Book b : books) {
			if (b.getIsbn().equals(isbn)) {
				return b;
			}
		}
		return null;
	}

	public List<Book> findBookbyIsbn(String isbn) {
		List<Book> bookList = new LinkedList<Book>();
		bookList.add(bookManager.findBookByIsbn(isbn));
		return bookList;
	}

	public List<Book> findBookByAuthor(String author) {
		List<Book> bookList = bookManager.Book_findAll();
		List<Book> result = new LinkedList<Book>();
		for (Book b : bookList) {
			if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				result.add(b);
			}
		}
		return result;
	}

	public List<Book> findBookByTitle(String title) {
		List<Book> bookList = bookManager.Book_findAll();
		List<Book> result = new LinkedList<Book>();
		for (Book b : bookList) {
			if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
				result.add(b);
			}
		}
		return result;
	}
	
	public String comparePrices(Book book1, Book book2){
		double result = 0;
		result = Double.parseDouble(book1.getPrice()) - Double.parseDouble(book2.getPrice());
		if(result < 0){
			result = result*-1;
		}
		return ""+result; 
	}

}
