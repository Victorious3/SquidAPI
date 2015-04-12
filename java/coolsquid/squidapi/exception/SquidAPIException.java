/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

import coolsquid.squidapi.util.StringUtils;



public class SquidAPIException extends RuntimeException {

	private static final long serialVersionUID = 8368735654861876572L;

	public SquidAPIException() {
		
	}

	public SquidAPIException(Object... arg0) {
		super(StringUtils.newString(arg0));
	}

	public SquidAPIException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SquidAPIException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SquidAPIException(String arg0) {
		super(arg0);
	}

	public SquidAPIException(Throwable arg0) {
		super(arg0);
	}
}