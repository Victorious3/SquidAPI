/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.coolsquid.squidapi.exception.SquidAPIException;
import com.github.coolsquid.squidapi.util.Utils;

public class Logger implements ILogger {
	
	protected final String folder;
	protected final String logfile;
	protected final boolean usetime;

	public Logger(String folder, String logfile) {
		this(folder, logfile, false);
	}
	
	public Logger(String folder, String logfile, boolean usetime) {
		this.folder = folder;
		this.logfile = logfile;
		this.usetime = usetime;
		new Saver(this).start();
	}

	protected SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
	
	public SimpleDateFormat getTimeFormat() {
		return this.timeformat;
	}

	public void setTimeFormat(SimpleDateFormat timeformat) {
		this.timeformat = timeformat;
	}

	protected List<String> loglist = new ArrayList<String>();
	
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
		this.loglist.add("[" + time + "]" + "[" + caller + "]" + "[" + level + "]: " + message);
		if (level.equals(Level.INFO)) {
			if (print) {
				System.out.println("[" + time + "]" + "[" + caller + "]" + "[" + level + "]: " + message);
			}
		}
		else if (level.equals(Level.WARN) || level.equals(Level.ERROR) || level.equals(Level.FATAL)) {
			if (print) {
				System.err.println("[" + time + "]" + "[" + caller + "]" + "[" + level + "]: " + message);
			}
		}
		else {
			throw new LoggingException("Level is wrong");
		}
	}
	
	@Override
	public void log(String msg) {
		this.loglist.add(msg);
	}
	
	public void log(Throwable t) {
		this.loglist.add("");
		this.loglist.add(Utils.newString(t.getClass().getName(), ": ", t.getMessage()));
		for (StackTraceElement s: t.getStackTrace()) {
			this.loglist.add(Utils.newString(s.getClassName(), ":", s.getMethodName(), ":", s.getLineNumber()));
		}
		this.loglist.add("");
		this.save();
	}
	
	protected static final SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
	protected static final SimpleDateFormat fd = new SimpleDateFormat("dd-MM-YYYY");
	
	/**
	 * Saves the log to the specified file at the specified location.
	 * @param location - Must be a subfolder of the working directory. Will be created if not existing.
	 * @param name - The name of the log file.
	 */
	
	@Override
	public void save() {
		try {
			File folder = new File(this.folder);
			folder.mkdirs();
			File log;
			if (this.usetime) {
				log = new File(this.logfile.replace(".log", ft.format(Calendar.getInstance()) + "-" + fd.format(Calendar.getInstance()) +".log"));
			}
			else {
				log = new File(this.logfile);
			}
			if (!this.loglist.isEmpty()) {
				BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log)));
				for (int a = 0; a < this.loglist.size(); a++) {
					w.write(this.loglist.get(a));
					w.newLine();
				}
				w.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class LoggingException extends SquidAPIException {
		
		private static final long serialVersionUID = 528745347;
				
		public LoggingException(String s2) {
			super(s2);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.folder == null) ? 0 : this.folder.hashCode());
		result = prime * result + ((this.logfile == null) ? 0 : this.logfile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Logger other = (Logger) obj;
		if (this.folder == null) {
			if (other.folder != null) {
				return false;
			}
		} else if (!this.folder.equals(other.folder)) {
			return false;
		}
		if (this.logfile == null) {
			if (other.logfile != null) {
				return false;
			}
		} else if (!this.logfile.equals(other.logfile)) {
			return false;
		}
		return true;
	}
	
	public class Saver extends Thread {
		
		private final Logger logger;
		private int size = 0;
		
		public Saver(Logger logger) {
			super();
			this.logger = logger;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (this.logger.loglist.size() != this.size) {
					this.logger.save();
					this.size = this.logger.loglist.size();
				}
			}
		}
	}
}