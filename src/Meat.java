
public class Meat extends Product{

	public Meat() {
		super("Meat");
	}

	@Override
	public void generate_price(Prices p) {
		setPrice(p.get_meat_price());
	}

}
