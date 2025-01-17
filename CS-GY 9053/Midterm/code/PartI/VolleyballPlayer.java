
public class VolleyballPlayer extends BallSportsPlayer {
	
	private int maxPoints;
	
	public VolleyballPlayer() {
		super();
		this.maxPoints = 0;
	}
	
	public VolleyballPlayer(int weight, String gender, int maxPoints) {
		super(weight, gender);
		this.maxPoints = maxPoints;
	}
	
	public int getMaxPoints() {
		return this.maxPoints;
	}
	
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	public String toString() {
		return "VolleyballPlayer[" + super.toString() + ", maxPoints=" + this.maxPoints + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof VolleyballPlayer) {
				VolleyballPlayer volleyballPlayer = (VolleyballPlayer)o;
				
				if (this.maxPoints == volleyballPlayer.maxPoints) return true;
				else return false;
			}
		}
		return false;
	}
	
}
