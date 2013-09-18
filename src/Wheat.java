
public class Wheat extends Product{

	public Wheat() {
		super("Wheat");
	}

	@Override
	public void generate_price(Prices p) {
		setPrice(p.get_wheat_price());
	}
	
}
