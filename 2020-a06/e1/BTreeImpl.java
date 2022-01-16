package e1;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class BTreeImpl<L> implements BTree<L> {
	
	private final BTree<L> left;
	private final BTree<L> right;
	
	public BTreeImpl(final BTree<L> left, final BTree<L> right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public L getLeaf() {
		throw new NoSuchElementException();
	}

	@Override
	public BTree<L> getLeft() {
		return this.left;
	}

	@Override
	public BTree<L> getRight() {
		return this.right;
	}

	@Override
	public L compute(BinaryOperator<L> function) {
		return function.apply(this.left.compute(function), this.right.compute(function));
	}

	@Override
	public <O> BTree<O> map(Function<L, O> mapper) {
		return new BTreeImpl<>(this.left.map(mapper), this.right.map(mapper));
	}

	@Override
	public BTree<L> symmetric() {
		return new BTreeImpl<>(this.right.symmetric(), this.left.symmetric());
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BTreeImpl other = (BTreeImpl) obj;
		return Objects.equals(left, other.left) && Objects.equals(right, other.right);
	}

	
	
}
