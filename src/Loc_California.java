
public class Loc_California extends Location{
	
	public Loc_California(Player p) {
		super("California", p);
		set_average_prices(market.prices);
	}

	private void set_average_prices(Prices p) {
		// California has high prices on everything 10% increase of true average
		market.prices.set_wheat_average(p.TRUE_WHEAT_AVERAGE * 1.10);
		market.prices.set_meat_average(p.TRUE_MEAT_AVERAGE * 1.10);
		market.prices.set_fruit_average(p.TRUE_FRUIT_AVERAGE * 1.10);
		market.prices.set_alcohol_average(p.TRUE_ALCOHOL_AVERAGE * 1.10);
	}

	@Override
	public void set_can_repair() {
		can_repair = true;
	}
	
	@Override
	public void set_can_upgrade() {
		can_upgrade = true;
	}

	@Override
	public void set_can_buy_guns() {
		can_buy_guns = false;
	}
	
	// Set the percentage chance that you get busted when entering the city
	@Override
	public double set_bust_percentage() {
		return 0.25;  // 20%
	}
}