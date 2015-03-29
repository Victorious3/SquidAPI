/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.io;

import java.io.File;
import java.io.IOException;

public class SquidAPIFile extends File {

	private static final long serialVersionUID = -4571911940665891112L;

	public SquidAPIFile(String pathname) {
		super(pathname);
		if (!this.exists()) {
			File parent = this.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			try {
				this.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}