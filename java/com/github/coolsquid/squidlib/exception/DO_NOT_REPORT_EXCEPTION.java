package com.github.coolsquid.squidlib.exception;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class DO_NOT_REPORT_EXCEPTION extends SquidLibException {
	private static final long serialVersionUID = 6583935765453828886L;
	
	public DO_NOT_REPORT_EXCEPTION(String comment) {
		super(comment);
	}
}