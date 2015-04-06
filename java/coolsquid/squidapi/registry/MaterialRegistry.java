/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.registry;

import net.minecraft.block.material.Material;

public class MaterialRegistry extends Registry<Material> {

	public static final MaterialRegistry INSTANCE = new MaterialRegistry();

	private MaterialRegistry() {
		this.register("air", Material.air);
		this.register("grass", Material.grass);
		this.register("ground", Material.ground);
		this.register("wood", Material.wood);
		this.register("rock", Material.rock);
		this.register("iron", Material.iron);
		this.register("anvil", Material.anvil);
		this.register("water", Material.water);
		this.register("lava", Material.lava);
		this.register("leaves", Material.leaves);
		this.register("plants", Material.plants);
		this.register("vine", Material.vine);
		this.register("sponge", Material.sponge);
		this.register("cloth", Material.cloth);
		this.register("fire", Material.fire);
		this.register("sand", Material.sand);
		this.register("circuits", Material.circuits);
		this.register("carpet", Material.carpet);
		this.register("glass", Material.glass);
		this.register("redstoneLight", Material.redstoneLight);
		this.register("tnt", Material.tnt);
		this.register("coral", Material.coral);
		this.register("ice", Material.ice);
		this.register("packedIce", Material.packedIce);
		this.register("snow", Material.snow);
		this.register("craftedSnow", Material.craftedSnow);
		this.register("cactus", Material.cactus);
		this.register("clay", Material.clay);
		this.register("gourd", Material.gourd);
		this.register("dragonEgg", Material.dragonEgg);
		this.register("portal", Material.portal);
		this.register("cake", Material.cake);
		this.register("web", Material.web);
		this.register("piston", Material.piston);
	}
}