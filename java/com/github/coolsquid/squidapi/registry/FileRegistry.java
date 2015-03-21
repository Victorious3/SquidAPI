/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.io.File;

import com.github.coolsquid.squidapi.util.io.IOUtils.FileReader;

public class FileRegistry extends LockedRegistrySimple<String> {

	public FileRegistry(File file) {
		for (String a: new FileReader(file)) {
			this.register(a);
		}
		this.lock();
	}
}