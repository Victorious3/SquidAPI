/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.recipe;

import com.github.coolsquid.squidapi.handlers.ExplosionRecipeHandler;

public class ExplosionRecipe {
	
	public ExplosionRecipe(Object input, Object output) {
		ExplosionRecipeHandler.recipes.put(input, output);
	}
}