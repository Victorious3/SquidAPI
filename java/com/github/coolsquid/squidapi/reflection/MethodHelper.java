package com.github.coolsquid.squidapi.reflection;

import java.lang.reflect.Method;

import com.github.coolsquid.squidapi.exception.ReflectionException;

public class MethodHelper {
	
	private final Method method;

	MethodHelper(Class<?> clazz, String method, Class<?>... params) {
		try {
			this.method = clazz.getDeclaredMethod(method, params);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ReflectionException();
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