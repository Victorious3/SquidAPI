/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.github.coolsquid.squidapi.util.Utils;

public class ReflectionHelper {
	
	/**
	 * Get an object.
	 * @param c
	 * @param fieldName
	 * @return Object
	 */
	
	public static final Object get(Class<?> c, String fieldName) {
		try {
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			return f;
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final Method getMethod(Class<?> c, String methodName) {
		try {
			Method m = c.getDeclaredMethod(methodName);
			m.setAccessible(true);
			return m;
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final void invoke(String className, String methodName) {
		try {
			Class<?> c = Class.forName(className);
			Method m = c.getDeclaredMethod(methodName);
			m.setAccessible(true);
			m.invoke(true);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	public static final void replaceField(Class<?> c, String fieldName, Object replacement) {
		try {
			Field f = c.getDeclaredField(fieldName);
			
			Field m = Field.class.getDeclaredField("modifiers");
			m.setAccessible(true);
			m.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			
			f.setAccessible(true);
			f.set(f, replacement);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	public static final void replaceField(Class<?> c, Object object, String fieldName, Object replacement) {
		try {
			Field f = c.getDeclaredField(fieldName);
			
			Field m = Field.class.getDeclaredField("modifiers");
			m.setAccessible(true);
			m.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			
			f.setAccessible(true);
			f.set(object, replacement);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	public static final void replaceField(Class<?> c, Object object, String deObfFieldName, String obfFieldName, Object replacement) {
		if (Utils.developmentEnvironment) {
			replaceField(c, object, deObfFieldName, replacement);
		}
		else {
			replaceField(c, object, obfFieldName, replacement);
		}
	}
	
	public static final void replaceField(Class<?> c, String deObfFieldName, String obfFieldName, Object replacement) {
		if (Utils.developmentEnvironment) {
			replaceField(c, deObfFieldName, replacement);
		}
		else {
			replaceField(c, obfFieldName, replacement);
		}
	}
}