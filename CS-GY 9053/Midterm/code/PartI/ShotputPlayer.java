
public class ShotputPlayer extends FieldSportsPlayer {
	
	private int maxDistance;
	
	public ShotputPlayer() {
		super();
		this.maxDistance = 0;
	}
	
	public ShotputPlayer(int weight, String gender, int maxDistance) {
		super(weight, gender);
		this.maxDistance = maxDistance;
	}
	
	public int getMaxDistance() {
		return this.maxDistance;
	}
	
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public String toString() {
		return "ShotputPlayer[" + super.toString() + ", maxDistance=" + this.maxDistance + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof ShotputPlayer) {
				ShotputPlayer shotputPlayer = (ShotputPlayer)o;
				
				if (this.maxDistance == shotputPlayer.maxDistance) return true;
				else return false;
			}
		}
		return false;
	}
	
}
