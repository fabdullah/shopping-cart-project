package assignment3;

import java.util.List;
import java.util.Arrays;

public class Electronics extends Item 
{
	protected boolean fragile;
	protected String state;
	List<String> validStates = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", 
			                                 "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", 
			                                 "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
			                                 "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", 
			                                 "VT", "VA", "WA", "WV", "WI", "WY");
	List<String> noTaxStates = Arrays.asList("TX", "NM", "VA", "AZ", "AK");
	
	public Electronics(String name, float price, int quantity, float weight, boolean fragile, String state)
	{
		super(name, weight, quantity, weight);
		this.fragile = fragile;
		this.state = state;
	}
		
	float calculatePrice () 
	{
		float final_price = 0;
		float tax = 0;
		
		if(!validStates.contains(state))
		{
			//throw exception
		}
		else if(validStates.contains(state) && !noTaxStates.contains(state))
		{
			tax = (float) (price*.1);
		}

		if(this.fragile == true)
		{
		    final_price = (float) (price + tax + 1.2*((20*weight)*quantity));
		}
		else
		{
			final_price = (float) (price + tax + (20*weight)*quantity);
		}
		
		return final_price;
	}

}
