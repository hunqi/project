package com.raysun.email.demo.exception;

public class SendEmailException extends Exception {
	private static final long serialVersionUID = 1L;

	public SendEmailException() {
	}

	public SendEmailException(String message) {
		super(message);
	}

	public SendEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendEmailException(Throwable cause) {
		super(cause);
	}
}
