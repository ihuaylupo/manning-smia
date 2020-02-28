package com.optimagrowth.license.model.utils;

/**
 * Error message class
 * @author ihuaylupo
 * @version 1.0
 * @since Jun 25, 2018
 */
public class ErrorMessage {
	
    /**
	 * @param message
	 * @param code
	 * @param detail
	 */
	private String message;
	private String code;
    private String detail;

    
	public ErrorMessage(String message, String code, String detail) {
		super();
		this.message = message;
		this.code = code;
		this.detail = detail;
	}

	public ErrorMessage(String message, String detail) {
		super();
		this.message = message;
		this.detail = detail;
	}

    public ErrorMessage(String message) {
        this(message, "", "");
    }

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

    


}
