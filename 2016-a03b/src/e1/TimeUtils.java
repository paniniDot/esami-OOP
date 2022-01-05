package e1;

public class TimeUtils {
	
	public final static int SEC_PER_HOUR = 3600;
	public final static int SEC_PER_MINUTE = 60;
	public final static int LAST_HOUR = 23;
	public final static int LAST_MIN = 59;
	public final static int LAST_SEC = 59;
	
	public static boolean legalHours(int hours) {
		return hours <= LAST_HOUR && hours >= 0;
	}
	
	public static boolean legalMinutes(int minutes) {
		return minutes <= LAST_MIN && minutes >= 0;
	}
	
	public static boolean legalSeconds(int seconds) {
		return seconds <= LAST_SEC && seconds >= 0;
	}
	
	
}
