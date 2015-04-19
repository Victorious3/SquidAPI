/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

public class MisuseException extends SquidAPIException {
	private static final long serialVersionUID = 3315582821617896061L;

	public MisuseException() {
		
	}

	public MisuseException(Object... arg0) {
		super(arg0);
	}

	public MisuseException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MisuseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MisuseException(String arg0) {
		super(arg0);
	}

	public MisuseException(Throwable arg0) {
		super(arg0);
	}
}