/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import java.util.List;

import com.github.coolsquid.squidapi.SquidAPIMod;
import com.github.coolsquid.squidapi.util.IterableMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;

public final class IncompatibilityHandler {
	
	private static final IncompatibilityHandler instance = new IncompatibilityHandler();
	
	private final IterableMap<SquidAPIMod, List<Incompatibility>> incompatibilities = new IterableMap<SquidAPIMod, List<Incompatibility>>();

	private List<Incompatibility> getIncompatibilities2(SquidAPIMod mod) {
		if (!this.incompatibilities.containsKey(mod)) {
			this.incompatibilities.put(mod, Lists.newArrayList());
		}
		return this.incompatibilities.get(mod);
	}
	
	private IncompatibilityHandler() {}
	
	public void registerIncompatibility(SquidAPIMod mod, Incompatibility incompatibility) {
		this.getIncompatibilities2(mod).add(incompatibility);
	}

	public List<Incompatibility> getIncompatibilities(SquidAPIMod mod, Severity severity) {
		ImmutableList.Builder<Incompatibility> a = ImmutableList.builder();
		for (Incompatibility b: this.getIncompatibilities2(mod)) {
			if (Loader.isModLoaded(b.getModid()) && b.getSeverity() == severity) {
				a.add(b);
			}
		}
		return a.build();
	}
	
	public List<Incompatibility> getIncompatibilities(SquidAPIMod mod) {
		ImmutableList.Builder<Incompatibility> a = ImmutableList.builder();
		for (Incompatibility b: this.getIncompatibilities2(mod)) {
			if (Loader.isModLoaded(b.getModid())) {
				a.add(b);
			}
		}
		return a.build();
	}
	
	public static IncompatibilityHandler instance() {
		return instance;
	}

	public static final class Incompatibility {
		private final String modid;
		private final String reason;
		private final Severity severity;
		
		public Incompatibility(String modid, String reason, Severity severity) {
			this.modid = modid;
			this.reason = reason;
			this.severity = severity;
		}

		public String getModid() {
			return this.modid;
		}

		public String getReason() {
			return this.reason;
		}

		public Severity getSeverity() {
			return this.severity;
		}
	}
	
	public enum Severity {
		LOW,
		MEDIUM,
		HIGH;
	}
}