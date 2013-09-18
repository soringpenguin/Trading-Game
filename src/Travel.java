import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;


public class Travel {
	
	private ArrayList<Location> travel_list;
	private ArrayList<Double> cost_list;
	Player player;
	Timer timer;
	int count = 0;
	int turn_num = 0;    // number of travels basically or turn number
	int chance_of_battle = 6;
	
	public Travel(Location location) {
		travel_list = location.can_travel_list;
		cost_list = location.can_travel_cost_list;
		player = location.player;
		
	}

		// Helps show the right travel locations based on your current location
		// the int tells me if I should print or set current location
		public boolean travel_helper(String str) {
			// make sure the int parse wont fail
			if(check_choice(str)) {
				int choice = 0;
				try {
					choice = Integer.parseInt(str.trim());
				}
				catch (NumberFormatException e) {
					print("Error: Please enter a numerical number\n");
					return false;
				}
				if (choice <= travel_list.size() && choice > 0){
					// Money check
					if(player.cash - cost_list.get(choice - 1) < 0.0) {
						print("You do not have enough money to travel there.\n");
						return false;
					}
				}
				
				long INITIAL_WAIT_TIME = 300;
				long REGULAR_WAIT_TIME = 900;
				// Simulate travel time
				try {
					Thread.sleep(INITIAL_WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Show travel time through text animation
				print("Traveling .");
				try {
					Thread.sleep(REGULAR_WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				print("Traveling . .");
				try {
					Thread.sleep(REGULAR_WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				print("Traveling . . .\n");
				try {
					Thread.sleep(REGULAR_WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Random rand = new Random();
				// 50% chance of encounter
				if(rand.nextInt(10) < chance_of_battle)
					new Encounter(player, player.get_location(), travel_list.get(choice-1), rand);
				
				player.travel(travel_list.get(choice-1), cost_list.get(choice-1));
				return true;
			}
			else if (str.equals("back") || str.equals("no") || str.equals("")) {
				return false;
			}
			else {
				print("Error: please select one of the options.\n");
				return false;
			}
		}

		private boolean check_choice(String str) {
			for(int x = 0; x < travel_list.size(); x++) {
				if(str.equals(x+1 + ""))
					return true;
			}
			return false;
		}

		// Will have a chance that you get busted for having alcohol
		// Percentage differs from location to location
		public void bust_alcohol(Location where_to) {
			if(player.num_alcohol > 0) {
				Random rand = new Random();
				double temp = rand.nextDouble();
				// Grabs the percentage from the location you are traveling and uses that
				if(temp < where_to.alcohol_bust_percentage) {
					player.available_space += player.num_alcohol;
					player.num_alcohol = 0;
					player.stat.add_to_alcohol_bust(turn_num-1);
					print("\nBusted for carring alcohol! They took it all.\n");
				}
			}
		}
		
	// Prints where the player can travel to.
	public void print_locations() {
		for(int x = 0; x < travel_list.size(); x++) {
			print(x+1 + ". Travel to " + travel_list.get(x).name +" costs $" + cost_list.get(x));
		}
	}
		
	private void print(String str) {
		System.out.println(str);
		player.print_to_frame(str);
	}
}
