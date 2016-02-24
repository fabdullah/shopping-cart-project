package Assignment3;

public class Grocery extends Item {

	private boolean perishable;

	public Grocery() {
		super();
		salesTax = 0.0;
	}

	public Grocery(String itemName, double itemPrice, int itemWeight, int itemQuantity, String perish) {
		super(itemName, itemPrice, itemWeight, itemQuantity);
		perishable = (perish.toUpperCase()).equals("P");
		salesTax = 0.0;
	}


	protected double shippingCost() {
		if(perishable)
			return super.shippingCost() * 0.2;
		return super.shippingCost();
	}


	public void printItemAttributes ()
	{
		System.out.format("Item: %s\n" + "Price: $%.2f\n" + "Quantity: %d\n" + "Weight: %d\n" + "Perishable: %s\n"
				+ "Total Price: $%.2f\n\n",
				name, price, quantity, weight, perishable?"yes":"no", calculatePrice());
	}
}