/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

public class JarZipError extends Error {

	private static final long serialVersionUID = 6082972472753862706L;

	public JarZipError() {
		
	}

	public JarZipError(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public JarZipError(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public JarZipError(String arg0) {
		super(arg0);
	}

	public JarZipError(Throwable arg0) {
		super(arg0);
	}

	@Override
	public String getLocalizedMessage() {
		String s = super.getLocalizedMessage();
		return s != null ? s : "";
	}

	@Override
	public String toString() {
		return "[JarZipError] " + this.getLocalizedMessage();
	}
}