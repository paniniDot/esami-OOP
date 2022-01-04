package e1;

public class LuminousDeviceImpl extends AbstractDevice implements LuminousDevice {
	
	private int currentIntensity = 0;
	private final int maxIntensity;
	private boolean isWorking = true;
	
	public LuminousDeviceImpl(final int maxIntensity) {
		this.maxIntensity = maxIntensity;
	}
	
	@Override
    public void onOn() {
        if (isWorking == true) {
        	this.setIsOn(true);
            if (currentIntensity >= maxIntensity) {
                isWorking = false;
                this.setIsOn(false);
            }
        }
    }
	
	@Override
	public boolean onWorking() {
		return this.isWorking;
	}
	
	@Override
	public void dim() {
		this.currentIntensity--;
	}
	
	@Override
    public void brighten() {
        currentIntensity++;
        if (currentIntensity >= maxIntensity && super.isOn()) {
            isWorking = false;
            this.setIsOn(false);
        }
    }

    @Override
    public int getIntesity() {
        return this.currentIntensity;
    }
    
}
