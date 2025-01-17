package PartI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueue<E> implements Queue<E> {
	
	private ArrayList<E> ar;
	
	public MyQueue() {
		this.ar = new ArrayList<E>();
	}
	
	public int size() {
		return ar.size();
	}
	
	public boolean isEmpty() {
		return ar.isEmpty();
	}
	
	public boolean offer(E e) {
		ar.add(e);
		return true;
	}
	
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("MyQueue is empty");
		}
		return ar.remove(0);
	}
	
	public E poll() {
		if (isEmpty()) {
			return null;
		}
		return ar.remove(0);
	}
	
	public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException("MyQueue is empty");
        }
        return ar.get(0);
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return ar.get(0);
    }

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
//		return false;
		return ar.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
//		return null;
		return ar.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
//		return null;
		return ar.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
//		return null;
		return ar.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
//		return false;
		return ar.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
//		return false;
		return ar.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
//		return false;
		return ar.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
//		return false;
		return ar.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
//		return false;
		return ar.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		ar.clear();
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
//		return false;
		if (offer(e)) {
			return true;
		}
		else {
			throw new IllegalStateException("Cannot add element to the queue");
		}
	}	

}










