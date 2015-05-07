/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import coolsquid.squidapi.util.StringUtils;
import coolsquid.squidapi.util.Utils;

@SuppressWarnings("unchecked")
public class ReflectionHelper {

	private final Class<?> clazz;
	private final Object object;

	private ReflectionHelper(Class<?> clazz) {
		this.clazz = clazz;
		this.object = null;
	}

	public ReflectionHelper(Object object) {
		this.clazz = object.getClass();
		this.object = object;
	}

	public static ReflectionHelper in(Class<?> clazz) {
		if (clazz == null) {
			String mod = Utils.getCurrentMod().getModId();
			throw new NullPointerException(StringUtils.newString("The parameter \"clazz\" can't be null! Contact the author of ", mod, "."));
		}
		return new ReflectionHelper(clazz);
	}

	public static ReflectionHelper in(Object object) {
		if (object == null) {
			String mod = Utils.getCurrentMod().getModId();
			throw new NullPointerException(StringUtils.newString("The parameter \"object\" can't be null! Contact the author of ", mod, "."));
		}
		return new ReflectionHelper(object);
	}

	public static PackageHelper in(String pakkage) {
		if (pakkage == null || pakkage.isEmpty()) {
			String mod = Utils.getCurrentMod().getModId();
			throw new NullPointerException(StringUtils.newString("The parameter \"pakkage\" can't be null! Contact the author of ", mod, "."));
		}
		return new PackageHelper(Package.getPackage(pakkage));
	}

	public MethodHelper method(String deobfname, String obfname, Class<?>... params) {
		return new MethodHelper(this.clazz, deobfname, obfname, params);
	}

	public FieldHelper field(String... names) {
		if (this.object == null) {
			return new FieldHelper(this.clazz, names[0], names.length > 1 ? names[1] : names[0], false);
		}
		return new FieldHelper(this.object, names[0], names.length > 1 ? names[1] : names[0], false);
	}

	public FieldHelper finalField(String... names) {
		if (this.object == null) {
			return new FieldHelper(this.clazz, names[0], names.length > 1 ? names[1] : names[0], true);
		}
		return new FieldHelper(this.object, names[0], names.length > 1 ? names[1] : names[0], true);
	}

	public <E> E newInstance(Object... params) {
		Class<?>[] paramTypes = new Class<?>[params.length];
		for (int a = 0; a < params.length; a++) {
			paramTypes[a] = params[a].getClass();
		}
		try {
			Constructor<E> c = (Constructor<E>) this.clazz.getConstructor(paramTypes);
			c.setAccessible(true);
			return c.newInstance(params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
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