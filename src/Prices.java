import java.text.DecimalFormat;
import java.util.Random;


public class Prices {
	
	private static boolean is_debug = false;
	
	double TRUE_WHEAT_AVERAGE = 5.0;
	double TRUE_MEAT_AVERAGE = 10.0;
	double TRUE_FRUIT_AVERAGE = 20.0;
	double TRUE_ALCOHOL_AVERAGE = 50.0;
	
	private double wheat_average;
	private double meat_average;
	private double fruit_average;
	private double alcohol_average;

	public Prices() {
		// To make it random for game testing for myself
//		if(true) {
//			TRUE_WHEAT_AVERAGE = new Random().nextInt(1) + 4.5;
//			TRUE_MEAT_AVERAGE = new Random().nextInt(2) + 9;
//			TRUE_FRUIT_AVERAGE = new Random().nextInt(4) + 18;
//			TRUE_ALCOHOL_AVERAGE = new Random().nextInt(10) + 45;
//		}
		wheat_average = TRUE_WHEAT_AVERAGE;
		meat_average = TRUE_MEAT_AVERAGE;
		fruit_average = TRUE_FRUIT_AVERAGE;
		alcohol_average = TRUE_ALCOHOL_AVERAGE;
	}
	
	public void set_wheat_average(double avg) {
		wheat_average = avg;
	}
	
	public void set_meat_average(double avg) {
		meat_average = avg;
	}
	
	public void set_fruit_average(double avg) {
		fruit_average = avg;
	}
	
	public void set_alcohol_average(double avg) {
		alcohol_average = avg;
	}
	
	public double get_wheat_price() {
		double price_wheat = 0.0;
		Random rand = new Random();
		double range = 0;
		boolean is_plus;
		//Set wheat price
		int percentile = rand.nextInt(10);
		if(percentile < 5) {
			// 10% range from average
			if(is_debug)
				System.out.println("10%");
			range = rand.nextDouble() / (0.1 * wheat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_wheat = round_two_decimals(wheat_average + range);
			else
				price_wheat = round_two_decimals(wheat_average - range);
		}
		else if (percentile < 7){
			if(is_debug)
				System.out.println("30%");
			// 30% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.3 * wheat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_wheat = round_two_decimals(wheat_average + range);
			else
				price_wheat = round_two_decimals(wheat_average - range);
		}
		else {
			if(is_debug)
				System.out.println("60%");
			// 60% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.6 * wheat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_wheat = round_two_decimals(wheat_average + range);
			else
				price_wheat = round_two_decimals(wheat_average - range);
		}
		return price_wheat;
	}
	
	public double get_meat_price() {
		double price_meat = 0.0;
		Random rand = new Random();
		double range = 0;
		boolean is_plus;
		//Set meat price
		int percentile = rand.nextInt(10);
		if(percentile < 5) {
			// 10% range from average
			if(is_debug)
				System.out.println("10%");
			range = rand.nextDouble() / (0.1 * meat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_meat = round_two_decimals(meat_average + range);
			else
				price_meat = round_two_decimals(meat_average - range);
		}
		else if (percentile < 7){
			if(is_debug)
				System.out.println("30%");
			// 30% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.3 * meat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_meat = round_two_decimals(meat_average + range);
			else
				price_meat = round_two_decimals(meat_average - range);
		}
		else {
			if(is_debug)
				System.out.println("60%");
			// 60% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.6 * meat_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_meat = round_two_decimals(meat_average + range);
			else
				price_meat = round_two_decimals(meat_average - range);
		}
		return price_meat;
	}
	
	public double get_fruit_price() {
		double price_fruit = 0.0;
		Random rand = new Random();
		double range = 0;
		boolean is_plus;
		//Set fruit price
		int percentile = rand.nextInt(10);
		if(percentile < 5) {
			// 10% range from average
			if(is_debug)
				System.out.println("10%");
			range = rand.nextDouble() / (0.1 * fruit_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_fruit = round_two_decimals(fruit_average + range);
			else
				price_fruit = round_two_decimals(fruit_average - range);
		}
		else if (percentile < 7){
			if(is_debug)
				System.out.println("30%");
			// 30% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.3 * fruit_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_fruit = round_two_decimals(fruit_average + range);
			else
				price_fruit = round_two_decimals(fruit_average - range);
		}
		else {
			if(is_debug)
				System.out.println("60%");
			// 60% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.6 * fruit_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_fruit = round_two_decimals(fruit_average + range);
			else
				price_fruit = round_two_decimals(fruit_average - range);
		}
		return price_fruit;
	}
	
	public double get_alcohol_price() {
		double price_alcohol = 0.0;
		Random rand = new Random();
		double range = 0;
		boolean is_plus;
		//Set alcohol price
		int percentile = rand.nextInt(10);
		if(percentile < 5) {
			// 10% range from average
			if(is_debug)
				System.out.println("10%");
			range = rand.nextDouble() / (0.1 * alcohol_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_alcohol = round_two_decimals(alcohol_average + range);
			else
				price_alcohol = round_two_decimals(alcohol_average - range);
		}
		else if (percentile < 7){
			if(is_debug)
				System.out.println("30%");
			// 30% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.3 * alcohol_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_alcohol = round_two_decimals(alcohol_average + range);
			else
				price_alcohol = round_two_decimals(alcohol_average - range);
		}
		else {
			if(is_debug)
				System.out.println("60%");
			// 60% range from average
			range = rand.nextInt();
			range = rand.nextDouble() * (0.6 * alcohol_average);
			is_plus = rand.nextBoolean();
			if(is_plus == true)
				price_alcohol = round_two_decimals(alcohol_average + range);
			else
				price_alcohol = round_two_decimals(alcohol_average - range);
		}
		return price_alcohol;
	}

	public double round_two_decimals (double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        String temp = d + "";
        int period_index = temp.indexOf(".");
        double before_decimal = Double.parseDouble(temp.substring(0,period_index));
        double decimal = Double.parseDouble(temp.substring(period_index));
        if(is_debug)
			System.out.println(before_decimal + "DECIMAL:" + decimal);
        // Round to 1.0
        if(decimal > 0.875)
        	decimal = 1.0;
        // Round to 0.75
        else if(decimal > 0.625)
        	decimal = 0.75;
        // Round to 0.5
        else if(decimal > 0.375)
        	decimal = 0.5;
        // Round to 0.25
        else if(decimal > 0.125)
        	decimal = 0.25;
        // Round to 0.0
        else
        	decimal = 0.0;
        decimal += before_decimal;
        if(is_debug)
			System.out.println("D:" + decimal);
        return Double.valueOf(twoDForm.format(decimal));
	}
	
	public static void main(String[] args) {
	}
}
