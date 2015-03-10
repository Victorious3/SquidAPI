/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import com.github.coolsquid.squidapi.util.Utils;

@SuppressWarnings("unchecked")
public class ReflectionHelper {
	
	private final Class<?> clazz;

	private ReflectionHelper(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public static ReflectionHelper in(Class<?> clazz) {
		if (clazz == null) {
			String mod = Utils.getCurrentMod().getModId();
			throw new NullPointerException(Utils.newString("The parameter \"clazz\" can't be null! Contact the author of ", mod, "."));
		}
		return new ReflectionHelper(clazz);
	}
	
	public static PackageHelper in(String pakkage) {
		if (pakkage == null || pakkage.isEmpty()) {
			String mod = Utils.getCurrentMod().getModId();
			throw new NullPointerException(Utils.newString("The parameter \"pakkage\" can't be null! Contact the author of ", mod, "."));
		}
		return new PackageHelper(Package.getPackage(pakkage));
	}
	
	public MethodHelper method(String deobfname, String obfname, Class<?>... params) {
		return new MethodHelper(this.clazz, deobfname, obfname, params);
	}
	
	public FieldHelper field(String deobfname, String obfname) {
		return new FieldHelper(this.clazz, deobfname, obfname, false);
	}
	
	public FieldHelper finalField(String deobfname, String obfname) {
		return new FieldHelper(this.clazz, deobfname, obfname, true);
	}
	
	public <E> E newInstance(Object... params) {
		try {
			for (Constructor<?> c: this.clazz.getDeclaredConstructors()) {
				c.setAccessible(true);
				if (c.getParameters().length != params.length) {
					continue;
				}
				for (int a = 0; a < c.getParameters().length; a++) {
					Parameter p = c.getParameters()[a];
					if (p.getClass() != params[a].getClass()) {
						continue;
					}
				}
				return (E) c.newInstance(params);
			}
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isInstance(Object object) {
		return this.clazz.isInstance(object);
	}
	
	@SuppressWarnings("rawtypes")
	public <E> E getAnnotation(Class a) {
		return (E) this.clazz.getAnnotation(a);
	}
}