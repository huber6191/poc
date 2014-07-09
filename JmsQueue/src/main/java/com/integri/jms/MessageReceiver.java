package com.integri.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.integri.model.MyMessage;
 
public class MessageReceiver implements MessageListener {
 
    public void onMessage(final Message message) {
    	 
    	System.out.println("onMessage() fired") ;
    	 
        if (message instanceof ObjectMessage) {
        	try { 
        	 
        	Object object = ((ObjectMessage) message).getObject();
        	final MyMessage mymessage= (MyMessage) object;
    	
    		String id = Long.toString( Thread.currentThread().getId()); 
        	System.out.println("Process(" + id + ") Received Message: " + mymessage.getText() );
        	
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