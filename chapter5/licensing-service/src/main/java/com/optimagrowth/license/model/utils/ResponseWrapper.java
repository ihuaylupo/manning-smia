package com.optimagrowth.license.model.utils;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * Response Wrapper
 * @author ihuaylupo
 * @version
 * @since Jun 25, 2018
 */
@JsonInclude(NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper {

    private Object data;
    private Object metadata;
    private List<ErrorMessage> errors;

}
