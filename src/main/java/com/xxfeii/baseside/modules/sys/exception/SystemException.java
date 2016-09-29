package com.xxfeii.baseside.modules.sys.exception;

/**
 * 系统异常
 * @author 80002196
 *
 */
public class SystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
	
}