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
public class SCFParser implements ISCFParser {

	private final List<ChatMessage> list = Lists.newArrayList();

	public SCFParser(Iterable<String> lines) {
		for (String line: lines) {
			this.list.add(this.format(line));
		}
	}

	@Override
	public List<ChatMessage> get() {
		return this.list;
	}

	private ChatMessage format(String line) {
		ChatMessage msg = new ChatMessage();
		if (line.contains(">--")) {
			msg.setText(line.replaceFirst(">--", ""));
			String code = line.split(">--")[0];
			for (char c: code.toCharArray()) {
				STFCodes.getCode(c).apply(msg);
			}
		}
		else {
			msg.setText(line);
		}
		return msg;
	}
}