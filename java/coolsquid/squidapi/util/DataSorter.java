/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class DataSorter {

	public static Map<String, String> sort(String[] data, String... keys) {
		Map<String, String> result = Maps.newHashMap();
		for (String s: data) {
			for (String key: keys) {
				if (s.startsWith(key)) {
					result.put(key, s.replaceFirst(key, ""));
					continue;
				}
			}
		}
		return result;
	}
}