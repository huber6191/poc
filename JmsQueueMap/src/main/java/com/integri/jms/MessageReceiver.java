package com.integri.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class MessageReceiver implements MessageListener {
 
    public void onMessage(final Message message) {
    	
        if (message instanceof MapMessage) {

        	final MapMessage mapMessage = (MapMessage) message;

        	try {
        		String id = Long.toString( Thread.currentThread().getId()); 
            	System.out.println("Process(" + id + ") Received Message: " + mapMessage.getString("NAME") );
            	
			} catch (JMSException e) {
				
				System.err.println("Exception"+ e.getMessage());
				
			}
        }
    }
    
    
	public static void main(String... args) {
		
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("./spring/MessageReceiver.xml");

	}
 
}