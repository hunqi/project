package com.raysun.email.demo.entity;

public class EmailBean {
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String title;
	private String content;
	private String username;
	private String password;
	private String host;
	private String protocol;
	private Boolean authFlag;
	private String htmlTemplatePath;
	private String attachments;

	public String getFrom() {
		return from;
	}

	public void setFrom(String fromAccount) {
		this.from = fromAccount;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String toAccout) {
		this.to = toAccout;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Boolean getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(Boolean authFlag) {
		this.authFlag = authFlag;
	}

	public String getHtmlTemplatePath() {
		return htmlTemplatePath;
	}

	public void setHtmlTemplatePath(String htmlTemplatePath) {
		this.htmlTemplatePath = htmlTemplatePath;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String ccAccout) {
		this.cc = ccAccout;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bccAccount) {
		this.bcc = bccAccount;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
}
