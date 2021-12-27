package e1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q> {

	private final Map<Q, List<T>> map = new HashMap<>();
	
	@Override
	public Set<Q> availableQueues() {
		return this.map.keySet();
	}

	@Override
	public void openNewQueue(Q queue) {
		raiseException(this.map.containsKey(queue), () -> new IllegalArgumentException());
		this.map.put(queue, new LinkedList<>());
	}

	@Override
	public boolean isQueueEmpty(Q queue) {
		raiseException(!this.map.containsKey(queue), () -> new IllegalArgumentException());
		return this.map.get(queue).isEmpty();
	}

	@Override
	public void enqueue(T elem, Q queue) {
		raiseException(!this.map.containsKey(queue), () -> new IllegalArgumentException());
		this.map.get(queue).add(elem);
	}

	@Override
	public Optional<T> dequeue(Q queue) {
		raiseException(!this.map.containsKey(queue), () -> new IllegalArgumentException());
		var list = this.map.get(queue);
		if (list.isEmpty()){
            return Optional.empty();
        }
		return Optional.of(list.remove(0));
	}

	@Override
	public Map<Q, Optional<T>> dequeueOneFromAllQueues() {
		var map = new HashMap<Q, Optional<T>>();
		for(var entry: this.map.entrySet()) {
			map.put(entry.getKey(), this.dequeue(entry.getKey()));
		}
		return map;
	}

	@Override
	public Set<T> allEnqueuedElements() {
		return this.map.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public List<T> dequeueAllFromQueue(Q queue) {
		raiseException(!this.map.containsKey(queue), () -> new IllegalArgumentException());
		var l = this.map.get(queue);
		this.map.put(queue, new LinkedList<>());
		return l;
	}

	@Override
	public void closeQueueAndReallocate(Q queue) {
		raiseException(!this.map.containsKey(queue), () -> new IllegalArgumentException());
		raiseException(this.map.size() == 1, () -> new IllegalStateException());
		var dequeued = this.map.remove(queue);
		Q q = this.map.keySet().stream().findAny().get();
		dequeued.forEach(elem -> this.enqueue(elem, q));
	}

	private void raiseException(final boolean condition, Supplier<RuntimeException> supplier) {
		if(condition) {
			throw supplier.get();
		}
	}
	
}
