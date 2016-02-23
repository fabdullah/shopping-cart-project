package Assignment3;

public class Clothing extends Item 
{

	// variables, constructors as necessary
	
	public Clothing(String name, float price, int quantity, float weight)
	{
		super(name, price, quantity, weight);
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		final_price = (float) (price*1.1 + (20*weight)*quantity);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		//Print all applicable attributes of this sub-class
	}
	

}
