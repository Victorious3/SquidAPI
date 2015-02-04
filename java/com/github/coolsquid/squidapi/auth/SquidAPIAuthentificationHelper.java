/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.auth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.github.coolsquid.squidapi.registry.SimpleRegistry;

public class SquidAPIAuthentificationHelper {
	
	public static final SimpleRegistry unauthorisedmods = new SimpleRegistry();
	
	public static void auth(String modid, String version, String newversionurl) {
		File authFile = new File("./config/auth/" + modid + ".auth");
		try {
			if (authFile.exists()) {
				BufferedReader r2 = new BufferedReader(new InputStreamReader(new FileInputStream(authFile)));
				String s = r2.readLine();
				if (s != null && s.equals(version)) {
					r2.close();
					return;
				}
				r2.close();
			}
			
			URL url = new URL(newversionurl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			InputStream input = connection.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(input));
			String s = r.readLine();
			if (version.equals(s)) {
				File folder = new File("./config/auth/");
				folder.mkdirs();
				authFile.createNewFile();
				
				BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(authFile)));
				w.write(version);
				w.close();
			}
			else {
				if (authFile.exists()) {
					authFile.delete();
				}
				unauthorisedmods.register(modid);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}