/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.List;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;

public class SuggestionManager {

	public static final SuggestionManager INSTANCE = new SuggestionManager();

	private final List<Suggestion> suggestions = Lists.newArrayList();

	public void suggestMod(Suggestion suggestion) {
		if (!Loader.isModLoaded(suggestion.getModid())) {
			this.suggestions.add(suggestion);
		}
	}

	public Suggestion getRandomSuggestedMod() {
		return this.suggestions.get(Utils.getRandInt(0, this.suggestions.size() - 1));
	}

	public Suggestion getRandomSuggestedModEnsureNotSame(Suggestion a) {
		Suggestion b = this.getRandomSuggestedMod();
		while (b == a) {
			b = this.getRandomSuggestedMod();
		}
		return b;
	}
}