package bookshop.dao;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books")
@NamedQueries({
		
	@NamedQuery(name = "getBookByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
	@NamedQuery(name = "getBookByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b") 
})
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;

	private String author;

	private String description;

	private String genre;

	private String linkToImg;

	private String price;

	private String title;

	public Book() {
	}


	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLinkToImg() {
		return this.linkToImg;
	}

	public void setLinkToImg(String linkToImg) {
		this.linkToImg = linkToImg;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}