/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Calendar;


public class DateUtils {

	public static int getDate() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getWeek() {
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static boolean dateMatches(int date, int month) {
		return getDate() == date && getMonth() == month;
	}

	public static boolean dateRangeMatches(Date startDate, Date endDate) {
		int date = getDate();
		int month = getMonth();
		return month > startDate.getMonth() && month < endDate.getMonth() && date > startDate.getDate() && date < endDate.getDate();
	}

	public static class Date {

		private final int date;
		private final int month;
		private int year;

		public Date(int date, int month) {
			this.date = date;
			this.month = month;
		}

		public Date(int date, int month, int year) {
			this.date = date;
			this.month = month;
			this.year = year;
		}

		public int getDate() {
			return this.date;
		}

		public int getMonth() {
			return this.month;
		}

		public int getYear() {
			return this.year;
		}
	}
}