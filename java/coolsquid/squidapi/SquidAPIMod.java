/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import coolsquid.squidapi.command.CommandDisable;
import coolsquid.squidapi.exception.MisuseException;
import coolsquid.squidapi.mod.AdvancedMod;
import coolsquid.squidapi.mod.SideOnly.ClientOnly;
import coolsquid.squidapi.mod.SideOnly.ServerOnly;
import coolsquid.squidapi.util.Incompatibility;
import coolsquid.squidapi.util.Incompatibility.Severity;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.ModManager;
import coolsquid.squidapi.util.SuggestionManager;
import coolsquid.squidapi.util.math.IntUtils;
import coolsquid.squidapi.util.objects.Suggestion;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModMetadata;

public class SquidAPIMod extends AdvancedMod {

	private final Set<Incompatibility> incompatibilities = Sets.newHashSet();
	private final File configFile;
	private final int hashCode;

	public SquidAPIMod(String desc) {
		this(desc, Lists.newArrayList("CoolSquid"), "", "http://coolsquid.wix.com/software");
	}

	@Deprecated
	public SquidAPIMod(String desc, String curseId) {
		this(desc);
	}

	@Deprecated
	public SquidAPIMod(String desc, List<String> authors, String credits, String url, String curseId) {
		this(desc, authors, credits, url);
	}

	public SquidAPIMod(String desc, List<String> authors, String credits, String url) {
		super(Loader.instance().activeModContainer());

		final int prime = 31;
		int result = 1;
		result = prime * result + this.getModid().hashCode();
		result = prime * result + this.getVersion().hashCode();
		this.hashCode = result;

		this.configFile = new File("./config/" + this.getModid() + ".cfg");

		ModMetadata meta = this.mod.getMetadata();
		meta.autogenerated = false;
		meta.credits = credits;
		meta.authorList = authors;
		meta.description = desc;

		if (this instanceof ClientOnly && MiscLib.SERVER) {
			throw new MisuseException(this.getName() + " is clientside only!");
		}
		else if (this instanceof ServerOnly && MiscLib.CLIENT) {
			throw new MisuseException(this.getName() + " is serverside only!");
		}

		if (this instanceof Disableable) {
			CommandDisable.disableables.put(this.getModid(), (Disableable) this);
		}

		this.info("Registering SquidAPIMod ", this.getModid(), ".");

		ModManager.INSTANCE.registerMod(this.getModid(), this);
	}

	public int getVersionAsInt() {
		return IntUtils.parseInt(this.getVersion());
	}

	public final Set<Incompatibility> getIncompatibilities() {
		return ImmutableSet.copyOf(this.incompatibilities);
	}

	protected final void registerIncompatibility(Incompatibility incompatibility) {
		if (Loader.isModLoaded(incompatibility.getModid())) {
			this.incompatibilities.add(incompatibility);
		}
	}

	protected final void registerIncompatibility(String modid, String reason, Severity severity) {
		this.registerIncompatibility(new Incompatibility(modid, reason, severity));
	}

	protected final void suggestMod(Suggestion suggestion) {
		SuggestionManager.INSTANCE.suggestMod(suggestion);
	}

	protected final void suggestMod(String suggestion, String reason, String url) {
		this.suggestMod(suggestion, suggestion, reason, url);
	}

	protected final void suggestMod(String suggestion, String modid, String reason, String url) {
		this.suggestMod(new Suggestion(this.getModid(), suggestion, modid, reason, url));
	}

	@Deprecated
	protected final void setDisableable() {
		if (!(this instanceof Disableable)) {
			throw new IllegalArgumentException();
		}
		CommandDisable.disableables.put(this.getModid(), (Disableable) this);
	}

	protected final void preInit() {

	}

	protected final void init() {

	}

	protected final void postInit() {
		for (Incompatibility a: this.getIncompatibilities()) {
			this.bigWarning("Incompatibility detected! ", this.mod.getName(), " has issues with ", a.getModid(), ". Reason: ", a.getReason(), ". Severity: ", a.getSeverity(), ".", MiscLib.LINE, "Please contact ", this.mod.getMetadata().getAuthorList(), " for more information.");
		}
	}

	public long hash() {
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public String toString() {
		return "SquidAPIMod [modid=" + this.getModid() + ", version=" + this.getVersion() + "]";
	}

	@Override
	public int hashCode() {
		return this.hashCode;
	}

	public File getConfigFile() {
		return this.configFile;
	}
}