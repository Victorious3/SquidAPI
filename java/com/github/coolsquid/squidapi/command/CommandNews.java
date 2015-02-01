/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandNews extends CommandBase {

	@Override
	public String getCommandName() {
		return "news";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		try {
			URL url = new URL("http://pastebin.com/raw.php?i=z20CbwVE");
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			InputStream input = connection.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(input));
			
			sender.addChatMessage(new ChatComponentText("§4<" + r.readLine() + ">§r " + r.readLine()));
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException) {
				sender.addChatMessage(new ChatComponentText("§4<SquidAPI>§r Timed out."));
				return;
			}
			e.printStackTrace();
		}
	}
}