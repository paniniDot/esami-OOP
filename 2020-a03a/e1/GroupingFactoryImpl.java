package e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GroupingFactoryImpl implements GroupingFactory {

	@Override
	public <G, V> Grouping<G, V> fromPairs(Iterable<Pair<G, V>> values) {
		
		final Set<Pair<G,Set<V>>> groups = new HashSet<>();
		for(var value: values) {
			var optSet = groups.stream().filter(g -> g.getX().equals(value.getX())).findAny();
			if(optSet.isEmpty()) {
				groups.add(new Pair<>(value.getX(), new HashSet<>(Set.of(value.getY()))));
			} else {
				optSet.get().getY().add(value.getY());
			}
		}
		return new Grouping<>() {
			
			@Override
			public Set<V> getValuesOfGroup(G group) {
				return groups.stream().filter(g -> g.getX().equals(group)).map(g -> g.getY()).findAny().orElse(Collections.emptySet());
			}

			@Override
			public Set<G> getGroups() {
				return groups.stream().map(g -> g.getX()).collect(Collectors.toSet());
			}

			@Override
			public Optional<G> getGroupOf(V data) {
				return groups.stream().filter(g -> g.getY().contains(data)).map(g -> g.getX()).findAny();
			}

			@Override
			public Map<G, Set<V>> asMap() {
				return groups.stream().collect(Collectors.toMap(Pair::getX, Pair::getY));
			}

			@Override
			public Grouping<G, V> combineGroups(G initial1, G initial2, G result) {
				return null;
			}
		};
	}

	@Override
	public <V> Grouping<V, V> singletons(Set<V> values) {
		return this.fromPairs(values.stream().map(v -> new Pair<>(v, v)).collect(Collectors.toList()));
	}

	@Override
	public <V> Grouping<V, V> withChampion(Set<V> values, BiPredicate<V, V> sameGroup, Predicate<V> champion) {
		List<Pair<V, V>> list = new ArrayList<>();
		for(var value: values) {
			if(champion.test(value)) {
				list.add(new Pair<>(value, value));
			}
		}
		for(var value: values) {
			var optPair = list.stream().filter(p -> sameGroup.test(p.getX(), value)).findAny();
			if(optPair.isEmpty()) {
				list.add(new Pair<>(value, value));
			} else {
				list.add(new Pair<>(optPair.get().getX(), value));
			}
		}
		return this.fromPairs(list);
	}

	@Override
	public <G, V> Grouping<G, V> fromFunction(Set<V> values, Function<V, G> mapper) {
		return this.fromPairs(values.stream().map(v -> new Pair<>(mapper.apply(v), v)).collect(Collectors.toList()));
	}

}
