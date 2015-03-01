/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class WebHelper extends Thread {
	
	private String url;
	
	@Override
	public void run() {
		this.read();
	}
	
	private ArrayList<String> text;
	
	public ArrayList<String> getText() {
		return this.text;
	}

	public void read() {
		this.text = new ArrayList<String>();
		try {
			URL url = new URL(this.url);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			InputStream input = connection.getInputStream();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(input));
			
			while (true) {
				String s = r.readLine();
				if (s == null) break;
				this.text.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}