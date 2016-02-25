/**
 * Constructor and variables for Electronics subclass, as well as methods
 * Solves EE422C programming assignment #3
 * @author Fatima Abdullah, Jai Bock Lee
 * @version 1.8 2016-2-24
 * 
 * UTEID: fa449, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package Assignment3;

import java.util.List;
import java.util.Arrays;

public class Electronics extends Item 
{
	protected boolean fragile;
	protected String state;
	static List<String> validStates = Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", 
			                                 "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", 
			                                 "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
			                                 "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", 
			                                 "VT", "VA", "WA", "WV", "WI", "WY");
	List<String> noTaxStates = Arrays.asList("TX", "NM", "VA", "AZ", "AK");
	
	public Electronics(String name, float price, int quantity, float weight, boolean fragile, String state)
	{
		super(name, price, quantity, weight);
		this.fragile = fragile;
		this.state = state;
	}
	
	
	/******************************************************************************
	* Method Name: calculatePrice                                             
	* Purpose: Calculates price                                       
	* Returns: Price as float                                                               
	******************************************************************************/
	
    @Override
	float calculatePrice () 
	{
		float final_price = 0;
		float tax = 0;
		
		if(!noTaxStates.contains(state))
		{
			tax = (float) (price*.1);
		}

		if(this.fragile == true)
		{
		    final_price = (float) (quantity*price + quantity*tax + 1.2*((20*weight)*quantity));
		}
		else
		{
			final_price = (float) (quantity*price + quantity*tax + (20*weight)*quantity);
		}
		return final_price;
	}
	
    
    /******************************************************************************
	* Method Name: validateState                                             
	* Purpose: Determines if state is tax-free or not                                       
	* Returns: Boolean stating if it is                                                              
	******************************************************************************/
    
	static boolean validateState (String destination)
	{
		if(validStates.contains(destination))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
