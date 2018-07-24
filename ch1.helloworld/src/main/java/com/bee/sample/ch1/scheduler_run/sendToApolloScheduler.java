package com.bee.sample.ch1.scheduler_run;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.fusesource.stomp.jms.StompJmsDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bee.sample.ch1.config.Ch1Scheduler;
import com.bee.sample.ch1.utils.Ch1ApolloUtil;
import com.bee.sample.ch1.utils.ConfDataUtil;

@Component
@Order(value = 1)
public class sendToApolloScheduler implements CommandLineRunner{
	
	@Autowired
	Ch1ApolloUtil ch1ApolloUtil;
	
	@Autowired
	ConfDataUtil confDataUtil;
	
	@Autowired
	Ch1Scheduler  ch1Scheduler;
	
	public void sendToApollo() {
		
		Session session = null;
		Connection connection = null;
		
		String destination = confDataUtil.getManagerTopic();
		connection = ch1ApolloUtil.getconnection();
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest=new StompJmsDestination(destination);
			MessageConsumer messConsumer=session.createConsumer(dest);
			System.out.println("Waiting for messages from dashboard");
			while(true) {
				System.out.println("开始等待接收");
				Message msg=messConsumer.receive();
				System.out.println("接收成功");
				
				if(msg instanceof TextMessage) {
					String sendTopic=((TextMessage)msg).getText();
//					ch1Scheduler.zuZhuang2(bkclass, topic);
				}else {
					System.out.println("msg not instanceof TextMessage");
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
				}
			}
		}
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		sendToApollo();
		
		ch1Scheduler.start();
		
	}

}
