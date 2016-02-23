package assignment3;

public class Grocery extends Item {

	protected boolean perishable;
	
	public Grocery(String name, float price, int quantity, float weight, boolean perishable)
	{
		super(name, weight, quantity, weight);
		this.perishable = perishable;
	}
		
	float calculatePrice () 
	{
		float final_price = 0;

		if(this.perishable == true)
		{
		    final_price = (float) (price*1.1 + 1.2*((20*weight)*quantity));
		}
		else
		{
			final_price = (float) (price*1.1 + (20*weight)*quantity);
		}
		
		return final_price;
	}
	
}
