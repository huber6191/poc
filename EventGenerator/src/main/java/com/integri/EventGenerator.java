package com.integri;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.integri.service.EventService;
import com.integri.utils.AppUtils;


public class EventGenerator {
	
	/*
	 * optional command line arguments     
	 * 
	 * 	1st - a number that defines number of event messages to generate
	 *  2nd - a number that defines the size of each event message
	 *  3rd - a 'R' or 'Random' option to generate random data 
	 * 
	 */
    public static void main (String... args) {
    			 
		/* the number of events (default as 1 ) */ 
		int count= AppUtils.getCommandLineArgAsNumber(args, 0, 1 ) ;
		
		/* the size of messages (default as 100 ) */
		int size = AppUtils.getCommandLineArgAsNumber(args, 1, 100 ) ;
		
		/* the option to randomize the data in each message */ 
		String random = StringUtils.left(AppUtils.getCommandLineArg(args, 2),1);
		boolean randomFlag = StringUtils.equalsIgnoreCase("R", random) ;
	
	
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("./app-context.xml");
		
		/* generate the messages */ 
		EventService service = (EventService) context.getBean("eventService");
		service.generateEvents(count, size, randomFlag ) ;
		 
		System.exit(0);
		 
    }

 


    
}
