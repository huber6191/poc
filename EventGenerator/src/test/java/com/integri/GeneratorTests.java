package com.integri;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.integri.model.BusinessFunction;

 
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class GeneratorTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void testSaveBusinessFunction() throws Exception {
 
		Session session = sessionFactory.getCurrentSession();
		BusinessFunction bf = new BusinessFunction();
		 
		session.save(bf);
		session.flush();
		assertNotNull(bf.getEntryId());
		
	}
	
	

	@Test
	@Transactional 
	public void testSaveAndGet() throws Exception {
		
		Session session = sessionFactory.getCurrentSession();
		
		// create
		BusinessFunction newBF = new BusinessFunction();
		newBF.setEntryDetails("Testing testSaveAndGet");
		session.save(newBF);
		session.flush(); 
		session.clear();
		
		// re-retrieve
		BusinessFunction testBF = (BusinessFunction) session.get(BusinessFunction.class, newBF.getEntryId());
		assertNotNull(testBF.getEntryId());
	 
	}



}
