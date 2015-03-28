/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

public class VanillaTamperException extends SquidAPIException {
	
	private static final long serialVersionUID = -9152298603142599605L;
	
	public VanillaTamperException(TamperedContent type) {
		super("A mod has broken a Vanilla ", type.toString().toLowerCase(), "!");
	}
	
	public enum TamperedContent {
		ITEM,
		BLOCK;
	}
}