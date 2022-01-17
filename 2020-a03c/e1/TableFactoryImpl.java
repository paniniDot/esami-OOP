package e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TableFactoryImpl implements TableFactory {

	@Override
	public <R, C, V> Table<R, C, V> fromMap(Map<Pair<R, C>, V> map) {
		return new Table<>() {

			private final Map<Pair<R, C>, V> table = map;
			private final Set<R> rows = this.rows();
			private final Set<C> columns= this.columns();
			
			
			@Override
			public Set<R> rows() {
				return table.keySet().stream().map(Pair::getX).collect(Collectors.toSet());
			}

			@Override
			public Set<C> columns() {
				return table.keySet().stream().map(Pair::getY).collect(Collectors.toSet());
			}

			@Override
			public Map<C, Map<R, V>> asColumnMap() {
				return this.columns.stream().collect(
						Collectors.toMap(col -> col, 
								col -> this.rows.stream()
								.map(row -> new Pair<>(row, this.getValue(row, col)))
								.filter(val -> val.getY().isPresent())
								.collect(Collectors.toMap(val -> val.getX(), val -> val.getY().get()))));
			}

			@Override
			public Map<R, Map<C, V>> asRowMap() {
				return this.rows.stream().collect(
						Collectors.toMap(r->r, 
								r-> this.columns.stream().
								map(c->new Pair<>(c,this.getValue(r,c))).
								filter(p->p.getY().isPresent()).
								collect(Collectors.toMap(p->p.getX(), p->p.getY().get()))));
			}

			@Override
			public Optional<V> getValue(R row, C column) {
				return Optional.ofNullable(this.table.get(new Pair<>(row, column)));
			}

			@Override
			public void putValue(R row, C column, V value) {
				this.table.put(new Pair<>(row, column), value);
			}
			
		};
	}

	@Override
	public <R, C, V> Table<R, C, V> fromFunction(Set<R> rows, Set<C> columns, BiFunction<R, C, V> valueFunction) {
		return new Table<>() {

			private final Map<Pair<R, C>, V> map = rows
					.stream()
					.flatMap(r -> columns.stream()
							.map(c -> new Pair<>(r, c)))
					.collect(Collectors.toMap(p -> p, p -> valueFunction.apply(p.getX(), p.getY())));
			
			private final Set<R> raws = this.rows();
			private final Set<C> cols = this.columns();
			
			@Override
			public Set<R> rows() {
				return this.map.keySet().stream().map(Pair::getX).collect(Collectors.toSet());
			}

			@Override
			public Set<C> columns() {
				return this.map.keySet().stream().map(Pair::getY).collect(Collectors.toSet());
			}

			@Override
			public Map<C, Map<R, V>> asColumnMap() {
				return this.cols.stream().collect(
						Collectors.toMap(col -> col, 
								col -> this.raws.stream()
								.map(row -> new Pair<>(row, this.getValue(row, col)))
								.filter(val -> val.getY().isPresent())
								.collect(Collectors.toMap(val -> val.getX(), val -> val.getY().get()))));
			}

			@Override
			public Map<R, Map<C, V>> asRowMap() {
				return this.raws.stream().collect(
						Collectors.toMap(r->r, 
								r-> this.cols.stream().
								map(c->new Pair<>(c,this.getValue(r,c))).
								filter(p->p.getY().isPresent())
								.collect(Collectors.toMap(p->p.getX(), p->p.getY().get()))));
			}

			@Override
			public Optional<V> getValue(R row, C column) {
				return Optional.ofNullable(this.map.get(new Pair<>(row, column)));
			}

			@Override
			public void putValue(R row, C column, V value) {
				this.map.put(new Pair<>(row, column), value);
			}
			
		};
	}

	@Override
	public <G> Table<G, G, Boolean> graph(Set<Pair<G, G>> edges) {
		return new Table<>() {
			
			private Map<Pair<G,G>, Boolean> table = edges.stream().collect(Collectors.toMap(edges -> edges, edges -> true));
			private Set<G> rows = this.rows();
			private Set<G> columns = this.columns();
			
			@Override
			public Set<G> rows() {
				return this.table.keySet().stream().map(Pair::getX).collect(Collectors.toSet());
			}

			@Override
			public Set<G> columns() {
				return this.table.keySet().stream().map(Pair::getY).collect(Collectors.toSet());
			}

			@Override
			public Map<G, Map<G, Boolean>> asColumnMap() {
				return this.rows.stream().collect(
						Collectors.toMap(r->r, 
								r-> this.columns.stream().
								map(c->new Pair<>(c,this.getValue(r,c))).
								filter(p->p.getY().isPresent())
								.collect(Collectors.toMap(p->p.getX(), p->p.getY().get()))));
			}

			@Override
			public Map<G, Map<G, Boolean>> asRowMap() {
				return this.columns.stream().collect(
						Collectors.toMap(c->c, 
								c -> this.rows.stream()
								.map(r -> new Pair<>(r, this.getValue(r, c)))
								.filter(p -> p.getY().isPresent())
								.collect(Collectors.toMap(p->p.getX(), p->p.getY().get()))));
			}

			@Override
			public Optional<Boolean> getValue(G row, G column) {
				return this.table.get(new Pair<>(row, column)) == null ? Optional.of(false) : Optional.of(this.table.get(new Pair<>(row, column)));
			}

			@Override
			public void putValue(G row, G column, Boolean value) {
				this.table.put(new Pair<>(row, column), value);
			}
			
		};
	}

	@Override
	public <V> Table<Integer, Integer, V> squareMatrix(V[][] values) {
		// TODO Auto-generated method stub
		return null;
	}

}
