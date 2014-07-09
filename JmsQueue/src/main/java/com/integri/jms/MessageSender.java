package com.integri.jms;

import java.util.HashMap;
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
 
    public void send(final Object obj) {
        jmsTemplate.convertAndSend(obj);
    }
 
    public static void main (String... args) {
    	
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("./spring/MessageSender.xml");
		MessageSender sender = (MessageSender) context.getBean("messageSender");
		 
		MyMessage mess =  buildTestMessage();
		
		sender.send(mess);
		
		System.out.println("Message sent" + mess.getText());
		System.exit(1);
		
    }

    
	/**
	 * 
	 * @return Map
	 */
	private static MyMessage  buildTestMessage() {
		MyMessage mess = new MyMessage();
		mess.setText("Testing a custom object");
		return mess;
	}
    
}
