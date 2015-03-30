/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.formatting;

import java.net.URL;

import coolsquid.squidapi.util.io.WebUtils;

/**
 * Simple Web Text Formatting Parser.
 */
public class SWTFParser extends STFParser {

	public SWTFParser(String url) {
		this(WebUtils.newURL(url));
	}

	public SWTFParser(URL url) {
		super(WebUtils.newReader(url));
	}
}