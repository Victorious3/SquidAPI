/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.net.URL;

public interface IUpdateable {
	public abstract String getModid();
	public abstract String getName();
	public abstract String getVersion();
	public abstract URL getCurseUrl();
}