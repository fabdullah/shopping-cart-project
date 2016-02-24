package Assignment3;

public class Electronics extends Item 
{
	protected String state;
	protected boolean fragile;
	
	private final static String[] TAX_FREE_STATES = {"TX", "NM", "VA", "AZ", "AK"};

	public Electronics() {
		super();
	}

	public Electronics(String itemName, double itemPrice, int itemWeight, int itemQuantity, String fragile, String itemState) {
		super(itemName, itemPrice, itemWeight, itemQuantity);
		this.fragile = (fragile.toLowerCase()).equals("f");
		state = itemState;
		getTaxRate();
	}

	protected void getTaxRate() {
		for(String temp: TAX_FREE_STATES) {
			if(temp.equals(state)) {
				salesTax = 0;
				return;
			}
		}
		salesTax = 0.1;
	}

	/**
	 * Gets shipping cost for item
	 * @return		Shipping cost applied to item
	 */
	protected double shippingCost() {
		if(fragile)
			return super.shippingCost() * 0.2;
		return super.shippingCost();
	}

	/**
	 * prints item attributes
	 */
	public void printItemAttributes ()
	{
		System.out.format("Item: %s\n" + "Price: $%.2f\n" + "Quantity: %d\n" + "Weight: %d\n" + "Fragile: %s\n"
				+ "State: %s\n" + "Total Price: $%.2f\n\n",
				name, price, quantity, weight, fragile?"yes":"no", state, calculatePrice());
	}
}