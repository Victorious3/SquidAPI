/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;


public final class Suggestion {
	
	private final String suggestor;
	private final String suggestion;
	private final String reason;

	public Suggestion(String suggestor, String suggestion, String reason) {
		this.suggestor = suggestor;
		this.suggestion = suggestion;
		this.reason = reason;
	}

	public String getSuggestor() {
		return this.suggestor;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public String getReason() {
		return this.reason;
	}
}