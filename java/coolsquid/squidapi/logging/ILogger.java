/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.logging;



public interface ILogger {
	public abstract void info(String msg);
	public abstract void debug(String msg);
	public abstract void warn(String msg);
	public abstract void error(String msg);
	public abstract void fatal(String msg);
	public abstract void info(Object msg);
	public abstract void debug(Object msg);
	public abstract void warn(Object msg);
	public abstract void error(Object msg);
	public abstract void fatal(Object msg);
	public abstract void info(Iterable<?> msg);
	public abstract void debug(Iterable<?> msg);
	public abstract void warn(Iterable<?> msg);
	public abstract void error(Iterable<?> msg);
	public abstract void fatal(Iterable<?> msg);
	public abstract void info(Object[] msg);
	public abstract void debug(Object[] msg);
	public abstract void warn(Object[] msg);
	public abstract void error(Object[] msg);
	public abstract void fatal(Object[] msg);
}