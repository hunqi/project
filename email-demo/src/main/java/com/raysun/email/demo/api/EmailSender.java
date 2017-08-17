package com.raysun.email.demo.api;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import com.raysun.email.demo.entity.EmailBean;
import com.raysun.email.demo.exception.SendEmailException;
import com.raysun.email.demo.util.FileUtil;

public class EmailSender {

	public static void send(EmailBean bean) throws SendEmailException, MessagingException {
		// 1.创建sesssion
		Session session = Session.getInstance(getProperties(bean));
		session.setDebug(true);	// 开启session的调试模式，可以查看当前邮件发送状态

		// 2.通过session获取Transport对象（发送邮件的核心API）
		Transport ts = session.getTransport();
		
		// 3.通过邮件的 用户名/密码 连接
		ts.connect(bean.getUsername(), bean.getPassword());

		// 4.创建邮件
		MimeMessage mm = new MimeMessage(session);	
		mm.setFrom(new InternetAddress(bean.getFrom())); // 设置发件人

		// 设置收件人
		InternetAddress[] toAccouts = getReceivers(bean.getTo());
		if (toAccouts == null) {
			throw new SendEmailException("Can't find Recipients.");
		}
		mm.setRecipients(Message.RecipientType.TO, toAccouts);
		
		// 设置抄送人
		InternetAddress[] ccAcounts = getReceivers(bean.getCc());
		if (ccAcounts != null && ccAcounts.length > 0) {
			mm.setRecipients(Message.RecipientType.CC, ccAcounts); // 抄送人
		}
		
		// 设置密件抄送人 [The "Bcc" (blind carbon copy) recipients] 
		InternetAddress[] bccAcounts = getReceivers(bean.getBcc());
		if (bccAcounts != null && bccAcounts.length > 0) {
			mm.setRecipients(Message.RecipientType.BCC, bccAcounts); // 抄送人
		}

		// 设置主题
		mm.setSubject(bean.getTitle());
		
		Multipart bodyContent = getBodyContent(bean);
		// 设置附件
		setDocuments(bodyContent, bean.getAttachments());
		
		// 设置邮件内容
		mm.setContent(bodyContent);

		// 5.发送邮件
		ts.sendMessage(mm, mm.getAllRecipients());
	}
	
	private static Multipart getBodyContent(EmailBean email) throws SendEmailException, MessagingException{
		Multipart multipart = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();
		String html = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(email.getHtmlTemplatePath())) {
			if (new File(email.getHtmlTemplatePath()).exists()) {
				try {

					html = FileUtil.read(email.getHtmlTemplatePath());
				} catch (IOException e) {
					throw new SendEmailException("Reading doucument conetent fail.");
				}
			}
		} else {
			html = email.getContent();
		}
		text.setContent(html, "text/html; charset=utf-8");
		multipart.addBodyPart(text);
		
		return multipart;
	}
	
	private static Properties getProperties(EmailBean email){
		Properties prop = new Properties();
		prop.put("mail.host", email.getHost());
		prop.put("mail.transport.protocol", email.getProtocol());
		prop.put("mail.smtp.auth", email.getAuthFlag());
		
		return prop;
	}

	/**
	 * like zhangsan@123.com, lisi@163.com
	 * 
	 * @param receivers
	 * @return
	 * @throws AddressException
	 */
	private static InternetAddress[] getReceivers(String receivers) throws AddressException {
		InternetAddress[] toAccouts = null;
		
		if (StringUtils.isNotBlank(receivers)) {
			String[] tos = receivers.split(",");
			if (tos != null && tos.length > 0) {
				toAccouts = new InternetAddress[tos.length];
				for (int i = 0; i < toAccouts.length; i++) {
					toAccouts[i] = new InternetAddress(tos[i]);
				}
			}
		}
		
		return toAccouts;
	}

	/**
	 * like c:\\temp1.txt,c:\\temp2.doc...
	 * 
	 * @param receivers
	 * @return
	 * @throws SendEmailException
	 * @throws AddressException
	 */
	private static void setDocuments(Multipart multipart, String documentPath) throws SendEmailException {
		if (StringUtils.isNotBlank(documentPath)) {
			String[] paths = documentPath.split(",");
			if (paths != null && paths.length > 0) {
				for (String path : paths) {
					try {
						File file = new File(path);
						if (file.exists()) {
							BodyPart attachment = new MimeBodyPart();
							String fileName = StringUtils.EMPTY;

							DataSource source = new FileDataSource(file.getAbsolutePath());
							attachment.setDataHandler(new DataHandler(source));
							fileName = MimeUtility.encodeWord(file.getName());
							if (StringUtils.isBlank(fileName)) {
								throw new SendEmailException("File name is blank.");
							}
							attachment.setFileName(fileName);
							multipart.addBodyPart(attachment);
						} else {
							System.out.println("can't find path from system. AbsolutePath:" + file.getAbsolutePath());
						}
					} catch (MessagingException e1) {
						throw new SendEmailException(e1.getMessage());
					} catch (UnsupportedEncodingException e) {
						throw new SendEmailException("Unsupported encoding exception.");
					}
				}
			}
		}
	}
}
