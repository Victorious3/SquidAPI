/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;


public class MathHelper {
	
	public static class Counter {
		private double a;
		private double b = 1;
		
		public Counter() {}
		
		public Counter(double a, double b) {
			this.a = a;
			this.b = b;
		}
		
		public double next() {
			return this.a += this.b;
		}
		
		public double last() {
			return this.a -= this.b;
		}
	}
}