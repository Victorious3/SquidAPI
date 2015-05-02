/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.mod;

import java.io.File;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModContainer.Disableable;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;

public class BaseMod {

	protected final ModContainer mod;

	public BaseMod(ModContainer mod) {
		this.mod = mod;
	}

	public ModContainer getContainer() {
		return this.mod;
	}

	public final String getModid() {
		return this.mod.getModId();
	}

	public final String getName() {
		return this.mod.getName();
	}

	public final String getVersion() {
		return this.mod.getVersion();
	}

	protected final File getSource() {
		return this.mod.getSource();
	}

	public final ModMetadata getMetadata() {
		return this.mod.getMetadata();
	}

	protected final void bindMetadata(MetadataCollection meta) {
		this.mod.bindMetadata(meta);
	}

	protected final void setEnabledState(boolean enabled) {
		this.mod.setEnabledState(enabled);
	}

	protected final Set<ArtifactVersion> getRequirements() {
		return this.mod.getRequirements();
	}

	protected final List<ArtifactVersion> getDependencies() {
		return this.mod.getDependencies();
	}

	protected final List<ArtifactVersion> getDependants() {
		return this.mod.getDependants();
	}

	protected final String getSortingRules() {
		return this.mod.getSortingRules();
	}

	protected final ArtifactVersion getProcessedVersion() {
		return this.mod.getProcessedVersion();
	}

	protected final String getDisplayVersion() {
		return this.mod.getDisplayVersion();
	}

	protected final VersionRange acceptableMinecraftVersionRange() {
		return this.mod.acceptableMinecraftVersionRange();
	}

	protected final Certificate getSigningCertificate() {
		return this.mod.getSigningCertificate();
	}

	protected final Map<String, String> getCustomModProperties() {
		return this.mod.getCustomModProperties();
	}

	protected final Class<?> getCustomResourcePackClass() {
		return this.mod.getCustomResourcePackClass();
	}

	protected final Disableable canBeDisabled() {
		return this.mod.canBeDisabled();
	}

	protected final String getGuiClassName() {
		return this.mod.getGuiClassName();
	}

	protected final List<String> getOwnedPackages() {
		return this.mod.getOwnedPackages();
	}

	protected final void addAuthors(String... authors) {
		for (String author: authors) {
			this.getMetadata().authorList.add(author);
		}
	}
}