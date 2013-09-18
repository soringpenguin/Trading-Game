
public class Player {
	
	private static final double STARTING_CASH_AMOUNT = 50.0;
	private static final int WAGON_CAPACITY = 20;
	private final int STARTING_NUM_GUNS = 1;
	int WAGON_HEALTH_POINTS = 100;
	
	// Wagons hit points
	int health;
	int capacity;
	double cash;
	int num_guns;
	int available_space;
	int num_wheat;
	int num_fruit;
	int num_meat;
	int num_alcohol;
	private Location cur_local;
	String market_log;
	StatTracker stat;
	
	boolean run_gui = false;
	
	GUI frame;
	
	public Player() {
		frame = new GUI(run_gui);
		health = WAGON_HEALTH_POINTS;
		cash = STARTING_CASH_AMOUNT;
		num_guns = STARTING_NUM_GUNS;
		num_wheat = 0;
		num_meat = 0;
		num_alcohol = 0;
		capacity = WAGON_CAPACITY;
		available_space = WAGON_CAPACITY;
		market_log = "";
		stat = new StatTracker(frame);
	}
	
	public void set_location(Location starting) {
		cur_local = starting;
	}
	
	public Location get_location() {
		return cur_local;
	}

	public void buy_wheat(double amount, double price) {
		num_wheat += amount;
		available_space -= amount;
		cash -= amount * price;
		stat.add_to_market_log(cur_local, "wheat", amount, price, true);
	}

	public void buy_meat(double amount, double price) {
		num_meat += amount;
		available_space -= amount;
		cash -= amount * price;
		stat.add_to_market_log(cur_local, "meat", amount, price, true);
	}

	public void buy_fruit(double amount, double price) {
		num_fruit += amount;
		available_space -= amount;
		cash -= amount * price;
		stat.add_to_market_log(cur_local, "fruit", amount, price, true);
	}

	public void buy_alcohol(double amount, double price) {
		num_alcohol += amount;
		available_space -= amount;
		cash -= amount * price;
		stat.add_to_market_log(cur_local, "alcohol", amount, price, true);
	}

	public void sell_wheat(double amount, double price) {
		if(amount > num_wheat)
			amount = num_wheat;
		num_wheat -= amount;
		available_space += amount;
		cash += amount * price;
		stat.add_to_market_log(cur_local, "wheat", amount, price, false);
	}
	
	public void sell_meat(double amount, double price) {
		num_meat -= amount;
		available_space += amount;
		cash += amount * price;
		stat.add_to_market_log(cur_local, "meat", amount, price, false);
	}
	
	public void sell_fruit(double amount, double price) {
		num_fruit -= amount;
		available_space += amount;
		cash += amount * price;
		stat.add_to_market_log(cur_local, "fruit", amount, price, false);
	}
	
	public void sell_alcohol(double amount, double price) {
		num_alcohol -= amount;
		available_space += amount;
		cash += amount * price;
		stat.add_to_market_log(cur_local, "alcohol", amount, price, false);
	}
	
	public void travel(Location new_local, double cost) {
		new_local.market.set_prices(); // Generates new prices for the new location
		stat.add_to_travel_log(cur_local, new_local, cost);
		cur_local = new_local;
		cash -= cost;
	}
	
	// Add the passed int to the health
	public void health_change(int change) {
		health += change;
	}
	
	public void print_to_frame(String str) {
		frame.print(str);
	}

}
