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
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public static boolean dateMatches(int date, int month) {
		return getDate() == date && getMonth() == month;
	}
}