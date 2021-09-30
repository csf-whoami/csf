package com.csf.base.exception;

import com.csf.base.exception.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final String code;
	private final HttpStatus httpStatus;
}
