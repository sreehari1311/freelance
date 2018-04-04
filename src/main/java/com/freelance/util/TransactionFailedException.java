package com.freelance.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public TransactionFailedException() {
        super();
    }
    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
    public TransactionFailedException(String message) {
        super(message);
    }
    public TransactionFailedException(Throwable cause) {
        super(cause);
    }
}
