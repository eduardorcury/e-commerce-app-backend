package com.eduardorib.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	public List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timeStamp, Integer status, String error, String messsage, String path) {
		super(timeStamp, status, error, messsage, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
