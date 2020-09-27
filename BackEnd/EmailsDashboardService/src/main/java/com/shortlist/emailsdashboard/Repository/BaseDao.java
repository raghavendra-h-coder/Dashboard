package com.shortlist.emailsdashboard.Repository;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	
	@Autowired
	protected DatabaseSessionManager sessionManager;

	protected void save(Object obj) {
		Session session = sessionManager.getCurrentSession();
		session.save(obj);
	}

	protected Object merge(Object obj) {
		Session session = sessionManager.getCurrentSession();
		obj = session.merge(obj);
		return obj;
	}

	protected void persist(Object obj) {
		Session session = sessionManager.getCurrentSession();
		session.persist(obj);
	}

	protected void saveOrUpdate(Object obj) {
		Session session = sessionManager.getCurrentSession();
		session.saveOrUpdate(obj);
	}

	protected void update(Object obj) {
		Session session = sessionManager.getCurrentSession();
		session.update(obj);
	}

	protected void delete(Object obj) {
		Session session = sessionManager.getCurrentSession();
		session.delete(obj);
	}

	protected Object findById(Class<?> className, int id) {
		Object object = null;
		Session session = sessionManager.getCurrentSession();
		object = session.get(className, id);
		return object;
	}


}
