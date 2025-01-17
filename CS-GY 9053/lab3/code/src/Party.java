
public class Party {
	
	private Hero[] heroes;
	
	public Party() {
		/* Initialize the Hero array to hold 3 Hero’s. 
		 * Initialize each element to null.
		 */
		this.heroes = new Hero[3];
	}
	
	public void addHero(Hero hero, int index) {
		/* Add a Hero to the current party at the given index. 
		 * If there is an existing Hero at the index, replace it. 
		 * If the hero is already in the party, 
		 * 		print "<name> is already in the party."
		 */
		for (int i=0; i<3; i++) {
			if (this.heroes[i] == hero) {
				System.out.println(this.heroes[i].getName() + " is already in the party.");
				return;
			}
		}
		if (index < 0 || index > 2) {
			// The index is invalid.
			System.out.println("Invalid index!");
			return;
		}
		else {
			if (this.heroes[index] == null) this.heroes[index] = hero;
			else this.heroes[index] = hero;
		}
	}
	
	public void removeHero(int index) {
		/* Remove the Hero from the given index (to "remove" from the array, 
		 * set the array element to null and shift any other elements to the left, 
		 * so that the only nulls are at the end of the array).
		 */
		if (index < 0 || index > 2) {
			// The index is invalid.
			System.out.println("Invalid index!");
			return;
		}
		this.heroes[index] = null;
		int cur = 0;
		for (int i=0; i<3; i++) {
			if (this.heroes[i] == null) continue;
			else {
				this.heroes[cur] = this.heroes[i];
				cur++;
			}
		}
		for (int i=cur; i<3; i++) {
			this.heroes[i] = null;
		}
	}
	
	public Hero getHero(int index) {
		// Return the Hero of the desired index.
		if (index < 0 || index > 2) {
			// The index is invalid.
			System.out.println("Invalid index!");
			return null;
		}
		return this.heroes[index];
	}
	
	public void gainExperience(int experience) {
		/* Prints "The party gained <experience> experience."
		 * Increase the experience of all the Hero’s in the Party.
		 * Hint: Use each Hero’s gainExperience method.
		 * Note: One common question I got was about how "gainExperience" works in the "Party" class. 
		 * 		 Take the experience and split it evenly among all members of the party 
		 * 		 and increase the experience for the Hero objects by that amount.
		 */
		int heroCount = 0;
		for (Hero hero : this.heroes) {
			if (hero == null) continue;
			else {
				heroCount++;
			}
		}
		if (heroCount == 0) {
			System.out.println("There isn't any hero in the party.");
			return;
		}
		int heroExperience = experience / heroCount;
		System.out.println("The party gained " + String.valueOf(experience) + " experience.");
		for (Hero hero : this.heroes) {
			if (hero == null) continue;
			else {
				hero.gainExperience(heroExperience);
			}
		}
	}
	
	public String toString() {
		/* Return a String containing the information about the hero.
		 * Template:
		 * 	   "Party:
		 * 		<name> the <role> is level <level> with <experience> experience.
		 * 		<name> the <role> is level <level> with <experience> experience.
		 * 		<name> the <role> is level <level> with <experience> experience.
		 * 	   "
		 * Example:
		 * 	   "Party:
		 * 		Thor the Warrior is level 10 with 4 experience.
		 * 		Groot the Thief is level 20 with 19 experience.
		 * 		Doctor Strange the Wizard is level 5 with 1 experience.
		 * 	   "
		 * Note: Only print assigned party members (Do not print anything if any heroes elements are null).
		 * Hint: Use each Hero’s toString method.
		 */
		StringBuilder stringBuilder = new StringBuilder("Party:\n");
		for (Hero hero : this.heroes) {
			if (hero == null) continue;
			else {
				stringBuilder.append(hero.toString());
				stringBuilder.append("\n");
			}
		}
		
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		/* After implementing this, 
		 * create three heroes with names and roles of your choice,
		 * add them to the party, and give the party 100 experience points. 
		 * Then print out the party by calling the toString() method for the party 
		 * and passing it to System.out.println()
		 */
		Party party = new Party();
		
		Hero hero1 = new Hero("Thor");
        hero1.setRole("Warrior");
        party.addHero(hero1, 0);

        Hero hero2 = new Hero("Groot");
        hero2.setRole("Thief");
        party.addHero(hero2, 1);

        Hero hero3 = new Hero("Doctor Strange");
        hero3.setRole("Wizard");
        party.addHero(hero3, 2);

        party.gainExperience(100);
        
        String string = party.toString();
        System.out.println(string);
	}
	
}
