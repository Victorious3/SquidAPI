/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;



public final class Suggestion implements IContent {
	
	private final String suggestor;
	private final String name;
	private final String modid;
	private final String description;
	private final String url;
	private final Set<String> authors;

	public Suggestion(String suggestor, String name, String modid, String description, String url, String... authors) {
		this.suggestor = suggestor;
		this.name = name;
		this.modid = modid;
		this.description = Charsets.punctuate(description);
		this.url = url;
		this.authors = Sets.newHashSet(authors);
	}

	public String getSuggestor() {
		return suggestor;
	}

	public String getModid() {
		return this.modid;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getAuthors() {
		return Joiner.on(", ").join(this.authors);
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}