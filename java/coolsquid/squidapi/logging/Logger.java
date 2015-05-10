/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import coolsquid.squidapi.exception.SquidAPIException;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.StringUtils;
import coolsquid.squidapi.util.Utils;

public class Logger implements ILogger {

	private static final String marker = StringUtils.repeat('#', 70);

	protected final File file;
	protected final boolean usetime;

	public Logger(File file) {
		this(file, false);
	}

	public Logger(File file, boolean usetime) {
		this.file = file;
		this.usetime = usetime;
		try {
			File folder = file.getParentFile();
			if (folder != null && !folder.exists()) {
				folder.mkdirs();
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");

	public SimpleDateFormat getTimeFormat() {
		return this.timeformat;
	}

	public void setTimeFormat(SimpleDateFormat timeformat) {
		this.timeformat = timeformat;
	}

	private void write(String line) {
		try {
			FileUtils.write(this.file, StringUtils.newString(line, MiscLib.LINE), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a message to the log list, and prints it to the console.
	 * @param caller - The name of the caller.
	 * @param level - The level to use. All levels may be found in Level.class.
	 * @param message - The message.
	 * @param print - Decides if the message should be printed to the console.
	 */

	public void log(String caller, Level level, String message) {
		String time = this.timeformat.format(Calendar.getInstance().getTime());
		String line = StringUtils.newString("[", time, "]", "[", caller, "]", "[", level, "]: ", message);
		this.write(line);
	}

	public void log(String msg) {
		this.write(msg);
	}

	public void log(Throwable t) {
		this.write(StringUtils.repeat('#', 30));
		this.write(StringUtils.newString(t.getClass().getName(), ": ", t.getMessage()));
		for (StackTraceElement s: t.getStackTrace()) {
			this.write(StringUtils.newString(s.getClassName(), ":", s.getMethodName(), ":", s.getLineNumber()));
		}
	}

	public static class LoggingException extends SquidAPIException {

		private static final long serialVersionUID = 528745347;

		public LoggingException(String s2) {
			super(s2);
		}
	}

	public void log(Level level, Object[] msg) {
		this.log(Utils.getCurrentMod().getName(), level, StringUtils.newString(msg));
	}

	public void log(Level level, String msg) {
		this.log(Utils.getCurrentMod().getName(), level, msg);
	}

	@Override
	public void info(Object[] msg) {
		this.log(Level.INFO, msg);
	}

	@Override
	public void debug(Object[] msg) {
		this.log(Level.DEBUG, msg);
	}

	@Override
	public void warn(Object[] msg) {
		this.log(Level.WARN, msg);
	}

	@Override
	public void error(Object[] msg) {
		this.log(Level.ERROR, msg);
	}

	@Override
	public void fatal(Object[] msg) {
		this.log(Level.FATAL, msg);
	}

	@Override
	public void info(String msg) {
		this.log(Utils.getCurrentMod().getName(), Level.INFO, msg);
	}

	@Override
	public void debug(String msg) {
		this.log(Level.DEBUG, msg);
	}

	@Override
	public void warn(String msg) {
		this.log(Utils.getCurrentMod().getName(), Level.INFO, msg);
	}

	@Override
	public void error(String msg) {
		this.log(Utils.getCurrentMod().getName(), Level.INFO, msg);
	}

	@Override
	public void fatal(String msg) {
		this.log(Utils.getCurrentMod().getName(), Level.INFO, msg);
	}

	@Override
	public void info(Iterable<?> msg) {
		for (Object object: msg) {
			this.info(object.toString());
		}
	}

	@Override
	public void debug(Iterable<?> msg) {
		for (Object object: msg) {
			this.debug(object.toString());
		}
	}

	@Override
	public void warn(Iterable<?> msg) {
		for (Object object: msg) {
			this.warn(object.toString());
		}
	}

	@Override
	public void error(Iterable<?> msg) {
		for (Object object: msg) {
			this.error(object.toString());
		}
	}

	@Override
	public void fatal(Iterable<?> msg) {
		for (Object object: msg) {
			this.fatal(object.toString());
		}
	}

	@Override
	public void info(Object msg) {
		this.info(msg.toString());
	}

	@Override
	public void debug(Object msg) {
		this.debug(msg.toString());
	}

	@Override
	public void warn(Object msg) {
		this.warn(msg.toString());
	}

	@Override
	public void error(Object msg) {
		this.error(msg.toString());
	}

	@Override
	public void fatal(Object msg) {
		this.fatal(msg.toString());
	}

	@Override
	public void info(Throwable t) {
		this.info(marker);
		this.info(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.info(s.toString());
		}
		this.info(marker);
	}

	@Override
	public void debug(Throwable t) {
		this.debug(marker);
		this.debug(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.debug(s.toString());
		}
		this.debug(marker);
	}

	@Override
	public void warn(Throwable t) {
		this.warn(marker);
		this.warn(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.warn(s.toString());
		}
		this.warn(marker);
	}

	@Override
	public void error(Throwable t) {
		this.error(marker);
		this.error(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.error(s.toString());
		}
		this.error(marker);
	}

	@Override
	public void fatal(Throwable t) {
		this.fatal(marker);
		this.fatal(t.toString());
		for (StackTraceElement s: t.getStackTrace()) {
			this.fatal(s.toString());
		}
		this.fatal(marker);
	}
}