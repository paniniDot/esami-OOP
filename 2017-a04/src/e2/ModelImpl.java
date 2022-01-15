package e2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private static final int SIZE = 5;
	private static final int LOWER = 1;
	private static final int GREATER = 6;
	
	private final List<Optional<Integer>> numbers;
	
	public ModelImpl() {
		this.numbers = IntStream.rangeClosed(1, SIZE)
				.mapToObj(i -> Optional.of(this.getRandom(LOWER, GREATER)))
				.collect(Collectors.toList());
		System.out.println(this.numbers);
	}

	private int getRandom(int lower, int greater) {
		return LOWER + (int)(Math.random() * GREATER);
	}
	
	
	@Override
	public void hit(int pos) {
		this.numbers.set(pos, Optional.empty());
	}
	
	@Override
	public void draw() {
		this.numbers.stream()
			.filter(n -> n.equals(Optional.empty()))
			.forEach(n -> this.numbers.set(this.numbers.indexOf(n), Optional.of(this.getRandom(LOWER, GREATER))));
		this.result();
	}

	@Override
	public List<Integer> getStatus() {
		return this.numbers
				.stream()
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}
	
	private void result() {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<SIZE; i++) {
			int counter = 0;
			for(int j=0; j<SIZE; j++) {
				if(this.numbers.get(i).get() == this.numbers.get(j).get()) {
					counter++;
				}
			}
			map.put(this.numbers.get(i).get(), counter);
		}
		/* 
		 * In alternativa si poteva usare la funzione merge delle mappe che però è incomprensibile
		 * 1. Map<Integer, Integer> map = this.numbers.stream().flatMap(Optional::stream).collect(Collectors.toMap(x -> x, x -> 1, (x,y) -> x+y));
		 * 2. for(int i=0; i<SIZE; i++) {
		 *		map.merge(this.numbers.get(i).get(), 1, (x,y) -> x+y);
		 *    }
		 */	
		String res;
		if(map.size() == 5) {
			res = "STRAIGHT";
		} else if(map.size() == 1) {
			res = "YAHTZEE";
		} else if(map.size() == 2 && map.containsValue(4)) {
			res = "FOUR";
		} else if(map.size() == 2 && map.containsValue(3)) {
			res = "FULL";
		} else if(map.size() == 3 && map.containsValue(3)) {
			res = "THREE";
		} else {
			res = "NOTHING";
		}
		System.out.println(res + " con " + this.getStatus());
	}

	@Override
	public int getSize() {
		return ModelImpl.SIZE;
	}
	
}
