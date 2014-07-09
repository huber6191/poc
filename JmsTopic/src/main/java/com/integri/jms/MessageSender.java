package com.integri.jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.integri.model.MyMessage;
 
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
		
//		 for(int i=0; i<2; i++){
			 sender.send(map);
//		 }
		
		System.out.println("Message sent" + map);
		System.exit(1);
		
    }
 
	/**
	 * 
	 * @return Map
	 */
	private static Map<String, List<String>> buildTestMessage() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		List<String> list = new ArrayList<String>();
		list.add("Joe");
		list.add("John");
		list.add("Sara");
		map.put("NAMES", list ); 
		return map;
	}
	
//
//	/**
//	 * 
//	 * @return Map
//	 */
//	private static Map<String, MyMessage> buildTestMessage2() {
//		Map<String, MyMessage> map = new HashMap<String, MyMessage>();
//		 
//		MyMessage msg = new MyMessage();
//		msg.setValue("testing custom object ");
//		
//		map.put("NAMES", msg ); 
//		return map;
//	}	
    
}
