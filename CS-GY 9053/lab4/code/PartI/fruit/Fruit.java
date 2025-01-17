package fruit;

public class Fruit {
	
	private String color;
	private boolean rotten;
	/* This uses the static “id” pattern. 
	 * Every creation of a new Fruit object (of any kind) 
	 * should generate a new and unique id from the static next_id field, 
	 * which is stored in the id field and accessible by getId.
	 */
	private static int next_id = 0;
	private int id;
	
	public Fruit() {
		this.id = next_id;
		next_id++;
	}
	
	public Fruit(String color, boolean rotten) {
		this();
		this.color = color;
		this.rotten = rotten;
//		this.id = next_id;
//		next_id++;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isRotten() {
		return this.rotten;
	}
	
	public void setRotten(boolean rotten) {
		this.rotten = rotten;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		/* toString() should contain the name of the class and all of the fields/data for that class. 
		 * You can decide whether this will work by a subclass calling the toString method of the superclass 
		 * and including that in the subclass toString result (as in the GeometricObject hierarchy) 
		 * or if the toString method accesses all the data/fields in the object itself.
		 * 
		 * Example: return "Shape[color=" + this.color + ", filled=" + this.filled + "]";
		 */
		return "Fruit[id=" + this.id + ", color=" + this.color + ", rotten=" + this.rotten + "]";
	}
	
	public boolean equals(Object o) {
		/* Now that you’ve implemented this, write equals methods for all the classes. 
		 * The equals method should take an Object as an argument 
		 * and return true if the field values of the class and its superclass(es) are equal.
		 */
		if (this == o) return true;
		else if (o instanceof Fruit) {
			Fruit fruit = (Fruit)o;
			// Determine whether id is the same
//			if (this.id != fruit.id) return false;
			// Determine whether rotten is the same
			if (this.rotten != fruit.rotten) return false;
			// Determine whether the colors are the same
			if (this.color == null && fruit.color == null) return true;
			else if (this.color == null || fruit.color == null) return false;
			else if (this.color.equals(fruit.color)) return true;
			else return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Fruit fruitTest1 = new Fruit();
		System.out.println(fruitTest1.toString());
		fruitTest1.setColor("red");
		fruitTest1.setRotten(false);
		System.out.println(fruitTest1.toString());
		System.out.println("fruitTest1 color is " + fruitTest1.getColor());
		System.out.println("fruitTest rotten is " + fruitTest1.isRotten());
		
		Fruit fruitTest2 = new Fruit("pink", true);
		System.out.println(fruitTest2.toString());
		System.out.println(fruitTest1.equals(fruitTest2));
		
		Fruit fruitTest3 = new Fruit("pink", true);
		System.out.println(fruitTest3.toString());
		System.out.println(fruitTest3.equals(fruitTest2));
		Fruit fruitTest4 = fruitTest2;
		System.out.println(fruitTest4.toString());
		System.out.println(fruitTest4.equals(fruitTest2));
		Fruit fruitTest5 = new Fruit();
		System.out.println(fruitTest5.toString());
		Fruit fruitTest6 = new Fruit();
		System.out.println(fruitTest6.toString());
		System.out.println(fruitTest5.equals(fruitTest6));
		Fruit fruitTest7 = fruitTest6;
		System.out.println(fruitTest7.toString());
		System.out.println(fruitTest7.equals(fruitTest6));
	}
	
}
