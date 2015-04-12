/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	private static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getDate() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getWeek() {
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	public static int getSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	public static Date getDateInstance() {
		return Calendar.getInstance().getTime();
	}

	public static boolean dateMatches(int date, int month) {
		return getDate() == date && getMonth() == month;
	}

	public static String getTime(String format) {
		StringBuilder time = StringUtils.builder();
		for (char c: format.toCharArray()) {
			if (c == 'h') {
				time.append(getHour());
			}
			else if (c == 'm') {
				time.append(getMinute());
			}
			else if (c == 's') {
				time.append(getSecond());
			}
			else if (c == 'd') {
				time.append(getDate());
			}
			else if (c == 'M') {
				time.append(getMonth());
			}
			else if (c == 'w') {
				time.append(getWeek());
			}
			else if (c == 'y') {
				time.append(getYear());
			}
			else {
				time.append(c);
			}
		}
		return time.toString();
	}
}