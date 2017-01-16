package bookshop.dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ebooks database table.
 * 
 */
@Entity
@Table(name="ebooks")
@NamedQuery(name="Ebook.findAll", query="SELECT e FROM Ebook e")
public class Ebook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;

	private String author;

	private String link;

	private String price;

	private String title;

	public Ebook() {
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

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
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