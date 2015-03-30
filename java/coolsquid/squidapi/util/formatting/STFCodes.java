/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.formatting;

import java.util.Map;

import net.minecraft.util.EnumChatFormatting;

import com.google.common.collect.Maps;

import coolsquid.squidapi.helpers.server.chat.ChatMessage;

public class STFCodes {

	private static final Map<String, STFCodes> map = Maps.newHashMap();

	public static final STFCodes ITALIC = new STFCodes("**", EnumChatFormatting.ITALIC);
	public static final STFCodes BOLD = new STFCodes("***", EnumChatFormatting.BOLD);
	public static final STFCodes BOLD_AND_ITALIC = new STFCodes("****", EnumChatFormatting.BOLD, EnumChatFormatting.ITALIC);

	private final String key;
	private final EnumChatFormatting[] formatting;

	public STFCodes(String key, EnumChatFormatting... formatting) {
		this.key = key;
		this.formatting = formatting;
		map.put(key, this);
	}

	public String getKey() {
		return this.key;
	}

	public ChatMessage apply(ChatMessage input) {
		for (EnumChatFormatting formatting: this.formatting) {
			if (formatting == EnumChatFormatting.BOLD) {
				input.setBold();
			}
			else if (formatting == EnumChatFormatting.ITALIC) {
				input.setItalic();
			}
			else if (formatting == EnumChatFormatting.UNDERLINE) {
				input.setUnderlined();
			}
			else if (formatting == EnumChatFormatting.OBFUSCATED) {
				input.getChatStyle().setObfuscated(true);
			}
			else if (formatting == EnumChatFormatting.STRIKETHROUGH) {
				input.getChatStyle().setStrikethrough(true);
			}
			else {
				input.setColor(formatting);
			}
		}
		return input;
	}

	public static STFCodes getCode(String key) {
		return map.get(key);
	}

	static {
		for (EnumChatFormatting a: EnumChatFormatting.values()) {
			new STFCodes(a.getFormattingCode() + "", a);
		}
	}
}