package arraylists;
import java.util.ArrayList;

import fruit.*;

public class FruitArraylist {

	public static void main(String[] args) {
		
		// this ArrayList MUST be parameterized
		ArrayList<Fruit> fruitArrayList = new ArrayList<Fruit>();
		
		/* a. Create 8 Fruit objects:
		 * 1 non-rotten red Apple with a crisp texture and sweet taste 
		 * 2 rotten green Apple objects with a soft texture and tart taste
		 * 3 non-rotten Lemon objects with a sour taste. “sourness” should be a random integer from 0-100 for each object 
		 * 2 rotten Orange objects of type “mandarin” with a sweet taste.
		 * 
		 * Put those objects in the Arraylist. 
		 * They should all be able to be added to the same Arraylist, regardless of their subclass. 
		 * The Arraylist MUST be parameterized to accept Fruit objects. 
		 * It should accept all Fruit objects but should not accept non-fruit objects. For example, this:
		 * 
		 * fruitArrayList.add(new Object());
		 * 
		 * should not compile
		 */
		
		// 1 non-rotten red Apple with a crisp texture and sweet taste
		Fruit fruit1 = new Apple("sweet", "crisp", "red", false);
		// 2 rotten green Apple objects with a soft texture and tart taste
		Fruit fruit2 = new Apple("tart", "soft", "green", true);
		Fruit fruit3 = new Apple("tart", "soft", "green", true);
		// 3 non-rotten Lemon objects with a sour taste. “sourness” should be a random integer from 0-100 for each object 
		Fruit fruit4 = new Lemon((int)(Math.random()*100), "sour", false);
		Fruit fruit5 = new Lemon((int)(Math.random()*100), "sour", false);
		Fruit fruit6 = new Lemon((int)(Math.random()*100), "sour", false);
		// 2 rotten Orange objects of type “mandarin” with a sweet taste.
		Fruit fruit7 = new Orange("mandarin", "sweet", true);
		Fruit fruit8 = new Orange("mandarin", "sweet", true);
		
		fruitArrayList.add(fruit1);
		fruitArrayList.add(fruit2);
		fruitArrayList.add(fruit3);
		fruitArrayList.add(fruit4);
		fruitArrayList.add(fruit5);
		fruitArrayList.add(fruit6);
		fruitArrayList.add(fruit7);
		fruitArrayList.add(fruit8);
		
		/* b. Print out the average sourness of all the Lemon objects in the Arraylist. 
		 * You have to do this by looping through the array list, finding the Lemon objects, and their sourness.
		 */
		int lemonCount = 0;
		double averageSourness = 0.0;
		for (Fruit fruit : fruitArrayList) {
			if (fruit instanceof Lemon) {
				lemonCount++;
				Lemon lemon = (Lemon) fruit;
				averageSourness += lemon.getSourness();
			}
		}
		if (lemonCount == 0) {
			// There are no lemons in the list。
			System.out.println("There are no lemons in the list.");
		}
		else {
			averageSourness /= lemonCount;
			System.out.println("The average sourness of all the Lemon objects: " + averageSourness);
		}
		
		/* c. Remove the matching objects: 
		 * Retain the 1st rotten green Apple object in an Apple variable. 
		 * The goal is ultimately to remove all of the Apple objects in the Arraylist that match this variable:
		 * 
		 * To start: 
		 * Loop through the Arraylist and print out (using toString) which objects in the Apple object is equal (in value) to the Apple object in your variable. 
		 * 
		 * Also print out which object in the Arraylist is the same object as the one in your variable.
		 * 
		 * You must figure out how to remove all the matching objects from the Arraylist. 
		 * There is no one correct way to do this. But there are incorrect ways.
		 */
		
		// this is the variable you should retain to compare
		// to the other objects in the arraylist
		Apple rottenGreenApple1 = (Apple)fruit2;
		
		for (int i=0; i<fruitArrayList.size(); i++) {
			if (fruitArrayList.get(i) instanceof Apple) {
				Apple apple = (Apple)fruitArrayList.get(i);
				if (apple.equals(rottenGreenApple1)) {
					System.out.println("The rottenGreenApple1 is equal (in value) to " + apple.toString());
				}
				if (apple == rottenGreenApple1) {
					System.out.println("The rottenGreenApple1 is same as " + apple.toString());
				}
			}
			else continue;
		}
		
		for (int i=fruitArrayList.size()-1; i>=0; i--) {
			if (fruitArrayList.get(i) instanceof Apple) {
				Apple apple = (Apple)fruitArrayList.get(i);
				if (apple.equals(rottenGreenApple1)) {
					fruitArrayList.remove(i);
				}
			}
			else continue;
		}
		
		/* d. Print out the remaining objects: 
		 * Loop through the Arraylist again and print out (using toString) all the remaining objects in the Arraylist.
		 */
		for (Fruit fruit : fruitArrayList) {
			System.out.println(fruit.toString());
		}
		
	}
}
