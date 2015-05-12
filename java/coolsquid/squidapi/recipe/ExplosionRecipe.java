/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.recipe;

import java.util.HashMap;
import java.util.Objects;

import net.minecraft.item.ItemStack;

public class ExplosionRecipe {
	
	public static final HashMap<Object, ExplosionRecipe> recipes = new HashMap<Object, ExplosionRecipe>();
	
	private Object input;
	private ItemStack output;
	private float strength;
	
	public ExplosionRecipe(Object input, ItemStack output, float strength) {
		this.input = input;
		this.output = output;
		this.strength = strength;
	}
	
	public Object getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public float getStrength() {
		return strength;
	}

	@Override
	public int hashCode() {
		return Objects.hash(input, output, strength);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ExplosionRecipe)) {
			return false;
		}
		ExplosionRecipe other = (ExplosionRecipe) obj;
		if (input == null) {
			if (other.input != null) {
				return false;
			}
		} else if (!input.equals(other.input)) {
			return false;
		}
		if (output == null) {
			if (other.output != null) {
				return false;
			}
		} else if (!output.equals(other.output)) {
			return false;
		}
		if (Float.floatToIntBits(strength) != Float
				.floatToIntBits(other.strength)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExplosionRecipe [input=" + input + ", output=" + output
				+ ", strength=" + strength + "]";
	}
}