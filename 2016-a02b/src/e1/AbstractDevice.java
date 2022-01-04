package e1;

public abstract class AbstractDevice implements Device {

	private boolean isOn = false;
	
	@Override
	public void on() {
		this.onOn();
	}
	
	protected abstract void onOn();
	
	protected void setIsOn(boolean status) {
		this.isOn = status;
	}

	@Override
	public void off() {
		this.isOn = false;
	}

	@Override
	public boolean isOn() {
		return this.isOn;
	}

	@Override
	public boolean isWorking() {
		return this.onWorking();
	}

	protected abstract boolean onWorking();
	
}
