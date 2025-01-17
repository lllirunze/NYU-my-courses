
public class TrackPlayer extends RunningSportsPlayer {

	private int distance;
	
	public TrackPlayer() {
		super();
		this.distance = 0;
	}
	
	public TrackPlayer(int weight, String gender, int distance) {
		super(weight, gender);
		this.distance = distance;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public String toString() {
		return "TrackPlayer[" + super.toString() + ", distance=" + this.distance + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof TrackPlayer) {
				TrackPlayer trackPlayer = (TrackPlayer)o;
				
				if (this.distance == trackPlayer.distance) return true;
				else return false;
			}
		}
		return false;
	}
	
}
