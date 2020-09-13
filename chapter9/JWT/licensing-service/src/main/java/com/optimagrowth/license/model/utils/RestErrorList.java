package com.optimagrowth.license.model.utils;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import static java.util.Arrays.asList;


/**
 * Error list
 * @author ihuaylupo
 * @version
 * @since Jun 25, 2018
 */
@Getter
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

}