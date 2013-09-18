import java.util.ArrayList;


public abstract class Location {

	String name;
	double wheat;
	double fruit;
	double meat;
	double alcohol;
	Market market;
	Player player;
	ArrayList<Product> all_products;
	ArrayList<Location> can_travel_list;
	ArrayList<Double> can_travel_cost_list;
	Travel travel;
	Bank bank;
	boolean can_repair;
	boolean can_upgrade;
	boolean can_buy_guns;
	double alcohol_bust_percentage;
		
	public Location(String n, Player p) {
		all_products = new ArrayList<Product>();
		name = n;
		setup_market();
		market = new Market(p, all_products);
		player = p;
		can_travel_list = new ArrayList<Location>();
		can_travel_cost_list = new ArrayList<Double>();
		alcohol_bust_percentage = set_bust_percentage();
		set_can_repair();
		set_can_upgrade();
		set_can_buy_guns();
		bank = new Bank(p);
	}
	
	public abstract void set_can_repair();
	public abstract void set_can_upgrade();
	public abstract void set_can_buy_guns();
	public abstract double set_bust_percentage();
	
	
	public void setup_market() {
		all_products.add(new Wheat());
		all_products.add(new Meat());
		all_products.add(new Fruit());
		all_products.add(new Alcohol());
	}
	
}