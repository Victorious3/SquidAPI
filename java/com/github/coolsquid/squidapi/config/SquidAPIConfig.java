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
import com.github.coolsquid.squidapi.util.IterableMap;
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
		this.configFile.getParentFile().mkdirs();
		if (!this.configFile.exists()) {
			this.configFile.createNewFile();
		}
		this.lines = FileUtils.readLines(this.configFile);
		for (String line: this.lines) {
			if (!line.startsWith("//") && !line.startsWith("#")) {
				if (line.startsWith("B:")) {
					String[] s = line.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("B:", ""), Boolean.parseBoolean(s[1]));
					}
				}
				else if (line.startsWith("S:")) {
					String[] s = line.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("S:", ""), s[1]);
					}
				}
				else if (line.startsWith("I:")) {
					String[] s = line.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("I:", ""), IntUtils.parseInt(s[1]));
					}
				}
				else if (line.startsWith("F:")) {
					String[] s = line.split("=");
					if (s.length == 2) {
						this.values.put(s[0].replaceFirst("F:", ""), Float.parseFloat(s[1]));
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
	
	public float get(String name, float defaultValue) {
		if (this.values.containsKey(name)) {
			return (float) this.values.get(name);
		}
		else {
			String a = Utils.newString(name, "=", defaultValue);
			this.lines.add(a);
			this.values.put(name, defaultValue);
			try {
				FileUtils.write(this.configFile, Utils.newString("F:", a, Utils.newLine()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultValue;
		}
	}
	
	public IterableMap<String, Object> getEntries() {
		IterableMap<String, Object> map = new IterableMap<String, Object>();
		map.putAll(this.values);
		return map;
	}
	
	public void addHeader(String string) {
		if (this.lines != null && (this.lines.isEmpty() || !this.lines.get(0).equals(string))) {
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