package com.raysun.email.demo;

import java.util.Random;

import com.raysun.email.demo.api.EmailSender;
import com.raysun.email.demo.entity.EmailBean;

public class EmailSenderTest {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		EmailBean bean = new EmailBean();
		bean.setHost("smtp.163.com");
		bean.setProtocol("smtp");
		bean.setAuthFlag(true);
		bean.setUsername("xxxxxx@163.com");
		
		//此处设置邮箱客户端授权码
		bean.setPassword("xxxxxx");
		
		bean.setFrom("xxxxxx@163.com");
		bean.setTo("xxxxxx@qq.com");
		
		bean.setHtmlTemplatePath(EmailSenderTest.class.getClassLoader().getSystemResource("test_mail_template.html").getFile());
		bean.setTitle(String.valueOf(Math.abs(new Random().nextLong())));
		bean.setContent("123456");
		
		EmailSender.send(bean);
	}
	
}
