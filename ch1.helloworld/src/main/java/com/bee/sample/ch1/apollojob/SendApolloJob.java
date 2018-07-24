package com.bee.sample.ch1.apollojob;

import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.fusesource.stomp.client.Stomp;
import org.fusesource.stomp.jms.StompJmsDestination;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ch1.ignite.dao.IgBookDao;
import com.bee.sample.ch1.utils.Ch1ApolloUtil;


public class SendApolloJob implements Job{
	
	@Autowired
	IgBookDao  igBookDao;
	
	@Autowired
	Ch1ApolloUtil ch1ApolloUtil;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		
		JobDataMap jb=context.getJobDetail().getJobDataMap();
		String topic = jb.getString("topic");
		String result=bookSendJob();
		sendData(topic, result);
	}
	
	public String bookSendJob() {
		
		List<List<?>> bookList=igBookDao.getBookList();
		String result=JSON.toJSONString(bookList);
		
		return result;
		
	}
	
	public void sendData(String topic,String result) {
		
		Connection connection=ch1ApolloUtil.getconnection();
		Session session= null;
		try {
			session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination ds=new StompJmsDestination(topic);
			
			MessageProducer mp=session.createProducer(ds);
			mp.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage tm=session.createTextMessage(result);
			tm.setStringProperty("topic", topic);
			mp.send(tm);
			
			System.out.println("向客户端发送消息结束---------");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(session!=null) {
				try {
					session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	

}
