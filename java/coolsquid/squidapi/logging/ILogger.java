/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.logging;

import org.apache.logging.log4j.Level;


public interface ILogger {

	@Deprecated
	public abstract void log(String msg);
	public abstract void log(Level level, Object... msg);
	public abstract void info(Object... msg);
	public abstract void warn(Object... msg);
	public abstract void error(Object... msg);
	public abstract void fatal(Object... msg);
}