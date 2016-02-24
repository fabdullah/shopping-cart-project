package Assignment3;

public class Item 
{
	protected String name;
	protected double price;
	protected int weight;
	protected int quantity;
	protected double salesTax;


	public Item() {
		name = "";
		price = 0.0;
		weight = 0;
		quantity = 0;
		salesTax = 0.1;
	}

	public Item(String itemName, double itemPrice, int itemWeight, int itemQuantity) {
		name = itemName;
		price = itemPrice;
		weight = itemWeight;
		quantity = itemQuantity;
		salesTax = 0.1;
	}


	public double calculatePrice () 
	{
		double final_price = 0;
		final_price = applyTax(price * quantity) + shippingCost();
		return final_price;
	}

	
	protected double applyTax(double in) {
		return in * (1 + salesTax);
	}


	protected double shippingCost() {
		return 20 * quantity * weight;
	}


	public void setQuantity(int update) {
		quantity = update;
	}


	public int getQuantity() { return quantity; }


	public String getName() { return name; }


	public void printItemAttributes () 
	{
		System.out.format("Item: %s\n" + "Price: $%.2f\n" + "Quantity: %d\n" + "Weight: %d\n" + "Total Price: $%.2f\n\n",
				name, price, quantity, weight, calculatePrice());
	}

}