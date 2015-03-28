/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Iterator;

public class MathUtils {
	
	public static class Counter implements Iterator<Integer>, Iterable<Integer> {
		
		private int a = 0;
		private int b = 1;
		
		public Counter() {}
		
		public Counter(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public Integer next() {
			return this.a += this.b;
		}
		
		public Integer last() {
			return this.a -= this.b;
		}
		
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Iterator<Integer> iterator() {
			return this;
		}
		
		public static Counter newCounter() {
			return new Counter();
		}
	}
}