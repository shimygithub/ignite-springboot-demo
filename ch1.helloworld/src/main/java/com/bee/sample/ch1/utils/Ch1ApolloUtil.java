package com.bee.sample.ch1.utils;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ch1ApolloUtil {

	@Autowired
	ConfDataUtil confDataUtil;
	
	private static Connection conn=null;
	
	public Connection getconnection() {
		
		if (conn==null) {
			String user=confDataUtil.getUser();
			String password=confDataUtil.getPassword();
			String host=confDataUtil.getHost();
			int port=confDataUtil.getPort();
			StompJmsConnectionFactory  factory=new StompJmsConnectionFactory();
			factory.setBrokerURI("tcp://" + host + ":" + port);
			try {
				conn=factory.createConnection(user,password);
				conn.start();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return conn;
		
	}
	
}
