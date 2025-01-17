package fruit;

public class Cirtus extends Fruit {
	
	private String taste;
	
	public Cirtus() {
		super();
	}
	
	public Cirtus(String taste, String color, boolean rotten) {
		super(color, rotten);
		this.taste = taste;
	}
	
	public String getTaste() {
		return this.taste;
	}
	
	public void setTaste(String taste) {
		this.taste = taste;
	}
	
	public String toString() {
		return "Cirtus[" + super.toString() + ", taste=" + this.taste + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof Cirtus) {
				Cirtus cirtus = (Cirtus)o;
				
				boolean checkTaste = false;
				if (this.taste == null && cirtus.taste == null) checkTaste = true;
				else if (this.taste == null || cirtus.taste == null) checkTaste = false;
				else if (this.taste.equals(cirtus.taste)) checkTaste = true;
				else checkTaste = false;
				
				return checkTaste;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Cirtus cirtusTest1 = new Cirtus();
		System.out.println(cirtusTest1.toString());
		
		Cirtus cirtusTest2 = new Cirtus("sweet", "red", false);
		System.out.println(cirtusTest2.toString());
		
		Cirtus cirtusTest3 = new Cirtus("salt", "red", false);
		System.out.println(cirtusTest3.toString());
		System.out.println(cirtusTest3.equals(cirtusTest2));
		
		Cirtus cirtusTest4 = new Cirtus("salt", "red", false);
		System.out.println(cirtusTest4.toString());
		System.out.println(cirtusTest3.equals(cirtusTest4));
		
		Cirtus cirtusTest5 = new Cirtus("salt", "red", true);
		System.out.println(cirtusTest5.toString());
		System.out.println(cirtusTest3.equals(cirtusTest5));
	}
	
}
