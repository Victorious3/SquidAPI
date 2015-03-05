/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.github.coolsquid.squidapi.exception.ReflectionException;
import com.github.coolsquid.squidapi.util.Utils;

public class FieldHelper {
	
	private final Field field;
	private final boolean isfinal;

	FieldHelper(Class<?> clazz, String deobfname, String obfname, boolean isfinal) {
		String name = obfname;
		if (Utils.developmentEnvironment()) {
			name = deobfname;
		}
		try {
			this.field = clazz.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException("Could not find field " + name);
		}
		this.isfinal = isfinal;
	}
	
	@SuppressWarnings("unchecked")
	public <E> E get() {
		return (E) this.get(null);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E get(Object object) {
		try {
			this.field.setAccessible(true);
			return (E) this.field.get(object);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void set(Object object, Object replacement) {
		try {
			if (this.isfinal) {
				Field m = Field.class.getDeclaredField("modifiers");
				m.setAccessible(true);
				m.setInt(this.field, this.field.getModifiers() & ~Modifier.FINAL);
			}
			
			this.field.setAccessible(true);
			this.field.set(object, replacement);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	public void set(Object replacement) {
		this.set(null, replacement);
	}
}