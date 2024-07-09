package com.company.exception;

public class AjaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public AjaxException() {
		super();
	}
	
	public AjaxException(String message) {
		super(message);
	}
	
	public AjaxException(String message, Throwable cause) {
		super(message, cause);
	}
}
