
public class Loc_Arizona extends Location{
		
	public Loc_Arizona(Player p) {
		super("Arizona", p);
		set_average_prices(market.prices);
	}

	private void set_average_prices(Prices p) {
		market.prices.set_wheat_average(p.TRUE_WHEAT_AVERAGE * 0.90);    // Wheat is -10%
		market.prices.set_meat_average(p.TRUE_MEAT_AVERAGE * 1.10);     // Meat is +10%
		market.prices.set_fruit_average(p.TRUE_FRUIT_AVERAGE * 0.90);   // Fruit is -10%
		market.prices.set_alcohol_average(p.TRUE_ALCOHOL_AVERAGE * 1.10); // Alcohol is +10%
	}

	@Override
	public void set_can_repair() {
		can_repair = false;
	}

	@Override
	public void set_can_upgrade() {
		can_upgrade = false;
	}
	
	@Override
	public void set_can_buy_guns() {
		can_buy_guns = false;
	}

	// Set the percentage chance that you get busted when entering the city
	@Override
	public double set_bust_percentage() {
		return 0.20;  // 20%
	}
}