
public class PoleVaultPlayer extends FieldSportsPlayer {
	
	private int maxHeight;
	
	public PoleVaultPlayer() {
		super();
		this.maxHeight = 0;
	}
	
	public PoleVaultPlayer(int weight, String gender, int maxHeight) {
		super(weight, gender);
		this.maxHeight = maxHeight;
	}
	
	public int getMaxHeight() {
		return this.maxHeight;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public String toString() {
		return "PoleVaultPlayer[" + super.toString() + ", maxHeight=" + this.maxHeight + "]";
	}
	
	public boolean equals(Object o) {
		if (super.equals(o)) {
			if (o instanceof PoleVaultPlayer) {
				PoleVaultPlayer poleVaultPlayer = (PoleVaultPlayer)o;
				
				if (this.maxHeight == poleVaultPlayer.maxHeight) return true;
				else return false;
			}
		}
		return false;
	}
	
}
