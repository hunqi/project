package com.raysun.email.demo;

import java.util.Random;

import javax.mail.MessagingException;

import com.raysun.email.demo.api.EmailSender;
import com.raysun.email.demo.entity.EmailBean;
import com.raysun.email.demo.exception.SendEmailException;

public class EmailSenderTest2 {

	public static void main(String[] args) throws SendEmailException,
			MessagingException {
			EmailBean bean = new EmailBean();
			bean.setHost("xxxx.xxxxx.xxx");
			bean.setProtocol("smtp");
			bean.setAuthFlag(false);
			bean.setUsername("xxxx@163.com");

			bean.setFrom("xxxxx@163.com");
			bean.setTo("xxxxxx@qq.com");

			bean.setHtmlTemplatePath(EmailSenderTest.class.getClassLoader()
					.getSystemResource("test_mail_template.html").getFile());
			bean.setTitle(String.valueOf(Math.abs(new Random().nextLong())));
			bean.setContent("123456");

			EmailSender.send(bean);
			System.out.println("---Done---");
	}

}
