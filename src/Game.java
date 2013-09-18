import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * The class Game is responsible for running the game. It holds all locations and
 * is in charge of repair, upgrade and gun checking. Also in charge of traveling and
 * arriving at new locations. Will print to console or call methods that will handle more
 * detail. Currently checks for alcohol busting. But should probably be moved to Traveling
 * object.
 * 
 * @version 0.2.1
 * 
 * @author Andrew Fortner
 *
 */
public class Game {

	public static void main(String args[]) {
		Game game = new Game();
		// Whether or not you use the gui or console
		game.p.run_gui = false;
		game.arrive();
		game.p.stat.print_all_stats();
	}	
	
	Player p;            // The player object, hold all player information and game stats
	Prices prices;       // How we generate product prices
	Loc_Arizona arizona;     // Arizona location
	Loc_California california;  // California location
	Loc_Mexico mexico;      // Mexico location
	Loc_Texas texas;      // Texas location
	
	ArrayList<Location> all_locations;      // List that hold all the locations you can travel to

	/** 
	 * Game constructor: Initializes all variables and adds locations to list of all locations.
	 * Then calls setup_travel_lists to organize where you can travel from where. Then sets the
	 * player's current and starting location.
	 */
	public Game() {
		all_locations = new ArrayList<Location>();
				
		p = new Player();
		
		arizona = new Loc_Arizona(p);
		all_locations.add(arizona);
		
		california = new Loc_California(p);
		all_locations.add(california);
		
		mexico = new Loc_Mexico(p);
		all_locations.add(mexico);
		
		texas = new Loc_Texas(p);
		all_locations.add(texas);
		
		// Sets up where you can travel from where and for how much
		// Hard coded in.
		setup_travel_lists();
		
		// Sets the players starting location
		p.set_location(arizona);		
	}	

	/**
	 *  This will set up the travel possibilities for each location by hand
	 *  and the cost to travel to each location
	 */
	private void setup_travel_lists() {
		for(Location l : all_locations) {
			if(l.name.equals("Arizona")) {
				// Can travel to California for $5
				l.can_travel_list.add(california);
				l.can_travel_cost_list.add(5.0);
				
				// Can travel to Mexico for $7
				l.can_travel_list.add(mexico);
				l.can_travel_cost_list.add(7.0);
				
				// Can travel to Texas for $10
				l.can_travel_list.add(texas);
				l.can_travel_cost_list.add(10.0);
			}
			else if(l.name.equals("California")) {
				// Can travel to Arizona for $5
				l.can_travel_list.add(arizona);
				l.can_travel_cost_list.add(5.0);
				
				// Can travel to Mexico for $10
				l.can_travel_list.add(mexico);
				l.can_travel_cost_list.add(10.0);
			}
			
			else if(l.name.equals("Mexico")) {
				// Can travel to Arizona for $7
				l.can_travel_list.add(arizona);
				l.can_travel_cost_list.add(7.0);
				
				// Can travel to California for $10
				l.can_travel_list.add(california);
				l.can_travel_cost_list.add(10.0);
				
				// Can travel to Texas for $5
				l.can_travel_list.add(texas);
				l.can_travel_cost_list.add(5.0);
			}
			else if(l.name.equals("Texas")) {
				// Can travel to Arizona City for $10
				l.can_travel_list.add(arizona);
				l.can_travel_cost_list.add(10.0);
				
				// Can travel to Mexico for $5
				l.can_travel_list.add(mexico);
				l.can_travel_cost_list.add(5.0);
			}
		}
		// Give each location a Travel object
		for(Location l : all_locations) {
			l.travel = new Travel(l);
		}
	}

	/**
	 * Tells you where you are and what the current prices are. Also checks for repairs
	 * , upgrades, guns, and alcohol busts. Then calls list_option()
	 */
	public void arrive() {
		// Charge interest on all banks
		check_and_charge_interest();
		
		Location l = p.get_location();
		print("Welcome to " + l.name);
		
		// Wagon repair
		check_repair(l);
		
		// Wagon upgrade
		check_upgrade(l);
			
		// Gun purchase
		check_gun_purchase(l);
		
		// Print prices
		print("\nPrices:");
		
		// Right now just the selling list because both lists should be the same
		// Will need to change for later versions
		for(Product pr : l.market.selling_product_list) {
			print(pr.getName() + ": $" + pr.getPrice());
		}
		// Check for alcohol bust
		l.travel.bust_alcohol(l);
		
		// List the players option or show menu
		list_options();
	}

	/**
	 * Check and then maybe offer a gun purchase to the player
	 * 
	 * @param l The current location so we can check if that location sells guns
	 */
	private void check_gun_purchase(Location l) {
		// Add random option
		if(l.can_buy_guns || p.num_guns == 0) { // If the player has no more guns. Should be able to buy one
			l.market.offer_firearms();
		}
	}

	/**
	 * Check and then maybe offer a upgrade purchase to the player
	 * 
	 * @param l The current location so we can check if that location can offer upgrades
	 */
	private void check_upgrade(Location l) {
		// Add random option
		if(l.can_upgrade) {
			l.market.offer_upgrade();
		}
	}

	/**
	 * Check and then maybe offer a wagon repair to the player
	 * 
	 * @param l The current location so we can check if that location can repair wagons
	 */
	private void check_repair(Location l) {
		if(l.can_repair && p.health < p.WAGON_HEALTH_POINTS)
			l.market.offer_repair();
	}

	// Gives you options of what you can do
	public void list_options() {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		print("\nHealth: " + p.health + " remaining");
		print("Cash: $" + p.cash);
		print("Available Space: " + p.available_space + " spaces");
		print("\nWhere to?");
		print("1. Market");
		print("2. Bank");
		print("3. Inventory");
		print("4. Travel");
		choice = p.frame.get_input();
		if(!p.run_gui) {
			if(choice.equals("")) {
				try {
					choice = bufRead.readLine().trim().toLowerCase();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (choice.equals("1"))
			market();
		else if (choice.equals("2"))
			bank();
		else if (choice.equals("3"))
			inventory();
		else if (choice.equals("4"))
			travel();
		else if(choice.equals("end"))
			return;
		else {
			print("Error: Please select one of the available options");
			list_options();
		}
	}
	private void bank() {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		Bank bank = p.get_location().bank;
		print("Welcome to the Bank of " + p.get_location().name);
		print("You have $" + bank.saving_amount + " in your saving");
		print("You owe $" + bank.loan_amount);
		print("1. Take out loan");
		print("2. Pay off loan");
		print("3. Deposit money");
		print("4. Withdraw money");
		print("\nEnter \"Back\" to return to menu");
		try {
			choice = bufRead.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (choice.equals("1"))
			bank.offer_loan();
		else if (choice.equals("2"))
			bank.pay_debt();
		else if (choice.equals("3"))
			bank.deposit();
		else if (choice.equals("4"))
			bank.withdraw();
		list_options();
	}

	// Shows what all you have and how much of it
	public void inventory() {
		print("Wheat = " + p.num_wheat);
		print("Meat = " + p.num_meat);
		print("Fruit = " + p.num_fruit);
		print("Alcohol = " + p.num_alcohol);
		print("\nCash = $" + p.cash);
		print("Available Space = " + p.available_space);
		print("Guns = " + p.num_guns);
		print("Wagon Capacity = " + p.capacity);
		print("\nEnter \"Back\" to return to menu");
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		try {
			@SuppressWarnings("unused")
			String str = bufRead.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		list_options();
	}

	public void market() {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		Location loc = p.get_location();
		// Print start up text
		loc.market.print_market_text();
		String choice = "";
		try {
			choice = bufRead.readLine().toLowerCase().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (choice.equals("1")) {
			loc.market.print_buy_afford("Buy");
			loc.market.buy(); // Get rid of the "buy" part
			market();
		} else if (choice.equals("2")) {
			loc.market.print_sell();
			loc.market.sell(); // Get rid of "sell" part
			market();
		} else if (choice.equals("3")) {
			loc.market.print_buy_afford("Afford");
			loc.market.afford(); // Get rid of "afford" part
			market();
		} else {
			list_options();
		}
	}

	// Prompts you where you can travel and how much it costs
	public void travel() {
		print("\nTravel:");
		p.get_location().travel.print_locations();
		print("Enter \"Back\" to stay where you are.");
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		try {
			choice = bufRead.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean ok_to_travel = p.get_location().travel.travel_helper(choice);
		if(ok_to_travel) {
			p.get_location().travel.turn_num++;
			arrive();
		}
		else
			list_options();
	}
	private void check_and_charge_interest() {
		for(Location loc : all_locations) {
			if(loc.bank.loan_amount > 0.0) {
				// Amount is the amount of interest being added to the loan
				double amount = loc.market.prices.round_two_decimals(loc.bank.loan_amount * loc.bank.loan_interest_rate) - loc.bank.loan_amount;
				p.stat.add_loan_interest(amount);
				loc.bank.loan_amount = loc.market.prices.round_two_decimals(loc.bank.loan_amount * loc.bank.loan_interest_rate);
			}
			if(loc.bank.saving_amount > 0.0) {
				// Amount is the amount of interest earned
				double amount = loc.market.prices.round_two_decimals(loc.bank.saving_amount * loc.bank.saving_interest_rate) - loc.bank.saving_amount;
				p.stat.add_saving_interest(amount);
				loc.bank.saving_amount = loc.market.prices.round_two_decimals(loc.bank.saving_amount * loc.bank.saving_interest_rate);	
			}
		}
	}
	
	/**
	 * Will print a given string to the console and to the JFrame GUI
	 * 
	 * @param str The String to be printed to both console and JFrame
	 */
	private void print(String str) {
		System.out.println(str);
		p.print_to_frame(str);
	}
}
