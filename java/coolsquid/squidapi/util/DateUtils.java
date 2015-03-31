package coolsquid.squidapi.util;

import com.ibm.icu.util.Calendar;

public class DateUtils {

	public static int getDate() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public static boolean dateMatches(int date, int month) {
		return getDate() == date && getMonth() == month;
	}
}