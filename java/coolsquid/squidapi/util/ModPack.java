/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Set;

import com.google.common.base.Joiner;

public class ModPack implements IContent {

	private final String name;
	private final String description;
	private final Set<String> authors;
	private final String url;

	public ModPack(String name, String description, String author) {
		this(name, description, "N/A", author);
	}

	public ModPack(String name, String description, String url, String... authors) {
		this.name = name;
		this.description = description;
		this.authors = new OneWaySet<String>(authors);
		this.url = url;
	}

	public void addAuthors(String... authors) {
		for (String author: authors) {
			this.authors.add(author);
		}
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

	@Override
	public String getUrl() {
		return this.url;
	}
}