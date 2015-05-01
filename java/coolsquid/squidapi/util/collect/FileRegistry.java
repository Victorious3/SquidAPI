/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.io.File;

import coolsquid.squidapi.util.io.IOUtils.FileReader;

public class FileRegistry extends LockedRegistrySimple<String> {

	public FileRegistry(File file) {
		for (String a: new FileReader(file)) {
			this.register(a);
		}
		this.lock();
	}
}