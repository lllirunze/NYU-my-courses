package PartII;
import java.util.HashSet;
import java.util.Set;

public class MathSet<E> extends HashSet<E> {

	public Set<E> intersection(Set<E> s2) {
		Set<E> set = new HashSet<>();
		for(E element : this) {
			if (s2.contains(element)) {
				set.add(element);
			}
		}
		return set;
	}
	
	public Set<E> union(Set<E> s2) {
		Set<E> set = new HashSet<>(this);
		set.addAll(s2);
		return set;
	}
	
	public <T> Set<Pair<E,T>> cartesianProduct(Set<T> s2) {
		Set<Pair<E, T>> set = new HashSet<>();
		for (E elementE : this) {
			for (T elementT : s2) {
				set.add(new Pair<E, T>(elementE, elementT));
			}
		}
		return set;
	}
	
	public static void main(String[] args) {
		// create two MathSet objects of the same type.
		// See if union and intersection works.
		
		// create another MathSet object of a different type
		// calculate the cartesian product of this set with one of the
		// above sets
		MathSet<Integer> A = new MathSet<>();
        A.add(1);
        A.add(2);
        A.add(3);

        MathSet<Integer> B = new MathSet<>();
        B.add(2);
        B.add(3);
        B.add(4);

        System.out.println("Intersection: " + A.intersection(B));
        System.out.println("Union: " + A.union(B));
        
        MathSet<Character> C = new MathSet<>();
        C.add('H');
        C.add('T');

        System.out.println("Cartesian Product: " + A.cartesianProduct(C));
	}
}
