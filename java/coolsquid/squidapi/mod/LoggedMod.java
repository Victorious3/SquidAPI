/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.mod;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import coolsquid.squidapi.helpers.ExceptionHelper;
import coolsquid.squidapi.logging.ILogger;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.StringUtils;
import cpw.mods.fml.common.ModContainer;

public class LoggedMod extends BaseMod implements ILogger {

	protected final Logger logger;
	protected final ExceptionHelper exceptionHelper;

	public LoggedMod(ModContainer mod) {
		super(mod);
		this.logger = LogManager.getLogger(this.getName());
		this.exceptionHelper = ExceptionHelper.getInstance(this);
	}

	@Override
	public void log(Level level, Object... msg) {
		String a = StringUtils.newString(msg);
		this.logger.log(level, a);
		MiscLib.LOGGER.log(this.getName(), level, a, false);
	}

	public Logger getLogger() {
		return this.logger;
	}

	@Override
	public void info(Object... msg) {
		this.log(Level.INFO, msg);
	}
	
	@Override
	public void warn(Object... msg) {
		this.log(Level.WARN, msg);
	}
	
	@Override
	public void error(Object... msg) {
		this.log(Level.ERROR, msg);
	}
	
	@Override
	public void fatal(Object... msg) {
		this.log(Level.FATAL, msg);
	}

	@Deprecated
	@Override
	public void log(String msg) {
		throw new UnsupportedOperationException();
	}

	public void bigWarning(Object... msg) {
		String a = StringUtils.newString(msg);
		String b = StringUtils.repeat('=', a.length());
		this.log(Level.FATAL, b);
		for (String c: a.split(MiscLib.LINE)) {
			this.log(Level.FATAL, c);
		}
		this.log(Level.FATAL, b);
	}

	public ExceptionHelper getExceptionHelper() {
		return this.exceptionHelper;
	}

	public void error(Throwable t) {
		this.exceptionHelper.log(t);
	}
}