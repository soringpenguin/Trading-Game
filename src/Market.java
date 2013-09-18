import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class Market {
	
	LinkedList<Product> selling_product_list;
	LinkedList<Product> buying_product_list;
	Prices prices;
	Player player;
	ArrayList<Product> all_products;
	
	public Market(Player play, ArrayList<Product> all) {
		all_products = all;
		selling_product_list = new LinkedList<Product>();
		buying_product_list = new LinkedList<Product>();
		prices = new Prices();
		player = play;
		set_prices();
		setup_market();
	}
	
	public void print_market_text() {
		// Right now just the selling list because both lists should be the same
		// Will need to change for later versions
		print("Market Prices:");
		for(Product p : selling_product_list) {
			print(p.getName() + ": $" + p.getPrice());
		}
//		print("Buy command: buy [name of product] [amount]");
//		print("Sell command: sell [name of product] [amount]");
//		print("Sell All command: sell all");
//		print("Afford command: afford [name of product]");
		print("\n1. Buy");
		print("2. Sell");
		print("3. Afford");
		print("Enter \"Back\" to return to menu");
	}
	
	public void print_buy_afford(String name) {
		print(name + ":");
		print("1. Wheat");
		print("2. Meat");
		print("3. Fruit");
		print("4. Alcohol");
		print("Enter \"Back\" to return to menu");
	}
	
	public void print_sell() {
		print("Sell:");
		print("1. Wheat");
		print("2. Meat");
		print("3. Fruit");
		print("4. Alcohol");
		print("5. All");
		print("Enter \"Back\" to return to menu");
	}
	
	public void setup_market() {
		// Setup the buying and selling lists
		set_prices();
		for(Product p : all_products) {
			add_to_selling_list(p);
			add_to_buying_list(p);
		}
	}
	
	public void set_prices() {
		// Set prices for selling list using Prices object to generate prices
		for(Product p : all_products) {
			p.generate_price(prices);
		}
	}
	
	public void add_to_selling_list(Product p) {
		selling_product_list.add(p);
	}
	
	public void add_to_buying_list(Product p) {
		buying_product_list.add(p);
	}
	
	public void remove_from_selling_list(Product p) {
		selling_product_list.remove(p);
	}
	
	public void remove_from_buying_list(Product p) {
		buying_product_list.remove(p);
	}
	
	public void remove_all_selling_list() {
		selling_product_list.clear();
	}
	
	public void afford() {
		String what_product = "";
		boolean enough_money = false;
		int amount = 0;
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		try {
			choice = bufRead.readLine().toLowerCase().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int temp = 0;
		if (choice.equals("1")) {
			for(Product p : all_products) {
				if(p.getName().toLowerCase().equals("wheat")) {
					if(player.cash > 0)
						temp = (int) (player.cash / p.getPrice());
					else
						temp = 0;
				}
			}
			if(temp <= player.available_space)
				if(temp == 1)
					print("You can afford " + temp + " barrel of Wheat.\n");
				else
					print("You can afford " + temp + " barrels of Wheat.\n");
			else
				print("You can afford " + temp + " but can only carry " + player.available_space + " more barrels of Wheat.\n");
		} else if (choice.equals("2")) {
			for(Product p : all_products) {
				if(p.getName().toLowerCase().equals("meat")) {
					if(player.cash > 0)
						temp = (int) (player.cash / p.getPrice());
					else
						temp = 0;
				}
			}
			if(temp <= player.available_space)
				if(temp == 1)
					print("You can afford " + temp + " barrel of Meat.\n");
				else
					print("You can afford " + temp + " barrels of Meat.\n");
			else
				print("You can afford " + temp + " but can only carry " + player.available_space + " more barrels of Meat.\n");
		} else if (choice.equals("3")) {
			for(Product p : all_products) {
				if(p.getName().toLowerCase().equals("fruit")) {
					if(player.cash > 0)
						temp = (int) (player.cash / p.getPrice());
					else
						temp = 0;
				}
			}
			if(temp <= player.available_space)
				if(temp == 1)
					print("You can afford " + temp + " barrel of Fruit.\n");
				else
					print("You can afford " + temp + " barrels of Fruit.\n");
			else
				print("You can afford " + temp + " but can only carry " + player.available_space + " more barrels of Fruit.\n");
		} else if (choice.equals("4")) {
			for(Product p : all_products) {
				if(p.getName().toLowerCase().equals("alcohol")) {
					if(player.cash > 0)
						temp = (int) (player.cash / p.getPrice());
					else
						temp = 0;
				}
			}
			if(temp <= player.available_space)
				if(temp == 1)
					print("You can afford " + temp + " barrel of Alcohol.\n");
				else
					print("You can afford " + temp + " barrels of Alcohol.\n");
			else
				print("You can afford " + temp + " but can only carry " + player.available_space + " more barrels of Alcohol.\n");
		} else if (choice.equals("back") || choice.equals("no") || choice.equals("")) {
			
		} else {
			print("Error: Please enter one of the options.");
			print_buy_afford("Afford");
		}
	}
	
	public void buy() {
		String what_product = "";
		boolean enough_money = false;
		int amount = 0;
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		if (player.available_space == 0)
			print("You have no available space.");
		else {
			try {
				choice = bufRead.readLine().toLowerCase().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (choice.equals("1")) {
				print("Enter how much wheat do you want to buy:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_buy_afford("Buy");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_buy_afford("Buy");
				}
				else if (check_cash_buy("wheat", amount) == 0) {
					print("You do not have enough cash.");
					print_buy_afford("Buy");
				} 
				else {
					player.buy_wheat(amount, getPrice("wheat", true));
					what_product = "wheat.";
					enough_money = true;
				}
			} else if (choice.equals("2")) {
				print("Enter how much meat do you want to buy:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_buy_afford("Buy");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_buy_afford("Buy");
				}
				else if (check_cash_buy("meat", amount) == 0) {
					print("You do not have enough cash.");
					print_buy_afford("Buy");
				} 
				else {
					player.buy_meat(amount, getPrice("meat", true));
					what_product = "meat.";
					enough_money = true;
				}
				
			} else if (choice.equals("3")) {
				print("Enter how much fruit do you want to buy:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_buy_afford("Buy");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_buy_afford("Buy");
				}
				else if (check_cash_buy("fruit", amount) == 0) {
					print("You do not have enough cash.");
					print_buy_afford("Buy");
				} 
				else {
					player.buy_fruit(amount, getPrice("fruit", true));
					what_product = "fruit.";
					enough_money = true;
				}
			
			} else if (choice.equals("4")) {
				print("Enter how much alcohol you want to buy:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_buy_afford("Buy");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_buy_afford("Buy");
				}
				else if (check_cash_buy("alcohol", amount) == 0) {
					print("You do not have enough cash.");
					print_buy_afford("Buy");
				} 
				else {
					player.buy_alcohol(amount, getPrice("alcohol", true));
					what_product = "alcohol.";
					enough_money = true;
				}
			} else if (choice.equals("back") || choice.equals("no") || choice.equals("")) {
					
			} else {
				print("Error: Please enter one of the options.");
				print_buy_afford("Buy");
			}
		}
			if(enough_money) {
				if (amount == 1)
					print("You bought 1 barrel of " + what_product + "\n");
				else
					print("You bought " + amount + " barrels of " + what_product + "\n");
			}
	}
	
	public void sell() {
		String what_product = "";
		int amount = 0;
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		try {
			choice = bufRead.readLine().toLowerCase().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (choice.equals("1")) {
			if(player.num_wheat <= 0) {
				print("Error: You do not have any wheat to sell.");
				return;
			}
			else {
				print("Enter how much wheat do you want to sell:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_sell();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_sell();
				}
				else {
					amount = check_amount_sell("wheat", amount); 
					player.sell_wheat(amount, getPrice("wheat", false));
					what_product = "wheat.";
				}
			}
		} else if (choice.equals("2")) {
			if(player.num_meat <= 0) {
				print("Error: You do not have any meat to sell.");
				return;
			}
			else {
				print("Enter how much meat do you want to sell:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_sell();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_sell();
				}
				else {
					amount = check_amount_sell("meat", amount); 
					player.sell_meat(amount, getPrice("meat", false));
					what_product = "meat.";
				}
			}
		} else if (choice.equals("3")) {
			if(player.num_fruit <= 0) {
				print("Error: You do not have any fruit to sell.");
				return;
			}
			else {
				print("Enter how much fruit do you want to sell:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_sell();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_sell();
				}
				else {
					amount = check_amount_sell("fruit", amount); 
					player.sell_fruit(amount, getPrice("fruit", false));
					what_product = "fruit.";
				}
			}
			
		} else if (choice.equals("4")) {
			if(player.num_alcohol <= 0) {
				print("Error: You do not have any alcohol to sell.");
				return;
			}
			else {
				print("Enter how much alcohol do you want to sell:");
				try {
					amount = Integer.parseInt(bufRead.readLine().toLowerCase().trim());
				} catch (NumberFormatException e) {
					print("Error: please enter a numerical number.");
					print_sell();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (amount < 0) {
					print("Error: You cannot enter a negative number");
					print_sell();
				}
				else {
					amount = check_amount_sell("alcohol", amount); 
					player.sell_alcohol(amount, getPrice("alcohol", false));
					what_product = "alcohol.";
				}
			}
				
		} else if (choice.equals("5")) {
			sell_all();
		}else if (choice.equals("back") || choice.equals("no") || choice.equals("")) {
					
		} else {
			print("Error: Please enter one of the options.");
			print_sell();
			return;
		}
		if(choice.equals("4") || choice.equals("3") || choice.equals("2") || choice.equals("1")) {
		if (amount == 1)
			print("You sold 1 barrel of " + what_product);
		else
			print("You sold " + amount + " barrels of " + what_product);
		}
	}
	
	// make sure this has every product in the game
	public void sell_all() {
		// Sell wheat
		player.sell_wheat(player.num_wheat, getPrice("wheat", false));
		// Sell meat
		player.sell_meat(player.num_meat, getPrice("meat", false));
		// Sell fruit
		player.sell_fruit(player.num_fruit, getPrice("fruit", false));
		// Sell alcohol
		player.sell_alcohol(player.num_alcohol, getPrice("alcohol", false));
		
		print("You sold all that you had.");
	}
	
	// Checks to see if they want to sell too much and changes the amount to all
		// they have instead of an error
		private int check_amount_sell(String str, int amount) {
			if (str.contains("wheat") && amount > player.num_wheat)
				return player.num_wheat;
			else if (str.contains("meat") && amount > player.num_meat)
				return player.num_meat;
			else if (str.contains("fruit") && amount > player.num_fruit)
				return player.num_fruit;
			else if (str.contains("alcohol") && amount > player.num_alcohol)
				return player.num_alcohol;
			else
				return amount;
		}
	
	// A check to see if the player selling has any of the product
		private int has_product(String str) {
			if (str.contains("wheat") && player.num_wheat <= 0)
				return 0;
			else if (str.contains("meat") && player.num_meat <= 0)
				return 0;
			else if (str.contains("fruit") && player.num_fruit <= 0)
				return 0;
			else if (str.contains("alcohol") && player.num_alcohol <= 0)
				return 0;
			else
				return 1;
		}
	
	private double getPrice(String choice, boolean is_buy) {
		// For a buy
		if(is_buy) {
			for(Product p : selling_product_list) {
				if(choice.contains(p.getName().toLowerCase()))
					return p.getPrice();
			}
		}
		// For a sell
		else {
			for(Product p : buying_product_list) {
				if(choice.contains(p.getName().toLowerCase()))
					return p.getPrice();
			}
		}
		return 0.0;
	}

	// Check if we have that product in this market
	private boolean have_product(String choice, boolean is_buy) {
		// For a buy
		if(is_buy) {
			for(Product p : selling_product_list) {
				if(choice.contains(p.getName().toLowerCase()))
						return true;
			}
		}
		// For a sell
		else {
			for(Product p : buying_product_list) {
				if(choice.contains(p.getName().toLowerCase()))
						return true;
			}
		}
		return false;
	}

	// A check to see if you have enough cash
		private int check_cash_buy(String str, double amount) {
			if (str.contains("wheat") && player.cash - (amount * getPrice("wheat", true)) < 0)
				return 0;
			else if (str.contains("meat")
					&& player.cash - (amount * getPrice("meat", true)) < 0)
				return 0;
			else if (str.contains("fruit")
					&& player.cash - (amount * getPrice("fruit", true)) < 0)
				return 0;
			else if (str.contains("alcohol")
					&& player.cash - (amount * getPrice("alcohol", true)) < 0)
				return 0;
			else
				return 1;
		}

		public void offer_repair() {
			InputStreamReader istream = new InputStreamReader(System.in);
			BufferedReader bufRead = new BufferedReader(istream);
			double cost = get_repair_cost();
			print("Your wagon is in need of repair.");
			print("We can repair it for $" + cost);
			if(cost > player.cash)
				print("You do not have enough cash though.");
			else
				print("Enter the cost to repair: ");
			print("Enter \"Back\" or \"No\" to return to menu");
			String choice = "";
			double amount = 0.0;
			try {
				choice = bufRead.readLine().trim();
			} catch (IOException e) {
				print("Error! Please try again");
				offer_repair();
			}
			if(choice.toLowerCase().equals("no") || choice.toLowerCase().equals("back") || choice.toLowerCase().equals("")) {
				print("You decided to not repair your wagon.\n");
				return;
			}
			else {
				try {
					amount = prices.round_two_decimals(Double.parseDouble(choice));
				}
				catch (NumberFormatException e) {
					print("Error: Please enter a numercial value\n");
					offer_repair();
					return;
				}
				if(amount > cost) {
					print("Error: Please enter a value less than or equal to the cost\n");
					offer_repair();
					return;
				}	
				else if(amount < 0) {
					print("Error: You cannot enter a negative amount\n");
					offer_repair();
					return;
				}
				else if(cost <= player.cash) {
					// Pay for it
					player.cash -= amount;
					int repair_amount = 0;
					if(amount == 0.0)
						repair_amount = 0;
					else
						repair_amount = (int)((player.WAGON_HEALTH_POINTS - player.health) * (amount/cost));
					player.stat.add_repair(amount, repair_amount);
					player.health += repair_amount;
					if(player.health == player.WAGON_HEALTH_POINTS)
						print("Your wagon has been fully repaired.\n");
					else
						print("Your wagon has been repaired " + repair_amount + " health points.\n");
					return;
				}
			}
		}

		private double get_repair_cost() {
			if(player.health < 100)
				return prices.round_two_decimals(player.cash * 0.4 * ((100 - player.health)/100.0));
			else
				return 0.0;
		}

		public void offer_upgrade() {
			InputStreamReader istream = new InputStreamReader(System.in);
			BufferedReader bufRead = new BufferedReader(istream);
			double cost = get_upgrade_cost();
			// How much the capacity gets upgraded
			int CAPACITY = 10;
			String choice = "";
			print("An upgrade for your wagon is available for " + CAPACITY + "+ wagon capacity");
			print("We can upgrade it for $" + cost);
			if(cost > player.cash)
				print("You do not have enough cash though.");
			else {
				print("Enter the exact cost to upgrade: ");
				print("Enter \"Back\" or \"No\" to return to menu");
				try {
					choice = bufRead.readLine().trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(choice.toLowerCase().equals("no") || choice.toLowerCase().equals("back") || choice.toLowerCase().equals("")) {
				print("You decided to not upgrade your wagon.\n");
				return;
			}
			else {
				double amount = 0.0;
				try {
					amount = Double.parseDouble(choice);
				}
				catch (NumberFormatException e) {
					print("Error: Please enter a numerical number\n");
					offer_upgrade();
					return;
				}
				if(amount < 0) {
					print("Error: You cannot enter a negative amount\n");
					offer_upgrade();
					return;
				}	
				else if(amount != cost) {
					print("Error: Please enter a value equal to the cost\n");
					offer_upgrade();
					return;
				}
				// Upgrade
				else {
					// Pay for it
					player.cash -= cost;
					// upgrade
					player.capacity += CAPACITY;
					player.available_space += CAPACITY;
					// Add to statistics
					player.stat.add_upgrade(cost, CAPACITY);
					print("You upgraded your wagon.\n");
					return;
				}
			}
		}

		private double get_upgrade_cost() {
			if(player.cash <= 100) {
				return 75.0;
			}
			else {
				Random rand = new Random();
				return prices.round_two_decimals(100 + (rand.nextDouble() * 20));
			}
		}

		public void offer_firearms() {
			InputStreamReader istream = new InputStreamReader(System.in);
			BufferedReader bufRead = new BufferedReader(istream);
			double cost = get_firearm_cost();
			String choice = "";
			print("A gun is availible for purchase.");
			print("It costs $" + cost);
			if(cost > player.cash)
				print("You do not have enough cash though.");
			else {
				print("Enter the cost to purchase: ");
				print("Enter \"Back\" to return to menu\n");
				try {
					choice = bufRead.readLine().trim();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(choice.toLowerCase().equals("no") || choice.toLowerCase().equals("back") || choice.toLowerCase().equals("")) {
				if(cost <= player.cash)
					print("You decided to not buy the gun.");
				return;
			}
			else {
				double amount = 0.0;
				try {
					amount = Double.parseDouble(choice);
				}
				catch (NumberFormatException e) {
					print("Error: Please enter a numerical number\n");
					offer_firearms();
					return;
				}
				if(amount < 0) {
					print("Error: You cannot enter a negative amount\n");
					offer_firearms();
					return;
				}	
				else if(amount != cost) {
					print("Error: Please enter a value equal to the cost\n");
					offer_firearms();
					return;
				}
				// buy gun
				else {
					// Pay for it
					player.cash -= cost;
					// add new gun
					player.num_guns++;
					// Add to statistics
					player.stat.add_firearm(cost);
					return;
				}
			}
		}
		
		private double get_firearm_cost() {
			Random rand = new Random();
			return prices.round_two_decimals(player.cash * (rand.nextDouble() * .1) + 5);
		}
		
		private void print(String str) {
			System.out.println(str);
			player.print_to_frame(str);
		}

}
