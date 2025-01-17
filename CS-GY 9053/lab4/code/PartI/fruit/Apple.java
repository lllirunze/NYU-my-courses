package fruit;

public class Apple extends Fruit {
	
	private String taste;
	private String texture;
	
	public Apple() {
		super();
	}
	
	public Apple(String taste, String texture, String color, boolean rotten) {
		super(color, rotten);
		this.taste = taste;
		this.texture = texture;
	}
	
	public String getTaste() {
		return this.taste;
	}
	
	public void setTaste(String taste) {
		this.taste = taste;
	}
	
	public String getTexture() {
		return this.texture;
	}
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	public String toString() {
		// return "Circle[" + super.toString() + ", radius=" + this.radius + "]";
		return "Apple[" + super.toString() + ", taste=" + this.taste + ", texture=" + this.texture + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof Apple) {
				Apple apple = (Apple)o;
				
				boolean checkTaste = false;
				if (this.taste == null && apple.taste == null) checkTaste = true;
				else if (this.taste == null || apple.taste == null) checkTaste = false;
				else if (this.taste.equals(apple.taste)) checkTaste = true;
				else checkTaste = false;
				
				boolean checkTexture = false;
				if (this.texture == null && apple.texture == null) checkTexture = true;
				else if (this.texture == null || apple.texture == null) checkTexture = false;
				else if (this.texture.equals(apple.texture)) checkTexture = true;
				else checkTexture = false;
				
				return checkTaste && checkTexture;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Apple appleTest1 = new Apple();
		System.out.println(appleTest1.toString());
		
		Apple appleTest2 = new Apple("sweet", "crispy", "red", false);
		System.out.println(appleTest2.toString());
		
		Apple appleTest3 = new Apple("salt", "crispy", "red", false);
		System.out.println(appleTest3.toString());
		System.out.println(appleTest3.equals(appleTest2));
		
		Apple appleTest4 = new Apple("salt", "crispy", "red", false);
		System.out.println(appleTest4.toString());
		System.out.println(appleTest3.equals(appleTest4));
		
		Apple appleTest5 = new Apple("salt", "crispy", "red", true);
		System.out.println(appleTest5.toString());
		System.out.println(appleTest3.equals(appleTest5));
	}
	
}
