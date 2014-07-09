package com.integri.jms;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class MessageReceiver implements MessageListener {
 
	private int count = 0;
	
    public void onMessage(final Message message) {
     
    	
    	System.out.println("on Message fired");
    	
        if (message instanceof MapMessage) {
        	
        	this.count++;
        	
        	final MapMessage inMsg = (MapMessage) message;

        	try {

        		@SuppressWarnings("unchecked")
				List<String> names = (List<String>) inMsg.getObject("NAMES");
      
        		System.out.println("count=" + this.count + " with msg=" + names);
        		
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