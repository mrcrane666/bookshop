package bookshop.dao.controller;

import com.ibm.jpa.web.JPAManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;

import bookshop.dao.Book;
import bookshop.dao.controller.EMFProvider;

import java.util.List;

import javax.persistence.Query;

@SuppressWarnings("unchecked")
@JPAManager(targetEntity = bookshop.dao.Book.class)
public class BookManager {

	private EntityManagerFactory emf;

	public BookManager() {
		emf = EMFProvider.getEMF();
	}

	public BookManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	@Action(Action.ACTION_TYPE.CREATE)
	public void createBook(Book book) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
	}

	@Action(Action.ACTION_TYPE.DELETE)
	public void deleteBook(Book book) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			book = em.merge(book);
			em.remove(book);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
	}

	@Action(Action.ACTION_TYPE.UPDATE)
	public void updateBook(Book book) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			book = em.merge(book);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
	}

	@Action(Action.ACTION_TYPE.FIND)
	public Book findBookByIsbn(String isbn) {
		Book book = null;
		EntityManager em = getEntityManager();
		try {
			book = (Book) em.find(Book.class, isbn);
		} finally {
			em.close();
		}
		return book;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public Book getNewBook() {
	
		Book book = new Book();
	
		return book;
	}

	@NamedQueryTarget("getBookByTitle")
	public List<Book> getBookByTitle(String title) {
		EntityManager em = getEntityManager();
		List<Book> results = null;
		try {
			Query query = em.createNamedQuery("getBookByTitle");
			query.setParameter("title", title);
			results = (List<Book>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	@NamedQueryTarget("getBookByAuthor")
	public List<Book> getBookByAuthor(String author) {
		EntityManager em = getEntityManager();
		List<Book> results = null;
		try {
			Query query = em.createNamedQuery("getBookByAuthor");
			query.setParameter("author", author);
			results = (List<Book>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	@NamedQueryTarget("Book.findAll")
	public List<Book> Book_findAll() {
		EntityManager em = getEntityManager();
		List<Book> results = null;
		try {
			Query query = em.createNamedQuery("Book.findAll");
			results = (List<Book>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

}