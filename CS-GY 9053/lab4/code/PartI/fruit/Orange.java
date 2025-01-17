package fruit;

public class Orange extends Cirtus {
	
	private String type;
	
	public Orange() {
		// Orange objects are always “orange” in color.
		super();
		super.setColor("orange");
	}
	
	public Orange(String type, String taste, Boolean rotten) {
		super(taste, "orange", rotten);
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return "Orange[" + super.toString() + ", type=" + this.type + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof Orange) {
				Orange orange = (Orange)o;
				
				boolean checkType = false;
				if (this.type == null && orange.type == null) checkType = true;
				else if (this.type == null || orange.type == null) checkType = false;
				else if (this.type.equals(orange.type)) checkType = true;
				else checkType = false;
				
				return checkType;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
	}
	
}
