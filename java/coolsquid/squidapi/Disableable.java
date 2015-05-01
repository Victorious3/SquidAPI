/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

import coolsquid.squidapi.annotation.DevOnly;

public interface Disableable {
	@DevOnly
	public abstract void disable() throws Exception;
	public abstract void enable() throws Exception;
}