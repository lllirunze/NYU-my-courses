package fruit;

public class Lemon extends Cirtus {

	private int sourness;
	
	public Lemon() {
		// Lemon objects are always “yellow” in color.
		super();
		super.setColor("yellow");
	}
	
	public Lemon(int sourness, String taste, boolean rotten) {
		super(taste, "yellow", rotten);
		this.sourness = sourness;
	}
	
	public int getSourness() {
		return this.sourness;
	}
	
	public void setSourness(int sourness) {
		this.sourness = sourness;
	}
	
	public String toString() {
		return "Lemon[" + super.toString() + ", sourness=" + this.sourness + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof Lemon) {
				Lemon lemon = (Lemon)o;
				return this.sourness == lemon.sourness;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
	}
	
}
