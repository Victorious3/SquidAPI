package com.github.coolsquid.squidapi.helpers;


public class MathHelper {
	
	public static class Counter {
		private int a;
		
		public Counter() {}
		
		public Counter(int a) {
			this.a = a;
		}
		
		public int next() {
			return this.a++;
		}
		
		public int last() {
			return this.a--;
		}
	}
}