
public class CrossCountryPlayer extends RunningSportsPlayer {
	
	private double bestMileTime;
	
	public CrossCountryPlayer() {
		super();
		this.bestMileTime = 0.0;
	}
	
	public CrossCountryPlayer(int weight, String gender, double bestMileTime) {
		super(weight, gender);
		this.bestMileTime = bestMileTime;
	}
	
	public double getBestMileTime() {
		return this.bestMileTime;
	}
	
	public void setBestMileTime(double bestMileTime) {
		this.bestMileTime = bestMileTime;
	}
	
	public String toString() {
		return "CrossCountryPlayer[" + super.toString() + ", bestMileTime=" + this.bestMileTime + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof CrossCountryPlayer) {
				CrossCountryPlayer crossCountryPlayer = (CrossCountryPlayer)o;
				
				if (this.bestMileTime == crossCountryPlayer.bestMileTime) return true;
				else return false;
			}
		}
		return false;
	}
	
}
