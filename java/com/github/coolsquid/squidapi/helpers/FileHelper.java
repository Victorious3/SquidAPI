/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileHelper {
	
	public static ArrayList<String> readFile(String location, String filename) {
		File folder = new File(location);
		folder.mkdirs();
		File file = new File("./" + folder + "/" + filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (true) {
				String s = r.readLine();
				if (s == null) {
					break;
				}
				list.add(s);
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void writeFile(String location, String filename, Iterable<String> lines) {
		File folder = new File(location);
		folder.mkdirs();
		File file = new File("./" + folder + "/" + filename);
		
		try {
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			for (String line: lines) {
				w.write(line);
				w.newLine();
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}