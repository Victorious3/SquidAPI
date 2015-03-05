/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.github.coolsquid.squidapi.helpers.LogHelper;

public class ASMHelper {
	
	public static byte[] getBytes(ClassNode c, int flags) {
		ClassWriter cw = new ClassWriter(flags);
		c.accept(cw);
		byte[] b = cw.toByteArray();
		LogHelper.info("Successfully tweaked ", c.name.replace("/", "."), ".");
		return b;
	}
	
	public static ClassNode createClassNode(byte[] bytes) {
		ClassNode c = new ClassNode();
		ClassReader r = new ClassReader(bytes);
		r.accept(c, ClassReader.EXPAND_FRAMES);
		return c;
	}
	
	public static MethodNode findMethod(ClassNode c, String name, String desc) {
		for (MethodNode m: c.methods ) {
			if (name.equals(m.name) && desc.equals(m.desc)) {
				return m;
			}
		}
		throw new RuntimeException(name);
	}
}