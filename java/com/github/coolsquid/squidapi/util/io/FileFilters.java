/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util.io;

import java.io.File;
import java.io.FileFilter;

public class FileFilters {
	
	public static final FileExtensionFilter JAR_FILES = new FileExtensionFilter(".jar");
	public static final FileExtensionFilter ZIP_FILES = new FileExtensionFilter(".zip");
	public static final FileExtensionFilter TEXT_FILES = new FileExtensionFilter(".txt", ".cfg");
	
	public static class FileExtensionFilter implements FileFilter {

		private final String[] extensions;

		public FileExtensionFilter(String... extensions) {
			this.extensions = extensions;
		}

		@Override
		public boolean accept(File file) {
			for (String a: this.extensions) {
				if (file.getName().endsWith(a)) {
					return true;
				}
			}
			return false;
		}
	}
}