
public class BaseballPlayer extends BallSportsPlayer {
	
	private int rbi;
	
	public BaseballPlayer() {
		super();
		this.rbi = 0;
	}
	
	public BaseballPlayer(int weight, String gender ,int rbi) {
		super(weight, gender);
		this.rbi = rbi;
	}
	
	public int getRbi() {
		return this.rbi;
	}
	
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}
	
	public String toString() {
		return "BaseballPlayer[" + super.toString() + ", rbi=" + this.rbi + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof BaseballPlayer) {
				BaseballPlayer baseballPlayer = (BaseballPlayer)o;
				
				if (this.rbi == baseballPlayer.rbi) return true;
				else return false;
			}
		}
		return false;
	}
	
}
