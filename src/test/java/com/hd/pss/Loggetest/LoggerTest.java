package com.hd.pss.Loggetest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerTest {
	//有sl4j-api-1.6.1.jar , log4j-1.2.14.jar , commons-logging.jar
	//编程的时候面向接口编程sl4j
	
	Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	
	@Test
	public void log() throws Exception{
		logger.debug("debug");
		logger.debug("info");
		logger.debug("warn");
		logger.debug("error");
		System.out.println(java.net.URLDecoder.decode("%E6%96%B9%E6%B3%95", "UTF-8"));  //解码
		System.out.println(java.net.URLEncoder.encode("方法", "UTF-8"));  				//编码
	}
	

}
