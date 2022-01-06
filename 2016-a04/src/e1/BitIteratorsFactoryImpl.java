package e1;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BitIteratorsFactoryImpl implements BitIteratorsFactory {
	
	@Override
	public Iterator<Bit> empty() {
		return Collections.emptyIterator();
	}

	@Override
	public Iterator<Bit> zeros() {
		return Stream.generate(() -> Bit.ZERO).iterator();
	}

	@Override
	public Iterator<Bit> ones() {
		return Stream.generate(() -> Bit.ONE).iterator();
	}

	@Override
	public Iterator<Bit> fromByteStartingWithLSB(int b) {
		return new Iterator<Bit>() {
			
			private int count = 8;
			private int currentVal = b;
			
			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public Bit next() {
				if(!this.hasNext()) {
					throw new NoSuchElementException();
				}
				count--;
				int old = currentVal;
				currentVal /= 2;
				return (old % 2 == 1 ? Bit.ONE : Bit.ZERO); 
			}		
		};
	}
	
	@Override
	public Iterator<Bit> fromBitList(List<Bit> list) {
		return list.iterator();
	}

	@Override
	public Iterator<Bit> fromBooleanList(List<Boolean> list) {
		return list.stream().map(el -> el == true ? Bit.ONE : Bit.ZERO).iterator();
	}

}
