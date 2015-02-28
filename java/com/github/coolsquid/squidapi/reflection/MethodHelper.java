/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.reflection;

import java.lang.reflect.Method;

import com.github.coolsquid.squidapi.exception.ReflectionException;
import com.github.coolsquid.squidapi.util.Utils;

public class MethodHelper {
	
	private final Method method;

	MethodHelper(Class<?> clazz, String deobfname, String obfname, Class<?>... params) {
		String name = obfname;
		if (Utils.developmentEnvironment) {
			name = deobfname;
		}
		try {
			this.method = clazz.getDeclaredMethod(deobfname, params);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ReflectionException("Could not find method " + name);
		}
	}
	
	public Object invoke(Object... params) {
		try {
			this.method.setAccessible(true);
			return this.method.invoke(true, params);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
}