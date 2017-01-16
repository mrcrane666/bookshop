package bookshop.dao.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFProvider {
	private static EntityManagerFactory emf;
	private static final String project = "BookShop";

	public EMFProvider() {
	}

	public static synchronized EntityManagerFactory getEMF() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(project);
		}
		return emf;
	}
}