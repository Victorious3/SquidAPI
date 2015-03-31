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
public class WebSCFParser extends SCFParser {

	public WebSCFParser(String url) {
		this(WebUtils.newURL(url));
	}

	public WebSCFParser(URL url) {
		super(WebUtils.newReader(url));
	}
}