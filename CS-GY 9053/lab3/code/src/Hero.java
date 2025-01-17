
public class Hero {
	
	private String name;
	private String role;
	private int level;
	private int experience;
	
	private static int MAX_LEVEL = 100;
	private static String[] ROLES = {"Warrior", "Priest", "Wizard", "Thief"};
	
	public Hero(String name) {
		/* Set the name to given value, 
		 * set role to “Unassigned”, 
		 * set level to 1, 
		 * and experience to 0.
		 */
		this.name = name;
		this.role = "Unassigned";
		this.level = 1;
		this.experience = 0;
	}
	
	public void setRole(String role) {
		/* Set the role of the Hero. 
		 * If the role is not one of the allowed roles, 
		 * 		print “Invalid role” and set the role to “Unassigned”.
		 */
		boolean findValidRole = false;
		for (String ROLE : ROLES) {
			if (ROLE.equals(role)) {
				findValidRole = true;
				break;
			}
		}
		if (findValidRole == true) {
			this.role = role;
		}
		else {
			System.out.println("Invalid role");
			this.role = "Unassigned";
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getExperience() {
		return this.experience;
	}
	
	public int expToLevelUP() {
		/* Return the amount of experience necessary to advance to the next level. 
		 * The formula is level^2. 
		 * For example, at level 6, this should return 6^2 = 36. 
		 * This is a helper method for the gainExperience method.
		 */
		return (this.level * this.level);
	}
	
	public void gainExperience(int experience) {
		/* Increase the experience of the Hero. 
		 * If the experience reaches or passes the maximum for the Hero’s current level, 
		 * 		increase the level by 1 by “consuming” the necessary experience.
		 * For example, at level 4 with 20 experience, 
		 * 				the Hero should level up to 5 with 4 experience remaining. 
		 * When a Hero levels up, print "<name> is now level <level>!" 
		 * The max level is 100.
		 * 
		 * Note: If enough experience is gained at once, 
		 * 			the Hero will level up more than once. 
		 * For example, a level 3 hero receiving 75 experience will level up to 6 with (75 – 9 – 16 – 25) = 25 experience remaining. 
		 */
		this.experience += experience;
		while (this.experience >= expToLevelUP() && this.level < MAX_LEVEL) {
			this.experience -= expToLevelUP();
			level++;
			System.out.println(this.name + " is now level " + this.level + "!");
		}
	}
	
	public String toString() {
		/* Return a String containing the information about the hero: 
		 * Template: "<name> the <role> is level <level> with <experience> experience."
		 * Example: "Hagrid the Wizard is level 55 with 120 experience."
		 */
		String string = this.name + " the " + this.role + " is level " + String.valueOf(this.level) + " with " + String.valueOf(this.experience) + " experience.";
		return string;
	}
	
}
