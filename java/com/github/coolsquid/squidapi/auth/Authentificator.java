/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.auth;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import com.github.coolsquid.squidapi.SquidAPI;
import com.github.coolsquid.squidapi.helpers.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;

public class Authentificator extends Thread {

	@Override
	public void run() {
		boolean isOffline = false;
		try {
			URL url = new URL("https://google.com");
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(10000);
			connection.connect();
		} catch (SocketTimeoutException e) {
			isOffline = true;
		} catch (UnknownHostException e) {
			isOffline = true;
		} catch (IOException e) {
			
		}
		if (isOffline) {
			return;
		}
		for (int a = 0; a < SquidAPIAuthentificationHelper.modstocheck.size(); a++) {
			AuthEntry entry = (AuthEntry) SquidAPIAuthentificationHelper.modstocheck.get(a);
			auth(entry);
		}
		init();
	}
	
	private static void auth(AuthEntry entry) {
		File authFile = new File("./config/auth/" + entry.getModid() + ".auth");
		try {
			if (authFile.exists()) {
				BufferedReader r2 = new BufferedReader(new InputStreamReader(new FileInputStream(authFile)));
				String s = r2.readLine();
				if (s != null && s.equals(entry.getVersion())) {
					r2.close();
					return;
				}
				r2.close();
			}
			
			URL url = new URL(entry.getNewVersionUrl());
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			InputStream input = connection.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(input));
			String s = r.readLine();
			if (entry.getVersion().equals(s)) {
				File folder = new File("./config/auth/");
				folder.mkdirs();
				authFile.createNewFile();
				
				BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authFile)));
				w.write(entry.getVersion());
				w.close();
			}
			else {
				if (authFile.exists()) {
					authFile.delete();
				}
				SquidAPIAuthentificationHelper.unauthorisedmods.add(entry.getModid());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final String n = System.getProperty("line.separator");
	
	private static void init() {
		if (SquidAPI.isLocked()) {
			Font font = new Font("Dialog", Font.BOLD, 20);
			JFrame frame = new JFrame();
			JTextPane text = new JTextPane();
			text.setFont(font);
			String warning = "SquidAPI has detected mods downloaded through an illegal website." + n + "To fix this error, download the **latest** official version from: http://coolsquid.wix.com/software#!downloads/cppf." + n + n + "Unauthorised mods:";
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				warning = warning + n + SquidAPIAuthentificationHelper.unauthorisedmods.get(a);
			}
			warning = warning + n + n + "Proudly supporting #StopModReposts!" + n + "http://stopmodreposts.org/";
			warning = warning + n + n + "Do you believe this is an error? Report it at:" + n + "https://github.com/coolsquid/SquidAPI/issues.";
			text.setText(warning);
			text.setVisible(true);
			text.setEditable(false);
			frame.setTitle("ILLEGAL MOD DISTRIBUTION DETECTED!");
			frame.setSize(800, 600);
			frame.add(text);
			frame.setVisible(true);
			FMLCommonHandler.instance().bus().register(new SquidAPI());
			LogHelper.warn("**********************************************************************************************************************************************************************");
			LogHelper.warn("SquidAPI has detected mods downloaded through an illegal website.");
			LogHelper.warn("To fix this error, download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			LogHelper.warn("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				LogHelper.warn((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			LogHelper.warn("**********************************************************************************************************************************************************************");
			SquidAPI.logger.log("**********************************************************************************************************************************************************************");
			SquidAPI.logger.log("SquidAPI has detected mods downloaded through an illegal website.");
			SquidAPI.logger.log("To fix this error, download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			SquidAPI.logger.log("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				SquidAPI.logger.log((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			SquidAPI.logger.log("**********************************************************************************************************************************************************************");
		}
	}
}