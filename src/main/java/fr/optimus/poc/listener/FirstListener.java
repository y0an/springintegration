package fr.optimus.poc.listener;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class FirstListener {
	
	@ServiceActivator(inputChannel="inputchan",requiresReply="false")
	public String method1(String message){
		System.out.println("hi from FirstListener :"+message);
		return "FirstListener";
	}

}
