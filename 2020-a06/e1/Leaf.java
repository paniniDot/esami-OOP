package e1;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Leaf<L> implements BTree<L> {

	private L value;
	
	public Leaf(final L value) {
		this.value = value;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public L getLeaf() {
		return this.value;
	}

	@Override
	public BTree<L> getLeft() {
		throw new NoSuchElementException();
	}

	@Override
	public BTree<L> getRight() {
		throw new NoSuchElementException();
	}

	@Override
	public L compute(BinaryOperator<L> function) {
		return this.value;
	}

	@Override
	public <O> BTree<O> map(Function<L, O> mapper) {
		return new Leaf<>(mapper.apply(value));
	}

	@Override
	public BTree<L> symmetric() {
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leaf other = (Leaf) obj;
		return Objects.equals(value, other.value);
	}
	
}
