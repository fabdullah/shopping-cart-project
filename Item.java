package Assignment3;

public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?

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
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}

}
