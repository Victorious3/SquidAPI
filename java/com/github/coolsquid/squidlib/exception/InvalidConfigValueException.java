package com.github.coolsquid.squidlib.exception;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class InvalidConfigValueException extends SquidAPIException {
	private static final long serialVersionUID = 434986523;
		
	public InvalidConfigValueException(String comment) {
		super(comment);
	}
}