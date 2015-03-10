/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.reflection;


public class PackageHelper {

	private final Package pakkage;

	PackageHelper(Package pakkage) {
		this.pakkage = pakkage;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E> E getAnnotation(Class annotation) {
		return (E) this.pakkage.getAnnotation(annotation);
	}
}