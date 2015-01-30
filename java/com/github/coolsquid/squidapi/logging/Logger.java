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
	
	public Logger(String location, String name) {
		folderName = location;
		fileName = name;
	}
	
	protected List<String> loglist = new ArrayList<String>();
	
	protected SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Adds a message to the log list, and prints it to the console.
	 * @param caller - The name of the caller.
	 * @param level - The level to use. All levels may be found in Level.class.
	 * @param message - The message.
	 * @param print - Decides if the message should be printed to the console.
	 */
	
	public final void log(String caller, Level level, String message, boolean print) {
		if (message.length() > 150) {
			throw new LoggingException("The message was too long!");
		}
		
		String time = t.format(Calendar.getInstance().getTime());
		loglist.add("[" + time + "]" + "[" + caller + "]" + "[" + level + "]: " + message);
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
		if (msg.length() > 150) {
			throw new LoggingException("The message was too long!");
		}
		loglist.add(msg);
	}
	
	protected static SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
	protected static SimpleDateFormat fd = new SimpleDateFormat("dd-MM-YYYY");
	
	/**
	 * Saves the log to the specified file at the specified location.
	 * @param location - Must be a subfolder of the working directory. Will be created if not existing.
	 * @param name - The name of the log file.
	 */
	
	public final void save(boolean timeAndDate) {
		if (!timeAndDate) {
			File logFolder = new File("./" + folderName);
			File log;
			if (fileName.isEmpty()) {
				log = new File("./" + folderName + "log.log");
			}
			else if (fileName.contains(".")) {
				log = new File("./" + folderName, fileName);
			}
			else {
				log = new File("./" + folderName, fileName + ".log");
			}
			PrintWriter w;
			try {
				logFolder.mkdirs();
				if (!loglist.isEmpty()) {
					w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(log)));
					w.print("#Log");
					for (int a = 0; a < loglist.size(); a++) {
						w.print("\n");w.print(loglist.get(a));
					}
					w.close();
					loglist.clear();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		String fileTime = ft.format(Calendar.getInstance().getTime());
		String fileDate = fd.format(Calendar.getInstance().getTime());
		
		File logFolder = new File("./" + folderName);
		File log;
		if (fileName.isEmpty()) {
			log = new File("./" + folderName, fileTime + "-" + fileDate + ".log");
		}
		else {
			log = new File("./" + folderName, fileName + "-" + fileTime + "-" + fileDate + ".log");
		}
		
		PrintWriter w;
		try {
			logFolder.mkdirs();
			if (!loglist.isEmpty()) {
				w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(log)));
				w.print("#Log");
				for (int a = 0; a < loglist.size(); a++) {
					w.print("\n");w.print(loglist.get(a));
				}
				w.close();
				loglist.clear();
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
}