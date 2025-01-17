package PartI;
import java.util.ArrayList;


public class MyStack<E> {

	private ArrayList<E> ar;
	
	public MyStack() {
		this.ar = new ArrayList<E>();
	}
	
	public boolean empty() {
		return this.ar.isEmpty();
	}
	
	public E peek() {
		if (empty()) {
			throw new RuntimeException("Stack is empty");
		}
		return this.ar.get(ar.size()-1);
	}
	
	public E pop() {
		if (empty()) {
			throw new RuntimeException("Stack is empty");
		}
		return this.ar.remove(ar.size()-1);
	}
	
	public E push(E item) {
		this.ar.add(item);
		return item;
	}
	
	public int search(Object o) {
		int index = this.ar.lastIndexOf(o);
		if (index >= 0) {
			return (ar.size()-index);
		}
		return -1;
	}
	
}
