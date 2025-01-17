import java.util.ArrayList;
import java.util.Collections;

public class Part1Test {

	public static double getAverageWeight(ArrayList<? extends SportsPlayer> players) {
        if (players.isEmpty()) {
            return 0.0;
        }

        double totalWeight = 0;
        for (SportsPlayer player : players) {
            totalWeight += player.getWeight();
        }
        return totalWeight / players.size();
    }

	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ArrayList<SportsPlayer> sportsPlayers = new ArrayList<SportsPlayer>();
		
		// Adding BaseballPlayer instances
		sportsPlayers.add(new BaseballPlayer(90, "male", 25));
		sportsPlayers.add(new BaseballPlayer(85, "female", 30));

        // Adding BasketballPlayer instances
        sportsPlayers.add(new BasketballPlayer(100, "male", 200));
        sportsPlayers.add(new BasketballPlayer(70, "female", 180));

        // Adding VolleyballPlayer instances
        sportsPlayers.add(new VolleyballPlayer(80, "male", 15));
        sportsPlayers.add(new VolleyballPlayer(75, "female", 20));

        // Adding ShotputPlayer instances
        sportsPlayers.add(new ShotputPlayer(110, "male", 15));
        sportsPlayers.add(new ShotputPlayer(105, "female", 10));

        // Adding PoleVaultPlayer instances
        sportsPlayers.add(new PoleVaultPlayer(95, "male", 300));
        sportsPlayers.add(new PoleVaultPlayer(90, "female", 290));

        // Adding TrackPlayer instances
        sportsPlayers.add(new TrackPlayer(80, "male", 5000));
        sportsPlayers.add(new TrackPlayer(75, "female", 4000));

        // Adding CrossCountryPlayer instances
        sportsPlayers.add(new CrossCountryPlayer(78, "male", 15.5));
        sportsPlayers.add(new CrossCountryPlayer(73, "female", 16.2));
		
//        for (SportsPlayer player : sportsPlayers) {
//        	System.out.println(player.toString());
//        }
//        
        System.out.println("Sort the ArrayList by weight in ascending order:");
        Collections.sort(sportsPlayers);
        for (SportsPlayer player : sportsPlayers) {
        	System.out.println(player.toString());
        }
        
        System.out.println();
        System.out.println("Sort the ArrayList by weight in descending order:");
        Collections.sort(sportsPlayers, (p1, p2) -> {
        	return Integer.compare(p2.getWeight(), p1.getWeight());
        });
        for (SportsPlayer player : sportsPlayers) {
        	System.out.println(player.toString());
        }
        
        System.out.println();
        System.out.println("The average weight of the elements of the ArrayList: " + getAverageWeight(sportsPlayers));
        
	}

}
