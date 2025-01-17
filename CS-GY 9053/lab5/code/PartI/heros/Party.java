package heros;

import java.util.ArrayList;

public class Party {

	/* Instead of accommodating just 3 Heroes, 
	 * it should accommodate an arbitrary number. 
	 * (hint: use ArrayList) 
	 */
//	private Hero[] heroes;
	private ArrayList<Hero> heroes;
	
	public Party() {
//		heroes = new Hero[3]; // will automatically be null
		heroes = new ArrayList<Hero>();
	}
	
	public void addHero(Hero hero, int index) {
		for (Hero h: heroes) {
			if (h == hero) {
				System.out.println(hero.getName() + " is already in the party");
				return;
			}
		}
//		heroes[index] = hero;
		heroes.add(index, hero);
	}
	
	public void removeHero(int index) {
//		if ( (heroes[index] != null)) {
//			heroes[index] = null;
//			index++;
//			while ((index < heroes.length) && 
//					(heroes[index] != null)) {
//				heroes[index-1] = heroes[index];
//				heroes[index] = null;
//				index++;
//			}
//		}
		heroes.remove(index);
	}
	
	public Hero getHero(int index) {
//		return heroes[index];
		return heroes.get(index);
	}
	
	public void gainExperience(int experience) {
		System.out.println("the party has gained " + experience +
				" experience");
//		int numHeroes = 0;
//		int index = 0;
//		while (heroes[index] != null) {
//			numHeroes++;
//			index++;
//		}
		int numHeroes = heroes.size();
		// we will be generous and round up.
		int individualExperience = (int)Math.ceil((1.0*experience)/numHeroes);

//		index = 0;
//		while (heroes[index] != null) {
//			heroes[index].gainExperience(individualExperience);
//			index++;
//		}
		for (int index = 0; index < heroes.size(); index++) {
			heroes.get(index).gainExperience(individualExperience);
		}
	}
	
	public String toString() {
		// you don't have to use StringBuilder, but it's 
		// the most efficient
		StringBuilder sb = new StringBuilder("Party:\n");
//		int index = 0;
//		while ((index < heroes.length) && (heroes[index] != null)){
//			sb.append(heroes[index].toString() + "\n");
//			index++;
//		}
		for (int index = 0; index < heroes.size(); index++) {
			sb.append(heroes.get(index).toString() + "\n");
		}
		return sb.toString();
	}
	
	public int getSize() {
		return heroes.size();
	}
	
	public static void main(String[] args) {
		Hero h1 = new Hero("Bob");
		h1.setRole("Warrior");
		Hero  h2  = new Hero("John");
		h2.setRole("Wizard");
		Hero h3 = new Hero("Jane");
		h3.setRole("Priest");
		Hero h4 = new Hero("Dimitri");
		h4.setRole("Thief");
		
		Party p = new Party();
		p.addHero(h1, 0);
		p.addHero(h2, 1);
		p.addHero(h3, 2);

		System.out.println(p);
		p.removeHero(2);

		System.out.println(p);
		p.addHero(h4, 2);

		System.out.println(p);
		p.removeHero(0);
		System.out.println(p);
		p.addHero(h3, 2);
		System.out.println(p);
		p.addHero(h3, 0);
		System.out.println(p);
		p.addHero(h1, 2);
		System.out.println(p);

		p.removeHero(1);
		System.out.println(p);
		
		p.gainExperience(25);
		System.out.println(p);
		
	}
}
