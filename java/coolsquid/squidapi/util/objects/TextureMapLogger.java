/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.objects;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.message.MessageFactory;

public class TextureMapLogger extends Logger {

	public TextureMapLogger(LoggerContext context, String name, MessageFactory messageFactory) {
		super(context, name, messageFactory);
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