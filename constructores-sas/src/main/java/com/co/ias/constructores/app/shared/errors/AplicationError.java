package com.co.ias.constructores.app.shared.errors;

public class AplicationError {
	
	  private final String errorCode;
	    private final String message;

	    public AplicationError(String errorCode, String message) {
	        this.errorCode = errorCode;
	        this.message = message;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }

	    public String getMessage() {
	        return message;
	    }

}
