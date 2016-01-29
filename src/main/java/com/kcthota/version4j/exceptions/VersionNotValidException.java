package com.kcthota.version4j.exceptions;

/**
 * Exception thrown if the String version passed is not parseable
 * @author kc
 *
 */
public class VersionNotValidException extends RuntimeException {

	public VersionNotValidException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -749371523607121478L;

}
