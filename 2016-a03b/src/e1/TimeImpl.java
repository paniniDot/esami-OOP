package e1;

import java.util.Objects;

public class TimeImpl implements Time {
	
	private int hours;
	private int minutes;
	private int seconds;
	
	public TimeImpl(final int hours, final int minutes, final int seconds) {
		this.throwIllegalArgumentExceptionIf(!TimeUtils.legalHours(hours) || !TimeUtils.legalMinutes(minutes) || !TimeUtils.legalSeconds(seconds));
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	private void throwIllegalArgumentExceptionIf(boolean condition) {
		if(condition) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public int getHours() {
		return this.hours;
	}

	@Override
	public int getMinutes() {
		return this.minutes;
	}

	@Override
	public int getSeconds() {
		return this.seconds;
	}

	@Override
	public String getLabel24() {
		return String.format("%02d", this.hours) + ":" + String.format("%02d", this.minutes) + ":" + String.format("%02d", this.seconds);
	}

	@Override
	public int getSecondsFromMidnight() {
		return this.seconds + this.minutes * TimeUtils.SEC_PER_MINUTE + this.hours * TimeUtils.SEC_PER_HOUR;
	}

	@Override
	public String toString() {
		return this.getLabel24();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hours, minutes, seconds);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TimeImpl other = (TimeImpl) obj;
		return this.hours == other.hours && this.minutes == other.minutes && this.seconds == other.seconds;
	}

	
	
}
