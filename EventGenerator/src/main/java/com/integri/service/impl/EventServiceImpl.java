package com.integri.service.impl;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integri.dao.EventDao;
import com.integri.model.BusinessFunction;
import com.integri.service.EventService;
import com.integri.utils.AppUtils;

@Service("eventService")
public class EventServiceImpl implements EventService, ApplicationContextAware {

	@Autowired
	private EventDao dao;

	private static ApplicationContext applicationContext = null;

	@Override
	@Transactional
	public void generateEvents(int count, int size, boolean randomizeData) {

		/* populate a map of data messages */  
		Map<String, String> events = populateMap(count, size, randomizeData);

		/* save to the database */ 
		for (Map.Entry<String, String> entry : events.entrySet()) { 
			BusinessFunction bf = newBusinessFunction(entry.getKey(),entry.getValue());
			dao.saveBusinessFunction(bf);  
		} 

	} 


	  
	/**
	 * populates the map of event data 
	 * @param count - number of events 
	 * @param size - the size of each event message 
	 * @param randomFlag - if true, uses random data for each message
	 *            
	 * @return Map
	 */
	private static Map<String, String> populateMap(int count, int size, boolean randomFlag) {
		
		List<File> files = AppUtils.getSampleFiles(applicationContext, "files");
		File file = AppUtils.getRandomSampleFile(files);

		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 1; i <= count; i++) {

			/* the sample data file */
			file = (randomFlag) ? AppUtils.getRandomSampleFile(files) : file;

			/* read the file data */
			String data = AppUtils.readData(file, size, randomFlag);

			/* store in the map */
			map.put("Event (" + i + ")", data);

			System.out.println("-----------------------------------------");
			System.out.println("len=" + data.length() );
			System.out.println("-----------------------------------------");
			System.out.println(data);
			System.out.println("-----------------------------------------");
		}

		return map;

	}

	/**
	 * returns new BusinessFunction 
	 * @param name - the name of the event
	 * @param details - the details
	 * @return BusinessFunction
	 */
	private static BusinessFunction newBusinessFunction(String name,String details) {
			
		BusinessFunction bf = new BusinessFunction();
		bf.setEntryName(name);
		bf.setEntryDetails(details);
		bf.setInsertedDate(new Date());
		return bf;
	}
	
	  
	/**
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)	
			throws BeansException { 
		this.applicationContext = applicationContext;
	}    

}
