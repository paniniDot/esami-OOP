package e2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private final static int MIN = 0;
	private final static int MAX = 99;
	
	private final List<Optional<Integer>> numbers;
	private int attempts;
	
	public ModelImpl(final int size) {
		this.numbers = IntStream.range(0, size)
				.mapToObj(i -> Optional.of((int)(MIN + (Math.random() * (MAX - MIN)))))
				.collect(Collectors.toList());
		this.attempts = 0;
	}
	
	private boolean isMin(final int number) {
		return number == this.numbers.stream()
				.flatMap(Optional::stream)
				.min(Integer::compareTo)
				.get();
	}
	
	@Override
	public boolean hit(final int position) {
		System.out.println("No. attempts = " + ++this.attempts);
		if(this.isMin(this.numbers.get(position).get())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getNumber(final int position) {
		int guessed = this.numbers.get(position).get();
		this.numbers.set(position, Optional.empty());
		return ""+guessed;
		
	}
	
}
