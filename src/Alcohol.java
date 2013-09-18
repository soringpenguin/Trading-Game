
public class Alcohol extends Product{

	public Alcohol() {
		super("Alcohol");
	}
	
	@Override
	public void generate_price(Prices p) {
		setPrice(p.get_alcohol_price());
	}

}
