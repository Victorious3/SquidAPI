/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.formatting;

import java.util.List;

import com.google.common.collect.Lists;

import coolsquid.squidapi.helpers.server.chat.ChatMessage;

/**
 * Simple Text Formatting Parser.
 */
public class STFParser {

	private final List<ChatMessage> list = Lists.newArrayList();

	public STFParser(Iterable<String> lines) {
		for (String line: lines) {
			this.list.add(this.format(line));
		}
	}

	public List<ChatMessage> get() {
		return this.list;
	}

	private ChatMessage format(String line) {
		ChatMessage msg = new ChatMessage();
		if (line.contains("--")) {
			String code = line.split("--")[0];
			STFCodes.getCode(code).apply(msg);
			line = line.substring(code.length() + 2);
		}
		msg.setText(line);
		return msg;
	}
}