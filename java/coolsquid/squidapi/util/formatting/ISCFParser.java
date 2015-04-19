/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.formatting;

import java.util.List;

import coolsquid.squidapi.helpers.server.chat.ChatMessage;

public interface ISCFParser {
	public abstract List<ChatMessage> get();
}