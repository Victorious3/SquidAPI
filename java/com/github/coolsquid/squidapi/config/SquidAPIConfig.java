/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SquidAPIConfig {
	
	private final File configFile;
	private List<String> lines;
	private final Map<String, Boolean> values = Maps.newHashMap();

	public SquidAPIConfig(File configFile) {
		this.configFile = configFile;
		try {
			this.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException {
		if (!this.configFile.exists()) {
			this.configFile.createNewFile();
		}
		this.lines = FileUtils.readLines(this.configFile);
		for (String value: this.lines) {
			if (!value.startsWith("//") && !value.startsWith("#")) {
				String[] s = value.split("=");
				if (s.length == 2) {
					this.values.put(s[0], Boolean.parseBoolean(s[1]));
				}
			}
		}
	}

	public boolean get(String name, boolean defaultValue) {
		if (this.values.containsKey(name)) {
			return this.values.get(name).booleanValue();
		}
		else {
			String a = Utils.newString(name, "=", defaultValue);
			this.lines.add(a);
			try {
				FileUtils.write(this.configFile, Utils.newString(a, Utils.newLine()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultValue;
		}
	}
	
	public void addHeader(String string) {
		if (this.lines.isEmpty() || !this.lines.get(0).equals(string)) {
			try {
				List<String> lines = Lists.newArrayList(string);
				lines.addAll(this.lines);
				FileUtils.writeLines(this.configFile, lines);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}