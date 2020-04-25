package com.falmeida.tech.exception;

/**
 * 
 * @author falmeida
 *
 */
public class DataAggregatorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAggregatorException(String errorMessage) {
        super(errorMessage);
    }
	
	public DataAggregatorException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
}
