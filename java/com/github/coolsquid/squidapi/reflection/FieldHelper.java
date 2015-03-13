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
	private final Object object;
	private final boolean isfinal;

	FieldHelper(Class<?> clazz, String deobfname, String obfname, boolean isfinal) {
		this.object = null;
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
	
	FieldHelper(Object object, String deobfname, String obfname, boolean isfinal) {
		this.object = object;
		String name = obfname;
		if (Utils.developmentEnvironment()) {
			name = deobfname;
		}
		try {
			if (object != null) {
				this.field = object.getClass().getDeclaredField(name);
			}
			else {
				throw new NullPointerException(name);
			}
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException("Could not find field " + name);
		}
		this.isfinal = isfinal;
	}
	
	@SuppressWarnings("unchecked")
	public <E> E get() {
		try {
			return (E) this.field.get(this.object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new ReflectionException();
		}
	}
	
	public void set(Object replacement) {
		try {
			if (this.isfinal) {
				Field m = Field.class.getDeclaredField("modifiers");
				m.setAccessible(true);
				m.setInt(this.field, this.field.getModifiers() & ~Modifier.FINAL);
			}
			
			this.field.setAccessible(true);
			this.field.set(this.object, replacement);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
}