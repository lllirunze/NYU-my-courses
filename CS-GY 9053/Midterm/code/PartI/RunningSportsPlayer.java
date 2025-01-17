
public abstract class RunningSportsPlayer extends SportsPlayer {

	public RunningSportsPlayer() {
		super();
	}
	
	public RunningSportsPlayer(int weight, String gender) {
		super(weight, gender);
	}
	
	public String toString() {
		return "RunningSportsPlayer[" + super.toString() + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof RunningSportsPlayer) {
				return true;
			}
		}
		return false;
	}
	
}
