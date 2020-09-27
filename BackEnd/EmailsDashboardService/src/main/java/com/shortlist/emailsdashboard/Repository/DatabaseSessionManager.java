package com.shortlist.emailsdashboard.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public class DatabaseSessionManager {

	@PersistenceContext(unitName="portalentitymanager")
	EntityManager entityManager;

	public Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}
}
