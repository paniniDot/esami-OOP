package e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListSplitterFactoryImpl implements ListSplitterFactory {

	@Override
	public <X> ListSplitter<X> asPairs() {
		return new ListSplitter<X>() {

			@Override
			public List<List<X>> split(List<X> list) {
				List<List<X>> l = new ArrayList<>();
				for(int i=0; i<list.size(); i+=2) {
					if(i < list.size() - 1) {
						l.add(List.of(list.get(i), list.get(i+1)));
					}
				}
				return l;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> asTriplets() {
		return new ListSplitter<X>() {

			@Override
			public List<List<X>> split(List<X> list) {
				List<List<X>> l = new ArrayList<>();
				for(int i=0; i<list.size(); i+=3) {
					if(i < list.size() - 2) {
						l.add(List.of(list.get(i), list.get(i+1), list.get(i+2)));
					}
				}
				return l;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> asTripletsWithRest() {
		return new ListSplitter<X>() {

			@Override
			public List<List<X>> split(List<X> list) {
				List<List<X>> l = new ArrayList<>();
				for(int i=0; i<list.size(); i+=3) {
					l.add(i < list.size()-2 ? List.of(list.get(i), list.get(i+1), list.get(i+2)) : i < list.size()-1 ? List.of(list.get(i), list.get(i+1)) : List.of(list.get(i)));
				}
				return l;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> bySeparator(X separator) {
		return new ListSplitter<X>() {

			@Override
			public List<List<X>> split(List<X> list) {
				List<List<X>> l = new ArrayList<>();
				List<X> temp = new ArrayList<>();
				for(int i=0; i<list.size(); i++) {
					if(!list.get(i).equals(separator)) {
						temp.add(list.get(i));
						if(i==list.size()-1 && !temp.isEmpty()) {
							 l.add(List.copyOf(temp));
						} 
					} else {
						l.add(List.copyOf(temp));
						l.add(List.of(separator));
						temp.clear();
					}
				}
				return l;
			}
			
		};
	}

	@Override
	public <X> ListSplitter<X> byPredicate(Predicate<X> predicate) {
		return new ListSplitter<X>() {

			@Override
			public List<List<X>> split(List<X> list) {
				List<List<X>> l = new ArrayList<>();
				List<X> tempTrue = new ArrayList<>();
				List<X> tempFalse = new ArrayList<>();
				for(int i=0; i<list.size(); i++) {
					if(predicate.test(list.get(i))) {
						if(!tempFalse.isEmpty()) {
							l.add(List.copyOf(tempFalse));
							tempFalse.clear();
						}
						tempTrue.add(list.get(i));
						if(i==list.size()-1 && !tempTrue.isEmpty()) {
							 l.add(List.copyOf(tempTrue));
						} 
					} else if(predicate.negate().test(list.get(i))){
						if(!tempTrue.isEmpty()) {
							l.add(List.copyOf(tempTrue));
							tempTrue.clear();
						}
						tempFalse.add(list.get(i));
						if(i==list.size()-1 && !tempFalse.isEmpty()) {
							 l.add(List.copyOf(tempFalse));
						} 
					}
				}
				return l;
			}
			
		};
	}

}
