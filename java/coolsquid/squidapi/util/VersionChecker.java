/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.io.WebUtils;

public class VersionChecker {

	public static final VersionChecker INSTANCE = new VersionChecker();
	public final Set<SquidAPIMod> outdatedMods = Sets.newHashSet();

	private VersionChecker() {
		
	}

	public void check(SquidAPIMod mod) {
		if (mod.getCurseUrl() != null && MiscLib.updateChecker()) {
			new Thread(new VersionCheckHandler(mod)).start();
		}
	}

	private class VersionCheckHandler implements Runnable {
		
		private final SquidAPIMod mod;

		public VersionCheckHandler(SquidAPIMod mod) {
			this.mod = mod;
		}

		@Override
		public void run() {
			try {
				String a = WebUtils.getLine(this.mod.getCurseUrl(), ".jar</a>");
				String b = a.split(this.mod.getModid() + "-")[1].replace(".jar</a>", "");
				if (!this.mod.getVersion().equals(b)) {
					VersionChecker.this.outdatedMods.add(this.mod);
					this.mod.info(b);
				}
			} catch (Exception e) {
				SquidAPI.instance().error("Version checking failed!");
			}
		}
	}

	public void onLogin(EntityPlayer player) {
		for (SquidAPIMod mod: this.outdatedMods) {
			player.addChatMessage(new ChatMessage("The following mod is outdated: " + mod.getName()).setColor(EnumChatFormatting.RED));
			player.addChatMessage(new ChatMessage("Download the latest version here:"));
			String url = "http://www.curse.com/mc-mods/minecraft/" + mod.getCurseId() + "-" + mod.getName();
			player.addChatMessage(new ChatMessage(url).setUrl(WebUtils.newURL(url)).setColor(EnumChatFormatting.BLUE));
		}
	}
}