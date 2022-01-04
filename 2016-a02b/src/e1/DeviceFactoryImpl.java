package e1;

public class DeviceFactoryImpl implements DeviceFactory {
	
	@Override
	public Device createDeviceNeverFailing() {
		return new AbstractDevice() {

			@Override
			protected void onOn() {
				super.setIsOn(true);		
			}

			@Override
			protected boolean onWorking() {
				return true;
			}
			
		};
	}

	@Override
	public Device createDeviceFailingAtSwitchOn(int count) {
		return new AbstractDevice() {
			
			private int left = count;
			
			@Override
			protected void onOn() {
				if(this.left > 1) {
					this.setIsOn(true);
				}
				this.left--;
			}

			@Override
			protected boolean onWorking() {
				return this.left > 0;
			}

			
		};
	}

	@Override
	public LuminousDevice createLuminousDeviceFailingAtMaxIntensity(int maxIntensity) {
		return new LuminousDeviceImpl(maxIntensity);
	}

}
