
public class Fruit extends Product{

	public Fruit() {
		super("Fruit");
	}
	
	@Override
	public void generate_price(Prices p) {
		setPrice(p.get_fruit_price());
	}

}
