import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

import heros.*;

/* your tasks:
 * create a class called HeroException
 * createHero should throw a HeroException
 * in main(), you should catch the HeroException
 * 
 */
public class ReadHeroFile {

	public static Hero createHero(String heroString) throws HeroException {
		
		String[] strings = heroString.split(",");
		String name = strings[0];
		String role = strings[1];
		int level = Integer.parseInt(strings[2]);
		int experience = Integer.parseInt(strings[3]);
		
		Hero hero = new Hero(name);
		hero.setRole(role);
		/* setRole should throw a HeroException which is caught 
		 * and then thrown by createHero if it is not an allowed ROLE.
		 */
		if (hero.getRole().equals("Unassigned")) {
			throw new HeroException("Role isn't allowed: " + role);
		}
		/* Furthermore, if the level and experience values do not match 
		 * (ie, if the amount of experience is insufficient to match the level listed 
		 * or if the level is smaller than would be expected for the amount of experience listed), 
		 * the you should throw a HeroException (the mechanism through which you do this is up to you. 
		 * It could be adding a new Hero constructor, or it could be something else. Up to you).
		 */
		hero.gainExperience(experience);
		if (hero.getLevel() != level) {
			throw new HeroException("Level " + level + " and experience " + experience + " values do not match");
		}
		
		return hero;
	}
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("heroes.txt");
		
		ArrayList<String> strings = new ArrayList<String>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				strings.add(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
//			System.out.println("Error reading file: " + e.getMessage());
		}
		
		Party party = new Party();
		
		for (String string : strings) {
			try {
//				System.out.println(string);;
				Hero hero = createHero(string);
				party.addHero(hero, party.getSize());
				
			} catch (HeroException e) {
				// TODO: handle exception
				e.printStackTrace();
//				System.out.println("Error creating hero: " + e.getMessage());
			}
		}
		
		System.out.print(party.toString());
		
	}
}
