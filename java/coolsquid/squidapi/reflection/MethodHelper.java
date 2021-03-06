/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.reflection;

import java.lang.reflect.Method;

import coolsquid.squidapi.exception.ReflectionException;
import coolsquid.squidapi.util.MiscLib;

public class MethodHelper {
	
	private final Method method;

	MethodHelper(Class<?> clazz, String deobfname, String obfname, Class<?>... params) {
		String name = obfname;
		if (MiscLib.DEV_ENVIRONMENT) {
			name = deobfname;
		}
		try {
			this.method = clazz.getDeclaredMethod(name, params);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ReflectionException("Could not find method " + name);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E> E invoke(Object... params) {
		try {
			this.method.setAccessible(true);
			return (E) this.method.invoke(true, params);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
}