package com.github.coolsquid.squidapi.logging;

public class LoggingLevel {
	
	private String name;
	private int level;
	
	public LoggingLevel(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "LoggingLevel [name=" + name + ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof LoggingLevel)) {
			return false;
		}
		LoggingLevel other = (LoggingLevel) obj;
		if (level != other.level) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}