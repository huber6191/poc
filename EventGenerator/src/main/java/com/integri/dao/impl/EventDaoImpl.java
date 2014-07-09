package com.integri.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.integri.dao.EventDao;
import com.integri.model.BusinessFunction;

@Repository 
public class EventDaoImpl implements EventDao {

	
	 @Autowired
	 private SessionFactory sessionFactory;
	
	
	@Override
	public void saveBusinessFunction(BusinessFunction obj) { 
		this.sessionFactory.getCurrentSession().save(obj); 
		 
		
	}

}
