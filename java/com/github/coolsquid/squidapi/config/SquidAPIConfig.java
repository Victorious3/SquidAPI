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

import com.github.coolsquid.squidapi.util.IntUtils;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SquidAPIConfig {
	
	private final File configFile;
	private List<String> lines;
	private final Map<String, Object> values = Maps.newHashMap();

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
				if (value.startsWith("B:")) {
					String[] s = value.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("B:", ""), Boolean.parseBoolean(s[1]));
					}
				}
				else if (value.startsWith("S:")) {
					String[] s = value.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("S:", ""), s[1]);
					}
				}
				else if (value.startsWith("I:")) {
					String[] s = value.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("I:", ""), IntUtils.parseInt(s[1]));
					}
				}
			}
		}
	}

	public boolean get(String name, boolean defaultValue) {
		if (this.values.containsKey(name)) {
			return ((boolean) this.values.get(name));
		}
		else {
			String a = Utils.newString(name, "=", defaultValue);
			this.lines.add(a);
			this.values.put(name, defaultValue);
			try {
				FileUtils.write(this.configFile, Utils.newString("B:", a, Utils.newLine()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultValue;
		}
	}
	
	public String get(String name, String defaultValue) {
		if (this.values.containsKey(name)) {
			return (String) this.values.get(name);
		}
		else {
			String a = Utils.newString(name, "=", defaultValue);
			this.lines.add(a);
			this.values.put(name, defaultValue);
			try {
				FileUtils.write(this.configFile, Utils.newString("S:", a, Utils.newLine()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultValue;
		}
	}
	
	public int get(String name, int defaultValue) {
		if (this.values.containsKey(name)) {
			return (int) this.values.get(name);
		}
		else {
			String a = Utils.newString(name, "=", defaultValue);
			this.lines.add(a);
			this.values.put(name, defaultValue);
			try {
				FileUtils.write(this.configFile, Utils.newString("I:", a, Utils.newLine()), true);
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