package com.integri.jms;

import java.util.HashMap;
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
		 
		Map<String, String> map = buildTestMessage();	
		int i=0;
		
	
		sender.send(map); 
		
		
		System.out.println("Message sent" + map);
		System.exit(1);
		
    }

    
	/**
	 * 
	 * @return Map
	 */
	private static Map<String, String> buildTestMessage() {
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("NAME", "My Message  ");
		 
		return map;
	}
    
}
