package com.github.coolsquid.squidapi.command;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CommandNames {
	
	private static final String BUNDLE_NAME = "com.github.coolsquid.squidapi.command.commands";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private CommandNames() {
		
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
}
