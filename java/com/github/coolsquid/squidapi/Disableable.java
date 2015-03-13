/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

public interface Disableable {
	void disable() throws Exception;
	void enable() throws Exception;
}