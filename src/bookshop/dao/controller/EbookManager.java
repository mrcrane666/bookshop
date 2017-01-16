package bookshop.dao.controller;

import bookshop.dao.Ebook;

import com.ibm.jpa.web.JPAManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;

import java.util.List;

import javax.persistence.Query;

@SuppressWarnings("unchecked")
@JPAManager(targetEntity = bookshop.dao.Ebook.class)
public class EbookManager {

	private EntityManagerFactory emf;

	public EbookManager() {
		emf = EMFProvider.getEMF();
	}

	public EbookManager(EntityManagerFactory emf) {
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
	public void createEbook(Ebook ebook) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ebook);
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
	public void deleteEbook(Ebook ebook) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			ebook = em.merge(ebook);
			em.remove(ebook);
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
	public void updateEbook(Ebook ebook) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			ebook = em.merge(ebook);
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
	public Ebook findEbookByIsbn(String isbn) {
		Ebook ebook = null;
		EntityManager em = getEntityManager();
		try {
			ebook = (Ebook) em.find(Ebook.class, isbn);
		} finally {
			em.close();
		}
		return ebook;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public Ebook getNewEbook() {
	
		Ebook ebook = new Ebook();
	
		return ebook;
	}

	@NamedQueryTarget("Ebook.findAll")
	public List<Ebook> Ebook_findAll() {
		EntityManager em = getEntityManager();
		List<Ebook> results = null;
		try {
			Query query = em.createNamedQuery("Ebook.findAll");
			results = (List<Ebook>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

}