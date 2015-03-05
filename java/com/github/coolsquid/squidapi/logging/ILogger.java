package com.github.coolsquid.squidapi.logging;


public interface ILogger {

	public abstract void log(String caller, Level level, String message,
			boolean print);

	public abstract void log(String msg);

	public abstract void save();
}