package assignment3;

public class Item 
{
	protected String name;
	protected float price;
	protected int quantity;
	protected float weight;

	public Item(String name, float price, int quantity, float weight)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float) (price*1.1 + (20*weight)*quantity);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.println(
				"Item: " + this.name + 
				" Quantity: " + this.quantity + 
				" Price: " + this.calculatePrice()
				);
	}

}
