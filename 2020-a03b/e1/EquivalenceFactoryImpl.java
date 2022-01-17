package e1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EquivalenceFactoryImpl implements EquivalenceFactory {

	@Override
	public <X> Equivalence<X> fromPartition(Set<Set<X>> partition) {
		return new Equivalence<X>() {

			private final Set<Set<X>> domain = partition;
			
			@Override
			public boolean areEquivalent(X x1, X x2) {
				return this.domain.stream().filter(domain -> domain.contains(x1) && domain.contains(x2)).findAny().isPresent();
			}

			@Override
			public Set<X> domain() {
				return this.domain.stream().flatMap(partition -> partition.stream()).collect(Collectors.toSet());
			}

			@Override
			public Set<X> equivalenceSet(X x) {
				return this.domain.stream().filter(partition -> partition.contains(x)).findAny().orElse(Collections.emptySet());
			}

			@Override
			public Set<Set<X>> partition() {
				return this.domain;
			}

			@Override
			public boolean smallerThan(Equivalence<X> eq) {
				// TODO Auto-generated method stub
				return false;
			}
	
		};
	}

	@Override
	public <X> Equivalence<X> fromPredicate(Set<X> domain, BiPredicate<X, X> predicate) {
		Set<Set<X>> sets = new HashSet<>();
		for(X el: domain) {
			var set = sets.stream().filter(s -> predicate.test(s.iterator().next(), el)).findAny();
			if(set.isEmpty()) {
				sets.add(new HashSet<>(Set.of(el)));
			} else {
				set.get().add(el);
			}
		}
		return this.fromPartition(sets);
	}

	@Override
	public <X> Equivalence<X> fromPairs(Set<Pair<X, X>> pairs) {
		Set<Set<X>> sets = new HashSet<>();
		for(var pair: pairs) {
			var set = sets.stream().filter(s -> s.contains(pair.getX()) || s.contains(pair.getY())).findAny();
			if(set.isEmpty()) {
				sets.add(new HashSet<>(Set.of(pair.getX(), pair.getY())));
			} else {
				set.get().add(pair.getX());
				set.get().add(pair.getY());
			}
		}
		System.out.println(sets);
		return this.fromPartition(sets);
	}

	public <X, Y> Equivalence<X> fromFunction(Set<X> domain, Function<X, Y> function) {
		Set<Set<X>> sets = new HashSet<>();
		for(var x: domain) {
			var set = sets.stream().filter(s -> function.apply(s.iterator().next()).equals(function.apply(x))).findAny();
			if(set.isEmpty()) {
				sets.add(new HashSet<>(Set.of(x)));
			} else {
				set.get().add(x);
			}
		}
		return this.fromPartition(sets);
	}

}
