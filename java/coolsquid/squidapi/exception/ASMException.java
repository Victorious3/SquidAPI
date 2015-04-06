/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

public class ASMException extends SquidAPIException {
	private static final long serialVersionUID = 7289723037900849526L;

	public ASMException() {
		
	}

	public ASMException(Object... comment) {
		super(comment);
	}
}