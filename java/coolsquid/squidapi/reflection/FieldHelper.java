/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import coolsquid.squidapi.exception.ReflectionException;
import coolsquid.squidapi.util.MiscLib;

public class FieldHelper {
	
	private final Field field;
	private final Object object;
	private final boolean isfinal;

	FieldHelper(Class<?> clazz, String deobfname, String obfname, boolean isfinal) {
		this.object = null;
		String name = obfname;
		if (MiscLib.DEV_ENVIRONMENT) {
			name = deobfname;
		}
		try {
			this.field = clazz.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException("Could not find field " + name);
		}
		this.isfinal = isfinal;
		this.field.setAccessible(true);
	}
	
	FieldHelper(Object object, String deobfname, String obfname, boolean isfinal) {
		this.object = object;
		String name = obfname;
		if (MiscLib.DEV_ENVIRONMENT) {
			name = deobfname;
		}
		Field f = null;
		if (object != null) {
			try {
				f = object.getClass().getDeclaredField(name);
			} catch (NoSuchFieldException | SecurityException e) {
				Class<?> clazz2 = object.getClass().getSuperclass();
				while (clazz2.getSuperclass() != Object.class) {
					try {
						f = clazz2.getDeclaredField(name);
					} catch (NoSuchFieldException | SecurityException e2) {
						clazz2 = clazz2.getSuperclass();
						continue;
					}
				}
			}
		}
		else {
			throw new NullPointerException(name);
		}
		this.isfinal = isfinal;
		this.field = f;
		this.field.setAccessible(true);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E get() {
		try {
			return (E) this.field.get(this.object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ReflectionException();
		}
	}
	
	public FieldHelper set(Object replacement) {
		try {
			if (this.isfinal) {
				Field m = Field.class.getDeclaredField("modifiers");
				m.setAccessible(true);
				m.setInt(this.field, this.field.getModifiers() & ~Modifier.FINAL);
			}
			this.field.set(this.object, replacement);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		return this;
	}
}