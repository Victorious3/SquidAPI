/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

public class IdException extends SquidAPIException {
	private static final long serialVersionUID = 1L;
	
	public IdException(Object... comment) {
		super(comment);
	}
}