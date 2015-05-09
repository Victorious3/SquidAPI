/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.logging;

public interface IErrorLogger {
	public abstract void info(Throwable t);
	public abstract void warn(Throwable t);
	public abstract void error(Throwable t);
	public abstract void fatal(Throwable t);
}