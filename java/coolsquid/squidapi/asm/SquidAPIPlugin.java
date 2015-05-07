/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.asm;

import java.util.List;
import java.util.Map;

import net.minecraft.launchwrapper.IClassTransformer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"coolsquid.squidapi.asm", "coolsquid.squidapi.exception"})
public class SquidAPIPlugin implements IFMLLoadingPlugin, IClassTransformer {

	public static final Logger LOGGER = LogManager.getLogger("SquidAPI");

	private static int hash = 1;

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {SquidAPIPlugin.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public byte[] transform(String untransformedName, String name, byte[] basicClass) {
		if (name.startsWith("coolsquid.squidapi.")) {
			hash += basicClass.length;
		}
		if (!ASMHelper.DEV && name.startsWith("coolsquid.") || name.startsWith("squidapimod.")) {
			ClassNode c = ASMHelper.createClassNodeFromBytes(basicClass);
			for (int i = 0; i < c.methods.size(); i++) {
				List<AnnotationNode> as = c.methods.get(i).visibleAnnotations;
				if (as != null) {
					for (AnnotationNode a: as) {
						if (a.desc.equals("Lcoolsquid/squidapi/annotation/DevOnly;")) {
							c.methods.remove(i);
							break;
						}
					}
				}
			}
			basicClass = ASMHelper.getBytesFromClassNodeNoMsg(c);
		}
		return basicClass;
	}

	public static long getHash() {
		return hash;
	}
}