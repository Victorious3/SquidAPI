/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util.io;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

public class WebUtils {
	
	public static URL newURL(String url) {
		if (!url.contains("://")) {
			url = "http://" + url;
		}
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	public static URLConnection newConnection(String url) {
		try {
			return newURL(url).openConnection();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static URLConnection newConnection(URL url) {
		try {
			return url.openConnection();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static InputStream getStream(String url) {
		try {
			return newConnection(url).getInputStream();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static InputStream getStream(URL url) {
		try {
			return newConnection(url).getInputStream();
		} catch (IOException e) {
			return null;
		}
	}

	public static List<String> readWebsite(String url) {
		return readWebsite(newURL(url));
	}

	public static List<String> readWebsite(URL url) {
		List<String> list = Lists.newArrayList();
		for (String a: new URLReader(url)) {
			list.add(a);
		}
		return list;
	}
	
	public static void connect(String url) {
		connect(newURL(url));
	}
	
	public static void connect(URL url) {
		try {
			newConnection(url).connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getLine(String url, String key) {
		return getLine(newURL(url), key);
	}
	
	public static String getLine(URL url, String key) {
		return IOUtils.getLine(IOUtils.newReader(getStream(url)), key);
	}

	public static String getLine(String url, int index) {
		return getLine(newURL(url), index);
	}

	public static String getLine(URL url, int index) {
		int a = 0;
		for (String b: new URLReader(url)) {
			if (a == index) {
				return b;
			}
			a++;
		}
		return null;
	}

	public static URLReader newReader(String url) {
		return new URLReader(newURL(url));
	}

	public static URLReader newReader(URL url) {
		return new URLReader(url);
	}
	
	public static void openBrowser(String url) {
		openBrowser(newURL(url));
	}
	
	public static void openBrowser(URL url) {
		try {
			Desktop.getDesktop().browse(url.toURI());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static class URLReader implements Iterable<String> {

		private final URL url;

		public URLReader(String url) {
			this.url = WebUtils.newURL(url);
		}

		public URLReader(URL url) {
			this.url = url;
		}

		@Override
		public Iterator<String> iterator() {
			return new SiteIterator();
		}

		private class SiteIterator implements Iterator<String> {

			private final BufferedReader a = IOUtils.newReader(WebUtils.getStream(URLReader.this.url));
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