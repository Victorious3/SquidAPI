/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;

import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.command.NewsHandler;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.helpers.server.ServerHelper;
import com.github.coolsquid.squidapi.registry.DamageSourceRegistry;
import com.github.coolsquid.squidapi.registry.WorldTypeRegistry;
import com.github.coolsquid.squidapi.util.Utils;
import com.github.coolsquid.squidapi.util.io.IOUtils;

public class CommandSquidAPI extends CommandBase {

	public CommandSquidAPI() {
		super("SquidAPI", "", false);
	}
	
	private void sendMsg(ICommandSender sender, String msg) {
		sender.addChatMessage(new ChatComponentText("§4<SquidAPI>§r " + msg));
	}
	
	private void sendHelp(ICommandSender sender, String msg) {
		this.sendMsg(sender, "/" + this.getCommandName() + " " + msg);
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		String subcommand = args[0];
		if (subcommand.equals("news")) {
			NewsHandler thread = new NewsHandler(sender);
			thread.start();
		}
		else if (subcommand.equals("help")) {
			this.sendMsg(sender, "Available commands:");
			this.sendHelp(sender, "help");
			this.sendHelp(sender, "news");
			this.sendHelp(sender, "dump items (modid)");
			this.sendHelp(sender, "dump blocks (modid)");
			this.sendHelp(sender, "dump commands");
			this.sendHelp(sender, "dump worldtypes");
			this.sendHelp(sender, "dump damagesources");
		}
		else if (subcommand.equals("dump")) {
			boolean a = true;
			if (args[1].equals("items")) {
				if (args.length > 2) {
					Utils.dump(args[2], new File("./dumps/items.txt"), Item.itemRegistry);
				}
				else {
					Utils.dump(null, new File("./dumps/items.txt"), Item.itemRegistry);
				}
			}
			else if (args[1].equals("blocks")) {
				if (args.length > 2) {
					Utils.dump(args[2], new File("./dumps/blocks.txt"), Block.blockRegistry);
				}
				else {
					Utils.dump(null, new File("./dumps/blocks.txt"), Block.blockRegistry);
				}
			}
			else if (args[1].equals("commands")) {
				IOUtils.writeLines(new File("./dumps/commands.txt"), ServerHelper.getCommands().keySet());
			}
			else if (args[1].equals("worldtypes")) {
				IOUtils.writeLines(new File("./dumps/worldtypes.txt"), WorldTypeRegistry.instance().names());
			}
			else if (args[1].equals("damagesources")) {
				IOUtils.writeLines(new File("./dumps/damagesources.txt"), DamageSourceRegistry.instance().names());
			}
			else {
				a = false;
			}
			if (a) {
				LogHelper.info("The requested information was dumped into a file in /dumps.");
			}
		}
		else if (subcommand.equals("togglesuperspeed")) {
			DevEnvironmentEventHandler.speedy = !DevEnvironmentEventHandler.speedy;
		}
		else {
			this.sendMsg(sender, "Type \"/" + this.getCommandName() + " help\" to recieve a list of subcommands.");
		}
	}
}