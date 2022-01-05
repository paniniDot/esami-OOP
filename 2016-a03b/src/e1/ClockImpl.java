package e1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClockImpl implements Clock {
	
	private Time time;
	private Map<Time,List<Runnable>> observers = new HashMap<>();
	
	public ClockImpl(Time time) {
		this.time = time;
	}
	
	@Override
	public Time getTime() {
		return this.time;
	}
	
	@Override
	public void tick() {	
		if(this.time.getHours() == TimeUtils.LAST_HOUR && this.time.getMinutes() == TimeUtils.LAST_MIN && this.time.getSeconds() == TimeUtils.LAST_SEC) {
			this.time = new TimeImpl(0, 0, 0);
		} else if(this.time.getMinutes() == TimeUtils.LAST_MIN && this.time.getSeconds() == TimeUtils.LAST_SEC) {
			this.time = new TimeImpl(this.time.getHours()+1, 0, 0);
		} else if(this.time.getSeconds() == TimeUtils.LAST_SEC) {
			this.time = new TimeImpl(this.time.getHours(), this.time.getMinutes()+1, 0);
		} else {
			this.time = new TimeImpl(this.time.getHours(), this.time.getMinutes(), this.time.getSeconds()+1);
		}
		this.notifyObservers();
	}
	
	private void notifyObservers(){
        this.observers.getOrDefault(time, new LinkedList<>()).forEach(Runnable::run);
    }
	
	@Override
    public void registerAlarmObserver(Time time, Runnable observer) {
        final List<Runnable> oneObserver = new LinkedList<>(Arrays.asList(observer));
        this.observers.merge(time, oneObserver, (v,v1)->{v.addAll(v1); return v;});
    }
	
	@Override
	public void registerHoursDeadlineObserver(int hours, Runnable observer) {
		//this.registerAlarmObserver(, observer);
	}

	@Override
	public void registerMinutesDeadlineObserver(int minutes, Runnable observer) {
		//this.registerGenericObserver(observer, this.time.getMinutes() == minutes);
	}

	@Override
	public void registerSecondsDeadlineObserver(int seconds, Runnable observer) {
		//this.registerGenericObserver(observer, this.time.getSeconds() == seconds);
	}

}
