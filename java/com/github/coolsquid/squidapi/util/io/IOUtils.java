/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import scala.actors.threadpool.Arrays;

import com.google.common.collect.Lists;

public class IOUtils {

	public static FileInputStream newInputStream(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static String readLine(BufferedReader reader) {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public static BufferedReader newReader(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream));
	}

	public static String getLine(BufferedReader reader, String key) {
		while (true) {
			String b = readLine(reader);
			if (b == null) {
				break;
			}
			else if (b.contains(key)) {
				return b;
			}
		}
		return key;
	}

	public static FileOutputStream newOutputStream(File file) {
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static BufferedWriter newWriter(OutputStream stream) {
		return new BufferedWriter(new OutputStreamWriter(stream));
	}

	public static void writeLines(File file, Iterable<String> lines) {
		if (file == null || !FileFilters.TEXT_FILES.accept(file)) {
			throw new IllegalArgumentException();
		}
		BufferedWriter a = newWriter(newOutputStream(file));
		try {
			for (String b: lines) {
				a.write(b);
				a.newLine();
			}
			a.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeLines(File file, Object... lines) {
		if (file == null || !FileFilters.TEXT_FILES.accept(file)) {
			throw new IllegalArgumentException();
		}
		BufferedWriter a = newWriter(newOutputStream(file));
		try {
			for (Object b: lines) {
				a.write(b.toString());
				a.newLine();
			}
			a.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> readLines(File file) {
		if (file == null || !FileFilters.TEXT_FILES.accept(file)) {
			throw new IllegalArgumentException();
		}
		List<String> a = Lists.newArrayList();
		for (String b: newReader(file)) {
			a.add(b);
		}
		return a;
	}

	public static List<String> readAllFilesInFolder(File folder) {
		if (folder == null || !folder.isDirectory()) {
			throw new IllegalArgumentException();
		}
		List<String> list = Lists.newArrayList();
		for (File file: folder.listFiles()) {
			for (String a: new FileReader(file)) {
				list.add(a);
			}
		}
		return list;
	}

	public static int hash(File file) {
		int result = 0;
		for (String a: newReader(file)) {
			for (char b: a.toCharArray()) {
				result += (b * 31);
			}
			result += (a.length() * 31);
			result += 31;
		}
		return result;
	}
	
	public static byte[] convertToBytes(File file) {
		byte[] result = new byte[256];
		int counter = 0;
		for (String a: newReader(file)) {
			for (byte b: a.getBytes()) {
				try {
					result[counter++] = b;
				} catch (Exception e) {
					result = Arrays.copyOf(result, result.length + 256);
				}
			}
		}
		return result;
	}
	
	public static void copy(File file, File newfile) {
		if (file == null || newfile == null || !file.exists() || newfile.exists()) {
			throw new IllegalArgumentException();
		}
		
		InputStream a = newInputStream(file);
		OutputStream b = newOutputStream(newfile);
		
		int c;
		try {
			c = a.read();
			while (c != -1) {
				b.write(c);
				c = a.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileReader newReader(File file) {
		return new FileReader(file);
	}

	public static class FileReader implements Iterable<String> {

		private final File file;

		public FileReader(File file) {
			if (file == null || !FileFilters.TEXT_FILES.accept(file)) {
				throw new IllegalArgumentException();
			}
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.file = file;
		}

		@Override
		public Iterator<String> iterator() {
			return new FileIterator();
		}

		private class FileIterator implements Iterator<String> {

			private final BufferedReader a = IOUtils.newReader(IOUtils.newInputStream(FileReader.this.file));
			private String line;

			@Override
			public boolean hasNext() {
				this.line = IOUtils.readLine(this.a);
				return this.line != null;
			}

			@Override
			public String next() {
				return this.line;
			}
		}
	}
}