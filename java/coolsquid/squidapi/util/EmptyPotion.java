/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import net.minecraft.potion.Potion;

public class EmptyPotion extends Potion {
	
	protected EmptyPotion(int id) {
		super(id, false, 000000);
		this.setPotionName(Utils.newString("empty", id));
	}

	public static void replacePotion(int id) {
		new EmptyPotion(id);
	}
}