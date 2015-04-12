/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

import java.util.Map;

import net.minecraft.launchwrapper.IClassTransformer;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("coolsquid.squidapi.SquidAPIPlugin")
public class SquidAPIPlugin implements IFMLLoadingPlugin, IClassTransformer {

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
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.startsWith("coolsquid.squidapi.")) {
			hash += basicClass.length;
		}
		return basicClass;
	}

	public static long getHash() {
		return hash;
	}
}