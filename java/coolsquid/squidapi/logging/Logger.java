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

import coolsquid.squidapi.exception.SquidAPIException;
import coolsquid.squidapi.util.Utils;

public class Logger implements ILogger {
	
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
			FileUtils.write(this.file, Utils.newString(line, Utils.newLine()), true);
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
	
	@Override
	public void log(String caller, Level level, String message, boolean print) {
		String time = this.timeformat.format(Calendar.getInstance().getTime());
		String line = Utils.newString("[", time, "]", "[", caller, "]", "[", level, "]: ", message);
		this.write(line);
		if (print) {
			System.out.println(line);
		}
	}

	@Override
	public void log(String msg) {
		this.write(msg);
	}
	
	@Override
	public void log(Throwable t) {
		this.write(Utils.repeat('#', 30));
		this.write(Utils.newString(t.getClass().getName(), ": ", t.getMessage()));
		for (StackTraceElement s: t.getStackTrace()) {
			this.write(Utils.newString(s.getClassName(), ":", s.getMethodName(), ":", s.getLineNumber()));
		}
	}
	
	public static class LoggingException extends SquidAPIException {
		
		private static final long serialVersionUID = 528745347;
				
		public LoggingException(String s2) {
			super(s2);
		}
	}
}