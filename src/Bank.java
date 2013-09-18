import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * Currently just there to provide loans to players with no money to travel.
 * Players can now store money in a bank and earn interest
 * 
 * @author andrewfortner
 *
 */
public class Bank {
	
	Player player;
	double loan_amount;
	double loan_interest_rate;
	double saving_interest_rate;
	double saving_amount; 
	Prices prices;

	public Bank(Player p) {
		player = p;
		loan_amount = 0.0;
		loan_interest_rate = 0.0;
		saving_amount = 0.0;
		saving_interest_rate = 1.02;
		prices = new Prices();
	}
	
	public void deposit() {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		double choice = 0.0;
		// Maybe make loan offers random
		print("Enter your deposit amount: ");
		try {
			choice = prices.round_two_decimals(Double.parseDouble(bufRead.readLine().trim()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			print("Error: Please enter a numerical value.");
			return;
		}
		if(choice > player.cash) {
			print("You do not have that much cash.");
			deposit();
		}
		else if(choice < 0) {
			print("You cannot deposit a negative amount of money.");
			deposit();
		}
		else {
			saving_amount += choice;
			player.cash -= choice;
			player.stat.add_deposit(choice);
			print("You deposited $" + choice);
		}
	}
	
	public void withdraw() {
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		double choice = 0.0;
		// Maybe make loan offers random
		print("Enter your withdraw amount: ");
		try {
			choice = prices.round_two_decimals(Double.parseDouble(bufRead.readLine().trim()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			print("Error: Please enter a numerical value.");
			return;
		}
		if(choice > saving_amount) {
			print("You do not have that much cash to withdraw.");
			withdraw();
		}
		else if(choice < 0) {
			print("You cannot withdraw a negative amount of money.");
			withdraw();
		}
		else {
			saving_amount -= choice;
			player.cash += choice;
			player.stat.add_withdraw(choice);
			print("You withdrew $" + choice);
		}
	}
	
	// Make sure cant take out multiple loans or figure out how that would work
	public void offer_loan() {
		if(loan_amount >= 200.0) {
			print("You owe too much money to take out anymore loans.");
			return;
		}
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		String choice = "";
		// Maybe make loan offers random
		print("Loan Options:");
		print("1. $50 loan with a 5% interest rate per travel");
		print("2. $100 loan with a 7% interest rate per travel");
		print("3. $200 loan with a 8% interest rate per travel");
		print("Enter \"Back\" to decline all loans");
		try {
			choice = bufRead.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
			accept_loan(choice);
			return;
		}
		else {
			print("You declined to take out a loan.\n");
			return;
		}		
	}
		
	private void accept_loan(String choice) {
		if(choice.equals("1")) {
			player.cash += 50.0;
			player.stat.add_take_loan(50.0);
			loan_amount += 50.0;
			if(loan_interest_rate < 1.05)
				loan_interest_rate = 1.05; // 5%
		}
		else if(choice.equals("2")) {
			player.cash += 100.0;
			player.stat.add_take_loan(100.0);
			loan_amount += 100.0;
			if(loan_interest_rate < 1.07)
				loan_interest_rate = 1.07; // 7%
		}
		else if(choice.equals("3")) {
			player.cash += 200.0;
			player.stat.add_take_loan(200.0);
			loan_amount += 200.0;
			if(loan_interest_rate < 1.08)
				loan_interest_rate = 1.08; // 8%
		}
		else {
			print("Error: Please enter one of the options.");
			offer_loan();
			return;
		}
	}
	
	public void pay_debt() {
		if(loan_amount > 0.0) {
			print("Enter how much you would like to pay back (will be rounded): ");
			InputStreamReader istream = new InputStreamReader(System.in);
			BufferedReader bufRead = new BufferedReader(istream);
			String choice = "";
			try {
				choice = bufRead.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			double amount = 0.0;
			try {
				amount = prices.round_two_decimals(Double.parseDouble(choice));
			}
			catch (NumberFormatException e) {
				print("Error: Please enter a numerical value.");
				pay_debt();
				return;
			}
			if(amount < 0) {
				print("Error: You cannot enter a negative amount of cash.");
				pay_debt();
				return;
			}
			else if(amount > loan_amount) {
				print("Well you didn't owe that much but you paided your debt.");
				amount = loan_amount;
				player.cash -= amount;
				player.stat.add_pay_loan(amount);
				loan_amount -= amount;
				loan_interest_rate = 0.0; // Reset loan interest
			}
			else {
				player.cash -= amount;
				player.stat.add_pay_loan(amount);
				loan_amount -= amount;
				if(loan_amount == 0)
					loan_interest_rate = 0.0; // Reset loan interest
			}
		}
		else
			print("You have no debt to pay off.\n");
	}
	
	private void print(String str) {
		System.out.println(str);
		player.print_to_frame(str);
	}
	
}
