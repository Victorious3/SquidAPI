/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

public interface Disableable {
	void disable() throws Exception;
	void enable() throws Exception;
}