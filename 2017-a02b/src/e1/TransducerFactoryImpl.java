package e1;

public class TransducerFactoryImpl implements TransducerFactory {

	@Override
	public <X> Transducer<X, String> makeConcatenator(int inputSize) {
		return new TransducerImpl<X, String>(inputSize) {

			@Override
			protected String onGetOutputElement() {
				String output="";
				for(int i=0; i<super.getLeft(); i++) {
					output+=super.getInputList().remove(0);
				}
				return output;
			}
			
		};
	}

	@Override
	public Transducer<Integer, Integer> makePairSummer() {
		return new TransducerImpl<Integer, Integer>(2) {

			@Override
			protected Integer onGetOutputElement() {
				int output = 0;
				for(int i=0; i<super.getLeft(); i++) {
					output+=super.getInputList().remove(0);
				}
				return output;
			}
		
		};
	}

}
