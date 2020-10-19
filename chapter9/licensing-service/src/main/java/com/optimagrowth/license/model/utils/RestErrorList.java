package com.optimagrowth.license.model.utils;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import static java.util.Arrays.asList;


/**
 * Error list
 * @author ihuaylupo
 * @version
 * @since Jun 25, 2018
 */
public class RestErrorList extends ArrayList<ErrorMessage> {


	/** Generated Serial Version*/
	private static final long serialVersionUID = -721424777198115589L;
	private HttpStatus status;

	public RestErrorList(HttpStatus status, ErrorMessage... errors) {
		this(status.value(), errors);
	}

	public RestErrorList(int status, ErrorMessage... errors) {
		super();
		this.status = HttpStatus.valueOf(status);
		addAll(asList(errors));
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}