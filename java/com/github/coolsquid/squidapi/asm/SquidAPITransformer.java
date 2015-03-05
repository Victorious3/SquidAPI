/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import com.github.coolsquid.squidapi.helpers.LogHelper;

//not currently used
public class SquidAPITransformer implements IClassTransformer, Opcodes {

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("")) {
			LogHelper.info("Tweaking ", transformedName, ".class.");
			ClassNode c = ASMHelper.createClassNode(basicClass);
			
			basicClass = ASMHelper.getBytes(c, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		}
		return basicClass;
	}
	
	/*private static void transformMethod(MethodNode m) {
		InsnList list = new InsnList();
		
		m.instructions.insertBefore(new InsnNode(0), list);
	}*/
}