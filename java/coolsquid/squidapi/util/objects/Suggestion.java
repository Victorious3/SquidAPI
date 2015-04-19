/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.objects;

import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

import coolsquid.squidapi.util.Charsets;



public final class Suggestion {
	
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
		return this.suggestor;
	}

	public String getModid() {
		return this.modid;
	}

	public String getUrl() {
		return this.url;
	}

	public String getName() {
		return this.name;
	}

	public String getAuthors() {
		return Joiner.on(", ").join(this.authors);
	}

	public String getDescription() {
		return this.description;
	}
}