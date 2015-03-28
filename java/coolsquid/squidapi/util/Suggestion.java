/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;



public final class Suggestion {
	
	private final String suggestor;
	private final String suggestion;
	private final String modid;
	private final String reason;
	private final String url;

	public Suggestion(String suggestor, String suggestion, String modid, String reason, String url) {
		this.suggestor = suggestor;
		this.suggestion = suggestion;
		this.modid = modid;
		this.reason = Charsets.punctuate(reason);
		this.url = url;
	}

	public String getSuggestor() {
		return this.suggestor;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public String getSuggestionModid() {
		return this.modid;
	}

	public String getReason() {
		return this.reason;
	}

	public String getUrl() {
		return this.url;
	}
}