/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.coolsquid.squidapi.exception.SquidAPIException;

public class Logger {
	
	protected String folderName;
	protected String fileName;
	protected boolean savewithtime;
	protected String name = "#Log";
	
	public Logger(String location, String filename) {
		this.folderName = location;
		this.fileName = filename;
	}
	
	public Logger(String location, String filename, String name) {
		this.folderName = location;
		this.fileName = filename;
		this.name = "#" + name;
	}
	
	public Logger(String folderName, String fileName, boolean savewithtime) {
		this.folderName = folderName;
		this.fileName = fileName;
		this.savewithtime = savewithtime;
	}

	public Logger(String folderName, String fileName, boolean savewithtime, SimpleDateFormat timeformat) {
		this.folderName = folderName;
		this.fileName = fileName;
		this.savewithtime = savewithtime;
		this.timeformat = timeformat;
	}
	
	public Logger(String folderName, String fileName, boolean savewithtime, SimpleDateFormat timeformat, String name) {
		this.folderName = folderName;
		this.fileName = fileName;
		this.savewithtime = savewithtime;
		this.timeformat = timeformat;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public boolean shouldSaveWithTime() {
		return this.savewithtime;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public SimpleDateFormat getTimeFormat() {
		return this.timeformat;
	}

	public void setTimeFormat(SimpleDateFormat timeformat) {
		this.timeformat = timeformat;
	}

	protected List<String> loglist = new ArrayList<String>();

	protected SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Adds a message to the log list, and prints it to the console.
	 * @param caller - The name of the caller.
	 * @param level - The level to use. All levels may be found in Level.class.
	 * @param message - The message.
	 * @param print - Decides if the message should be printed to the console.
	 */
	
	public void log(String caller, Level level, String message, boolean print) {
		if (message.length() > 150) {
			throw new LoggingException("The message was too long!");
		}
		
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
	
	public void log(String msg) {
		if (msg.length() > 10500) {
			throw new LoggingException("The message was too long!");
		}
		this.loglist.add(msg);
	}
	
	public void log(StackTraceElement[] stacktrace) {
		for (int a = 0; a < this.loglist.size(); a++) {
			this.loglist.add(stacktrace[a].getClassName());
		}
	}
	
	protected static SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
	protected static SimpleDateFormat fd = new SimpleDateFormat("dd-MM-YYYY");
	
	/**
	 * Saves the log to the specified file at the specified location.
	 * @param location - Must be a subfolder of the working directory. Will be created if not existing.
	 * @param name - The name of the log file.
	 */
	
	public final void save() {
		if (!this.savewithtime) {
			File logFolder = new File("./" + this.folderName);
			File log;
			if (this.fileName.isEmpty()) {
				log = new File("./" + this.folderName + "log.log");
			}
			else if (this.fileName.contains(".")) {
				log = new File("./" + this.folderName, this.fileName);
			}
			else {
				log = new File("./" + this.folderName, this.fileName + ".log");
			}
			PrintWriter w;
			try {
				logFolder.mkdirs();
				if (!this.loglist.isEmpty()) {
					w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(log)));
					w.print(this.name);
					for (int a = 0; a < this.loglist.size(); a++) {
						w.print("\n");w.print(this.loglist.get(a));
					}
					w.close();
					this.loglist.clear();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		String fileTime = ft.format(Calendar.getInstance().getTime());
		String fileDate = fd.format(Calendar.getInstance().getTime());
		
		File logFolder = new File("./" + this.folderName);
		File log;
		if (this.fileName.isEmpty()) {
			log = new File("./" + this.folderName, fileTime + "-" + fileDate + ".log");
		}
		else {
			log = new File("./" + this.folderName, this.fileName + "-" + fileTime + "-" + fileDate + ".log");
		}
		
		PrintWriter w;
		try {
			logFolder.mkdirs();
			if (!this.loglist.isEmpty()) {
				w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(log)));
				w.print("#Log");
				for (int a = 0; a < this.loglist.size(); a++) {
					w.print("\n");w.print(this.loglist.get(a));
				}
				w.close();
				this.loglist.clear();
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
	public String toString() {
		return "Logger [folderName=" + this.folderName + ", fileName=" + this.fileName
				+ ", savewithtime=" + this.savewithtime + ", timeformat=" + this.timeformat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.fileName == null) ? 0 : this.fileName.hashCode());
		result = prime * result
				+ ((this.folderName == null) ? 0 : this.folderName.hashCode());
		result = prime * result + (this.savewithtime ? 1231 : 1237);
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
		if (!(obj instanceof Logger)) {
			return false;
		}
		Logger other = (Logger) obj;
		if (this.fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!this.fileName.equals(other.fileName)) {
			return false;
		}
		if (this.folderName == null) {
			if (other.folderName != null) {
				return false;
			}
		} else if (!this.folderName.equals(other.folderName)) {
			return false;
		}
		if (this.savewithtime != other.savewithtime) {
			return false;
		}
		return true;
	}
}