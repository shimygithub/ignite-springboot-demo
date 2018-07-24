package com.bee.sample.ch1.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.Data;

@Component
@Data
public class ConfDataUtil {
	
//	@Value("${collector.interval}")
//	private int interver; 
	@Value("${sender.order.topic}")
	private String senderOrderTopic;
	@Value("${sender.receipt.topic}")
	private String senderReceiptTopic;
	
	@Value("${apollo.userName}")
	private String user;
	@Value("${apollo.password}")
	private String password ;
	@Value("${apollo.host}")
	private String host ;
	@Value("${apollo.port}")
	private int port ;
	@Value("${apollo.manager.dataSend.topic}")
	private String managerTopic;
}
