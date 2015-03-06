/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.Opcodes;

//not currently used
public class SquidAPITransformer implements IClassTransformer, Opcodes {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("com.github.coolsquid.squidapi.SquidAPI")) {
			
		}
		return basicClass;
	}
	
	/*private static void transformMethod(MethodNode m) {
		InsnList list = new InsnList();
		
		m.instructions.insertBefore(new InsnNode(0), list);
	}*/
}