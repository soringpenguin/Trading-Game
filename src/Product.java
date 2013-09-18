
public abstract class Product {
	
	private double price;
	private String name;
	
	public Product(String n) {
		price = 0.0;
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void add_price(double amount) {
		price += amount;
	}
	
	public void setPrice(double newPrice) {
		price = newPrice;
	}
	
	public abstract void generate_price(Prices p);
	
}
