package fr.optimus.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.optimus.poc.listener.FirstListener;
import fr.optimus.poc.listener.SecondListener;

@SpringBootApplication
@RestController
@MessageEndpoint
public class SpringintegrationApplication {

	@Autowired
	public SecondListener bean;
	@Autowired
	public FirstListener first;

	@Autowired
	private ApplicationContext context;
	
	;

	public static void main(String[] args) {
		SpringApplication.run(SpringintegrationApplication.class, args);
	}
	
	@RequestMapping(value="go")
	public String endPoint(){
		System.out.println("endPoint");
		this.test();
		bean.method1("directcall");
		first.method1("directcall");
		
		
		MessageChannel multipleMessagesChannel = context.getBean("inputchan", MessageChannel.class);
				
		Message<String> msg = MessageBuilder.withPayload("payloadd").build();
		multipleMessagesChannel.send(msg);
		
		
		return "hi from endPoint";
	}
	
	
	@ServiceActivator(outputChannel="channelTest")
	public String test(){
		return "SpringintegrationApplication";
	}
}
