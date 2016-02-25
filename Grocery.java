/**
 * Constructor and variables for Grocery subclass, as well as methods
 * Solves EE422C programming assignment #3
 * @author Fatima Abdullah, Jai Bock Lee
 * @version 1.8 2016-2-24
 * 
 * UTEID: fa449, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package Assignment3;

public class Grocery extends Item {

	protected boolean perishable;
	
	public Grocery(String name, float price, int quantity, float weight, boolean perishable)
	{
		super(name, weight, quantity, weight);
		this.perishable = perishable;
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

		if(this.perishable == true)
		{
		    final_price = (float) (quantity*price*1.1 + 1.2*((20*weight)*quantity));
		}
		else
		{
			final_price = (float) (quantity*price*1.1 + (20*weight)*quantity);
		}
		
		return final_price;
	}
	
}
