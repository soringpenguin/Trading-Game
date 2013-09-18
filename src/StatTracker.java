import java.util.LinkedList;


public class StatTracker {

	public static void main(String[] args) {
		
	}
	
	GUI frame;
	
	// Amount bought and sold
	int amount_wheat_bought;
	int amount_meat_bought;
	int amount_fruit_bought;
	int amount_alcohol_bought;
	int amount_wheat_sold;
	int amount_meat_sold;
	int amount_fruit_sold;
	int amount_alcohol_sold;
	
	// Keeps track of all your transactions at the market
	String market_log;
	
	// All purely market profits(+ or -)
	double wheat_profit;
	double meat_profit;
	double fruit_profit;
	double alcohol_profit;
	
	// Bank statistics variables
	double amount_loaned;
	double amount_payed_off;
	double amount_current_debt;
	double loan_interest_pile; // Amount of money lost of loan interest
	double amount_deposited;
	double amount_withdrew;
	double saving_interest_pile; // Amount of money gained through saving interest
	
	// Number of repairs
	int num_repairs;
	// Cost of repairs
	double cost_of_repair;
	// Total health repaired
	int total_health_repaired;
	
	// Number of upgrades
	int num_upgrade;	
	// Cost of upgrades
	double cost_of_upgrade;
	// Capacity added via upgrades
	int capacity_added;
	
	// Cost of all firearm purchases
	double cost_of_firearms;
	// Number of guns purchased
	int num_guns_bought;

	// Logs the number of the turn when was busted
	LinkedList<Integer> alcohol_bust_list;
	
	// Keeps track of where you go and from and how much it cost
	LinkedList<Location> travel_to_log;
	LinkedList<Location> travel_from_log;
	LinkedList<Double> travel_cost_log;
	
	// Keeps track of total travel cost
	double total_travel_cost;
	
	// Your net game profit including every transfer of cash
	double net_game_profit;
	
	double total_game_profit;
	
	// Battle section
	// Total damage taken by bandits
	int total_damage_taken;
	
	// Total bandits defeated
	int total_bandits_defeated;
	
	// Total battles that the player was in
	int total_battles;
	
	// Total battles where the player defeated the bandits
	int battles_won;
	
	// Total battles where the player ran away
	int battles_escaped;
	
	// Total money earned from defeating bandits
	double battle_money_won;
	
	
	
	public StatTracker(GUI gui){
		frame = gui;
		
		amount_wheat_bought = 0;
		amount_meat_bought = 0;
		amount_fruit_bought = 0;
		amount_alcohol_bought = 0;
		amount_wheat_sold = 0;
		amount_meat_sold = 0;
		amount_fruit_sold = 0;
		amount_alcohol_sold = 0;
		
		amount_loaned = 0.0;
		amount_payed_off = 0.0;
		amount_current_debt = 0.0;
		loan_interest_pile = 0.0;
		amount_deposited = 0.0;
		amount_withdrew = 0.0;
		saving_interest_pile = 0.0;
		
		market_log = "Market Log:\n";
		
		alcohol_bust_list = new LinkedList<Integer>();
		
		num_repairs = 0;
		cost_of_repair = 0.0;
		total_health_repaired = 0;
		
		num_upgrade = 0;
		cost_of_upgrade = 0.0;
		capacity_added = 0;
		
		cost_of_firearms = 0.0;
		num_guns_bought = 0;
		
		travel_to_log = new LinkedList<Location>();
		travel_from_log = new LinkedList<Location>();
		travel_cost_log = new LinkedList<Double>();
		
		wheat_profit = 0.0;
		meat_profit = 0.0;
		fruit_profit = 0.0;
		alcohol_profit = 0.0;
		
		total_travel_cost = 0.0;
		
		net_game_profit = 0.0;
		
		total_game_profit = 0.0;
		
		total_damage_taken = 0;
		total_bandits_defeated = 0;
		total_battles = 0;		
		battles_won = 0;
		battles_escaped = 0;
		battle_money_won = 0.0;
	}
	
	public void add_damage_taken(int damage) {
		total_damage_taken += damage;
	}
	
	public void add_bandits_defeated() {
		total_bandits_defeated++;
	}
	
	public void add_battle_count() {
		total_battles++;
	}
	
	public void add_battles_won() {
		battles_won++;
	}
	
	public void add_battles_escaped() {
		battles_escaped++;
	}
	
	public void add_battle_money(double amount) {
		battle_money_won += amount;
	}
	
	public void print_battle_stats() {
		print("Battle Stats:");
		print("Total battles: " + total_battles);
		print("Total battles won: " + battles_won);
		print("Total battles escaped: " + battles_escaped);
		print("Total bandits defeated: " + total_bandits_defeated);
		print("Total damage taken: " + total_damage_taken);
		print("Total money won: " + battle_money_won + "\n");
	}
	
	// Adds a market transaction to the log
	public void add_to_market_log(Location l, String product, double amount, double price, boolean is_buy) {
		if(amount > 0) {
			if(is_buy) {
				market_log += "At " + l.name + ". " + "Bought " + (int)amount + " " + product + ". Cost $" + price * amount  + "\n";
			}
			else
				market_log += "At " + l.name + ". " + "Sold " + (int)amount + " " + product + ". Gained $" + price * amount  + "\n";
			
			add_product_count(product, amount, is_buy);
			add_product_profit(product, amount, price, is_buy);
			// Add to total cost
			if(is_buy)
				add_to_game_profit(-1.0 * amount * price);
			else {
				add_to_game_profit(amount * price);
			}
		}
	}
	
	public void add_to_game_profit(double amount) {
		net_game_profit += amount;
		if(amount > 0.0)
			total_game_profit += amount;
	}
	
	public void print_market_log() {
		print(market_log);
	}
	
	public void add_product_profit(String product, double amount, double price, boolean is_buy) {
		if(product.equals("wheat")) {
			if(is_buy)
				wheat_profit -= amount * price;
			else
				wheat_profit += amount * price;
		}
		else if(product.equals("meat")) {
			if(is_buy)
				meat_profit -= amount * price;
			else
				meat_profit += amount * price;
		}
		else if(product.equals("fruit")) {
			if(is_buy)
				fruit_profit -= amount * price;
			else
				fruit_profit += amount * price;
		}
		else if(product.equals("alcohol")) {
			if(is_buy)
				alcohol_profit -= amount * price;
			else
				alcohol_profit += amount * price;
		}
	}
	
	public void print_product_profit() {
		print("Product Profits:");
		print("Wheat profits = $" + wheat_profit);
		print("Meat profits = $" + meat_profit);
		print("Fruit profits = $" + fruit_profit);
		print("Alcohol profits = $" + alcohol_profit + "\n");
	}

	// If is_buy is true then it was a buy if not it was a sell
	public void add_product_count(String product, double amount, boolean is_buy) {
			if(product.equals("wheat")) {
				if(is_buy)
					amount_wheat_bought += amount;
				else
					amount_wheat_sold += amount;
			}
			else if(product.equals("meat")) {
				if(is_buy)
					amount_meat_bought += amount;
				else
					amount_meat_sold += amount;
			}
			else if(product.equals("fruit")) {
				if(is_buy)
					amount_fruit_bought += amount;
				else
					amount_fruit_sold += amount;
			}
			else if(product.equals("alcohol")) {
				if(is_buy)
					amount_alcohol_bought += amount;
				else
					amount_alcohol_sold += amount;
			}
		}
	
	public void print_product_count() {
		print("Product Amount List:");
		print("You have bought " + amount_wheat_bought + " wheat.");
		print("You have bought " + amount_meat_bought + " meat.");
		print("You have bought " + amount_fruit_bought + " fruit.");
		print("You have bought " + amount_alcohol_bought + " alcohol.");
		print("You have sold " + amount_wheat_sold + " wheat.");
		print("You have sold " + amount_meat_sold + " meat.");
		print("You have sold " + amount_fruit_sold + " fruit.");
		print("You have sold " + amount_alcohol_sold + " alcohol.\n");
		
	}
	
	// Adds where you traveled and how much it cost
	public void add_to_travel_log(Location from, Location to, double cost) {
		travel_to_log.add(to);
		travel_from_log.add(from);
		travel_cost_log.add(cost);
		// Keep track of total travel cost
		total_travel_cost -= cost;
		// Add to total cost
		add_to_game_profit(-1 * cost);
	}
	
	public void add_to_alcohol_bust(int turn_num) {
		alcohol_bust_list.add(turn_num);
	}
	
	
	// Prints off your travel log and how much each trip cost
	public void print_travel_log() {
		print("Travel Log:");
		for(int x = 0; x < travel_to_log.size(); x++) {
			print(x+1 + ". Traveled to " + travel_to_log.get(x).name + " from " + travel_from_log.get(x).name + ". Cost $" + travel_cost_log.get(x));
			// Because turn_num in Game() starts at 0
			if(alcohol_bust_list.contains(x))
				print("   Busted for Alcohol Possesion");
		}
		System.out.print("\n");
	}
	
	public void add_repair(double cost, int amount_repaired) {
		total_health_repaired += amount_repaired;
		cost_of_repair -= cost;
		add_to_game_profit(-1 * cost);
		num_repairs++;
	}
	
	public void print_repairs() {
		print("Repair Report:");
		print("Number of repairs = " + num_repairs);
		print("Total health repaired = " + total_health_repaired);
		print("Total cost of repairs = $" + cost_of_repair);
		System.out.print("\n");
	}
	
	public void add_upgrade(double cost, int capacity) {
		cost_of_upgrade -= cost;
		add_to_game_profit(-1 * cost);
		num_upgrade++;
		capacity_added += capacity;
	}
	
	public void print_upgrades() {
		print("Upgrade Report:");
		print("Number of Upgrades = " + num_upgrade);
		print("Total cost of Upgrades = $" + cost_of_upgrade);
		print("Total capacity added to wagon = " + capacity_added);
		System.out.print("\n");
	}
	
	public void add_firearm(double cost) {
		cost_of_firearms -= cost;
		add_to_game_profit(-1 * cost);
		num_guns_bought++;
	}
	
	public void print_firearms() {
		print("Gun Report:");
		print("Number of Guns purchased = " + num_guns_bought);
		print("Total cost of Guns = $" + cost_of_firearms);
		System.out.print("\n");
	}
	
	public void add_deposit(double amount) {
		amount_deposited += amount;
	}
	
	public void add_withdraw(double amount) {
		amount_withdrew += amount;
	}
	
	public void add_take_loan(double amount) {
		amount_loaned += amount;
		amount_current_debt -= amount;
	}
	
	public void add_pay_loan(double amount) {
		amount_payed_off -= amount;
		amount_current_debt -= amount;
	}
	
	public void add_loan_interest(double amount) {
		add_to_game_profit(-amount);
		loan_interest_pile += amount;
		amount_current_debt -= amount;
	}
	
	public void add_saving_interest(double amount) {
		add_to_game_profit(amount);
		saving_interest_pile += amount;
	}
	
	public void print_total_game_profit() {
		print("Total Game Profit:");
		print("$" + total_game_profit + "\n");
		print("Net Game Profit:");
		print("$" + net_game_profit + "\n");
	}
	
	public void print_total_travel_cost() {
		print("Total Travel Cost:");
		print("$" + total_travel_cost + "\n");
	}
	
	// Print all bank related statistics
	public void print_bank() {
		print("Bank Report:");
		print("Amount of loans = " + amount_loaned);
		print("Amount you payed off loans = " + amount_payed_off);
		print("Amount debt left = " + amount_current_debt);
		print("Total amount of loan interest = " + loan_interest_pile);
		print("Amount deposited = " + amount_deposited);
		print("Amount withdrew = " + amount_withdrew);
		print("Total amount of savings interest = " + saving_interest_pile);
		System.out.print("\n");
	}

	public void print_all_stats() {
		print_market_log();
		print_travel_log();
		print_product_count();
		print_product_profit();
		print_battle_stats();
		print_repairs();
		print_upgrades();
		print_firearms();
		print_bank();
		print_total_travel_cost();
		print_total_game_profit();
	}
	
	private void print(String str) {
		System.out.println(str);
		frame.print(str);
	}
	
}
