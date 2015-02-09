package com.github.coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import com.github.coolsquid.squidapi.handlers.command.NewsHandler;

public class CommandSquidAPI extends CommandBase {

	public CommandSquidAPI() {
		super("SquidAPI", "");
	}
	
	private void sendMsg(ICommandSender sender, String msg) {
		sender.addChatMessage(new ChatComponentText("§4<SquidAPI>§r " + msg));
	}
	
	private void sendHelp(ICommandSender sender, String msg) {
		sendMsg(sender, "/" + getCommandName() + " " + msg);
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
<<<<<<< HEAD
		if (args.length != 1) {
			sendMsg(sender, "Type \"/" + getCommandName() + " help\" to recieve a list of subcommands.");
			return;
		}
		String subcommand = args[0];
		if (subcommand.equals("news")) {
			NewsHandler thread = new NewsHandler(sender);
			thread.start();
		}
		else if (subcommand.equals("help")) {
			sendMsg(sender, "Available commands:");
			sendHelp(sender, "help");
			sendHelp(sender, "news");
=======
		String news = CommandNames.getString("news");
		String help = CommandNames.getString("help");
		if (args.length != 1) {
			sendMsg(sender, "Type \"/" + getCommandName() + " " + help + "\" to recieve a list of subcommands.");
			return;
		}
		String subcommand = args[0];
		if (subcommand.equals(news)) {
			NewsHandler thread = new NewsHandler(sender);
			thread.start();
		}
		else if (subcommand.equals(help)) {
			sendMsg(sender, "Available commands:");
			sendHelp(sender, help);
			sendHelp(sender, news);
>>>>>>> origin/master
		}
		else {
			sendMsg(sender, "Type \"/" + getCommandName() + " help\" to recieve a list of subcommands.");
		}
	}
}