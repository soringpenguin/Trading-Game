import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Battle {

	ArrayList<Enemy> num_enemies;
	Player player;
	Prices prices;
	
	public Battle(Player p) {
		player = p;
		num_enemies = get_num_enemies();
		prices = new Prices();
	}
	
	// Will return number of enemies
	// Will only be at most 5 more enemies than the player has guns
	private ArrayList<Enemy> get_num_enemies() {
		Random rand = new Random();
		int temp = rand.nextInt(player.num_guns + 3) + 1;
		ArrayList<Enemy> list = new ArrayList<Enemy>();
		for(int x = 0; x < temp; x++) {
			list.add(new Enemy(rand.nextInt(2)));
		}
		return list;
	}

	public void start() {
		boolean ran_away = false;
		print("You have been attacked by " + num_enemies.size() + " bandits!");
		while(!num_enemies.isEmpty() && !ran_away) {
			print("1. Attack");
			print("2. Run away");
			InputStreamReader istream = new InputStreamReader(System.in);
			BufferedReader bufRead = new BufferedReader(istream);
			String choice = "";
			try {
				choice = bufRead.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (choice.equals("1")) {
				attack();
				enemies_attack();
			}
			else if (choice.equals("2")) {
				ran_away = run_away();
				// If you didn't make it they attack
				if(!ran_away)
					enemies_attack();
			}
			else
				print("Error: Please enter one of the options listed.\n");
		}
		Random rand = new Random();
		if(!ran_away) {
			double amount = 0.0;
			if(player.cash < 50)
				amount = 1 + rand.nextInt(3);
			else
				amount = prices.round_two_decimals(player.cash * 0.075);
			player.cash += amount;
			player.stat.add_battles_won();
			player.stat.add_to_game_profit(amount);
			player.stat.add_battle_money(amount);
			print("You defeated the bandits and gained $" + amount + ".\n");
		}
	}

	private void enemies_attack() {
		if(!num_enemies.isEmpty()) {
			Random rand = new Random();
			for(int x = 0; x < num_enemies.size(); x++) {
				// An attempt at enemies attack
				int damage = -1 * (rand.nextInt(4) + 3);
				player.stat.add_damage_taken(damage);
				player.health_change(damage);
			}
			// Only if you take damage
			print("You have " + player.health + " health left.\n");
		}
	}

	private boolean run_away() {
		Random rand = new Random();
		// 50% chance of success
		if(rand.nextInt(10) < 5) {
			print("You successfully ran away from them.\n");
			player.stat.add_battles_escaped();
			return true;
		}
		else {
			print("You couldn't get away from them.\n");
			return false;
		}
		
	}

	private void attack() {
		int count = player.num_guns;
		int enemy_index = 0;
		for(int x = 0; x < count && !num_enemies.isEmpty(); x++) {
			if(enemy_index >= num_enemies.size())
				enemy_index = 0;
			// For one shot enemies
			if(num_enemies.get(enemy_index).hit_points == 0) {
				player.stat.add_bandits_defeated();
				num_enemies.remove(enemy_index);
			}
			// makes a two shot enemy a one shot enemy
			else {
				num_enemies.get(enemy_index).hit_points = 0;
				enemy_index++;
			}
		}
		print("You attacked. Now there are " + num_enemies.size() + " enemies remaining.");
	}
	
	private void print(String str) {
		System.out.println(str);
		player.print_to_frame(str);
	}
}
