/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.handlers.ModEventHandler;
import coolsquid.squidapi.handlers.command.NewsHandler;
import coolsquid.squidapi.helpers.server.ServerHelper;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.Utils;
import coolsquid.squidapi.util.io.IOUtils;

public class CommandSquidAPI extends CommandBase {

	public static final CommandSquidAPI INSTANCE = new CommandSquidAPI();

	public CommandSquidAPI() {
		super("SquidAPI", "", false);
	}

	private void sendMsg(ICommandSender sender, String msg) {
		sender.addChatMessage(new ChatComponentText("�4<SquidAPI>�r " + msg));
	}

	private void sendHelp(ICommandSender sender, String msg) {
		this.sendMsg(sender, "/" + this.getCommandName() + " " + msg);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		super.processCommand(sender, args);
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
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
				else {
					a = false;
				}
				if (a) {
					SquidAPI.instance().info("The requested information was dumped into a file in /dumps.");
				}
			}
			else if (subcommand.equals("togglesuperspeed")) {
				ModEventHandler.speedy = !ModEventHandler.speedy;
			}
			else if (subcommand.equals("getuuid")) {
				SquidAPI.instance().info("UUID: " + player.getGameProfile().getId().toString());
				sender.addChatMessage(new ChatMessage("<SquidAPI> Successfully printed your UUID to the log!"));
			}
		}
	}
}