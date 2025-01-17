
public abstract class BallSportsPlayer extends SportsPlayer {

	public BallSportsPlayer() {
		super();
	}
	
	public BallSportsPlayer(int weight, String gender) {
		super(weight, gender);
	}
	
	public String toString() {
		return "BallSportsPlayer[" + super.toString() + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof BallSportsPlayer) {
				return true;
			}
		}
		return false;
	}
	
}
