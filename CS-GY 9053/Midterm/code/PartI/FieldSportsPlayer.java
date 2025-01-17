
public abstract class FieldSportsPlayer extends SportsPlayer {

	public FieldSportsPlayer() {
		super();
	}
	
	public FieldSportsPlayer(int weight, String gender) {
		super(weight, gender);
	}
	
	public String toString() {
		return "FieldSportsPlayer[" + super.toString() + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof FieldSportsPlayer) {
				return true;
			}
		}
		return false;
	}
	
}
