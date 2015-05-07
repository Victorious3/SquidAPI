/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class DataSorter {

	public static Map<String, String> sort(String data, String... keys) {
		Map<String, String> result = Maps.newHashMap();
		String[] datas = data.split(";");
		for (String s: datas) {
			for (String key: keys) {
				if (s.startsWith(key + '(') && s.endsWith(")")) {
					String s2 = s.replaceFirst(key, "");
					result.put(key, s2.substring(1, s2.length() - 1));
					continue;
				}
			}
		}
		return result;
	}
}