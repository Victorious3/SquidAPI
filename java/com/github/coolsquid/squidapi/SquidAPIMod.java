/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.github.coolsquid.squidapi.command.CommandDisable;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.registry.RegistrySimple;
import com.github.coolsquid.squidapi.util.Incompatibility;
import com.github.coolsquid.squidapi.util.Incompatibility.Severity;
import com.github.coolsquid.squidapi.util.Suggestion;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;

public class SquidAPIMod {

	private static final RegistrySimple<SquidAPIMod> mods = new RegistrySimple<SquidAPIMod>();
	private static final RegistrySimple<String> modids = new RegistrySimple<String>();
	private static final List<Suggestion> suggestedMods = Lists.newArrayList();

	private final ModContainer mod;
	private final List<Incompatibility> incompatibilities = Lists.newArrayList();

	public SquidAPIMod(String desc) {
		this(desc, Lists.newArrayList("CoolSquid"), "", "http://coolsquid.wix.com/software");
	}

	public SquidAPIMod(String desc, List<String> authors, String credits, String url) {
		this.mod = Loader.instance().activeModContainer();

		ModMetadata meta = this.mod.getMetadata();
		meta.autogenerated = false;
		meta.credits = credits;
		meta.authorList = authors;
		meta.description = desc;

		LogHelper.info("Registering SquidAPIMod ", this.mod.getModId(), ".");

		mods.register(this);
		modids.register(this.getMod().getModId());
	}

	public final ModContainer getMod() {
		return this.mod;
	}

	public final ModMetadata getMetadata() {
		return this.mod.getMetadata();
	}

	public final List<Incompatibility> getIncompatibilities() {
		return ImmutableList.copyOf(this.incompatibilities);
	}

	protected final void registerIncompatibility(Incompatibility incompatibility) {
		this.incompatibilities.add(incompatibility);
	}

	protected final void registerIncompatibility(String modid, String reason, Severity severity) {
		this.registerIncompatibility(new Incompatibility(modid, reason, severity));
	}

	protected final void suggestMod(Suggestion suggestion) {
		if (!Loader.isModLoaded(suggestion.getSuggestion())) {
			suggestedMods.add(suggestion);
		}
	}

	protected final void suggestMod(String suggestion, String reason, String url) {
		this.suggestMod(new Suggestion(this.getMod().getModId(), suggestion, reason, url));
	}

	protected void setDisableable() {
		if (!(this instanceof Disableable)) {
			throw new IllegalArgumentException();
		}
		CommandDisable.disableables.put(this.getMod().getModId(), (Disableable) this);
	}

	protected final void preInit() {
		
	}

	protected final void init() {
		
	}

	protected final void postInit() {
		for (Incompatibility a: this.getIncompatibilities()) {
			if (Loader.isModLoaded(a.getModid())) {
				LogHelper.bigWarning(Level.WARN, "Incompatibility detected! ", this.mod.getName(), " has issues with ", a.getModid(), ". Reason: ", a.getReason(), ". Severity: ", a.getSeverity(), ".", Utils.newLine(), "Please contact ", this.mod.getMetadata().getAuthorList(), " for more information.");
			}
		}
	}

	public static List<SquidAPIMod> getMods() {
		return ImmutableList.copyOf(mods);
	}
	
	public static List<String> getModids() {
		return ImmutableList.copyOf(modids);
	}

	public static Suggestion getRandomSuggestedMod() {
		return suggestedMods.get(Utils.getRandInt(0, suggestedMods.size() - 1));
	}

	public static List<Suggestion> getSuggestions() {
		return ImmutableList.copyOf(suggestedMods);
	}
}