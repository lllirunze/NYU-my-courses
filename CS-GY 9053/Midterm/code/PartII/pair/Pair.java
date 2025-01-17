package pair;

public class Pair<K, V> {
	
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public String toString() {
		return "Pair [key=" + this.key + ", value=" + this.value + "]";
	}
	
	public static void main(String[] args) {
		
		Pair<Integer, String> p = new Pair<Integer, String>(50, "Bob");
		System.out.println(p.toString());
	
	}
	
}
