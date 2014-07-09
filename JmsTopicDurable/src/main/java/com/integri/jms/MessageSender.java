package com.integri.jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
 
public class MessageSender {
  
    private final JmsTemplate jmsTemplate;
 
    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
 
    public void send(final Map map) {
        jmsTemplate.convertAndSend(map);
    }
 
    public static void main (String... args) {
    	
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("./spring/MessageSender.xml");
		MessageSender sender = (MessageSender) context.getBean("messageSender");
		 
		Map  map = buildTestMessage();
		int i = 0;
		for( i=1; i<10; i++){
			 sender.send(map);
		 }
		   
		System.out.println("Messages sent count=" + i);
		System.exit(1);
		
    }

 
	/**
	 * 
	 * @return Map
	 */
	private static Map<String, List<String>> buildTestMessage() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		map.put("NAMES", list ); 
		return map;
	}
    
}
