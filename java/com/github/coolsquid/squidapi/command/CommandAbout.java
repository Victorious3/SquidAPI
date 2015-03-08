/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage.Color;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;

public class CommandAbout extends CommandBase {

	public CommandAbout() {
		super("about", "");
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length != 1) {
			sender.addChatMessage(new ChatMessage("<SquidAPI> Wrong syntax.").setColor(Color.RED));
		}
		ModContainer mod = Utils.getMod(args[0]);
		ModMetadata meta = mod.getMetadata();
		try {
			String modid = mod.getModId();
			String description = meta.description;
			String version = mod.getVersion();
			String authors = mod.getMetadata().getAuthorList();
			String url = meta.url;
			
			sender.addChatMessage(new ChatMessage(modid).setBold());
			
			if (!description.isEmpty()) {
				sender.addChatMessage(new ChatMessage(Utils.newString("Description: ", description)));
			}
			if (!version.isEmpty()) {
				sender.addChatMessage(new ChatMessage(Utils.newString("Version: ", version)));
			}
			if (!authors.isEmpty()) {
				sender.addChatMessage(new ChatMessage(Utils.newString("Authors: ", authors)));
			}
			if (!url.isEmpty()) {
				sender.addChatMessage(new ChatMessage("URL: ").appendSibling(new ChatMessage(url).setUrl(url)));
			}
		} catch (NullPointerException e) {
			sender.addChatMessage(new ChatMessage("Mod not found.").setColor(EnumChatFormatting.RED));
		}
	}
	
	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args) {
		ArrayList<String> modids = new ArrayList<String>();
		modids.add("Minecraft");
		for (String modid: Loader.instance().getIndexedModList().keySet()) {
			modids.add(modid);
		}
		return modids;
	}
}