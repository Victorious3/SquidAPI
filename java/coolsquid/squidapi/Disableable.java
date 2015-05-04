/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;


public interface Disableable {
	public abstract void disable() throws Exception;
	public abstract void enable() throws Exception;
}