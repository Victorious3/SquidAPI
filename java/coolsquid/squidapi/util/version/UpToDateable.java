/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.HashMap;

import pt.api.IUpdateable;
import pt.api.UpdateableUtils;
import pt.uptodate.FetchedUpdateable;
import pt.uptodate.UpToDate;

import com.google.common.collect.Maps;

import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.Optional.Interface;

@Interface(iface = "pt.api.IUpdateable", modid = "uptodate")
public class UpToDateable implements IUpdateable {

	private final String name;
	private final String version;
	private final String url;

	public UpToDateable(String name, String version, String url) {
		this.name = name;
		this.version = version;
		this.url = url;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public HashMap<String, String> getLocal() {
		HashMap<String, String> map = Maps.newHashMap();
		map.put("technical", IntUtils.trim(this.version));
		map.put("display", this.version);
		return map;
	}

	@Override
	public String getRemote() {
		return UpdateableUtils.fromUrlToText(this.url);
	}

	public void register() {
		UpToDate.updates.add(new FetchedUpdateable(this));
	}
}