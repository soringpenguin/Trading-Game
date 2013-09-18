import java.util.Random;


// Will be used to handle random events that can happen when you travel
public class Encounter {
	
	Location loc_from; // The location the player is coming from
	Location loc_to; // The location the player is going to
	Player player; // The player object
	Battle battle;
	
	// Initializes Encounter object and sets variable
	public Encounter(Player p, Location from, Location to, Random rand) {
		player = p;
		loc_from = from;
		loc_to = to;
		// Randomly decide what kind of encounter will happen
		if(rand.nextInt(10) < 8) { // temporary for testing
			battle = new Battle(player);
			player.stat.add_battle_count();
			battle.start();
		}
		else;
			// Storm happens
//			print("Storm Happens");
	}
	
	private void print(String str) {
		System.out.println(str);
		player.print_to_frame(str);
	}

}
