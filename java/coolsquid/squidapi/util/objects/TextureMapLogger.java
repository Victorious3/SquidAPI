/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.objects;

import org.apache.logging.log4j.core.Logger;

public class TextureMapLogger extends Logger {

	public TextureMapLogger(Logger logger) {
		super(logger.getContext(), logger.getName(), logger.getMessageFactory());
	}

	@Override
	public void error(String message, Throwable t) {
		if (!message.startsWith("Using missing texture, unable to load ")) {
			super.error(message, t);
		}
		else {
			super.error(message);
		}
	}
}