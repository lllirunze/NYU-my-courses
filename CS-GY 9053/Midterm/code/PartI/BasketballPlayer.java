
public class BasketballPlayer extends BallSportsPlayer {

	private int height;
	
	public BasketballPlayer() {
		super();
		this.height = 0;
	}
	
	public BasketballPlayer(int weight, String gender, int height) {
		super(weight, gender);
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return "BasketballPlayer[" + super.toString() + ", height=" + this.height + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof BasketballPlayer) {
				BasketballPlayer basketballPlayer = (BasketballPlayer)o;
				
				if (this.height == basketballPlayer.height) return true;
				else return false;
			}
		}
		return false;
	}
	
}
