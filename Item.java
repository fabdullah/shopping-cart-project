/**
 * Constructor and variables for Item superclass, as well as methods
 * Solves EE422C programming assignment #3
 * @author Fatima Abdullah, Jai Bock Lee
 * @version 1.8 2016-2-24
 * 
 * UTEID: fa449, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package Assignment3;
import java.util.Comparator;

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

    public static Comparator<Item> NameComparator = new Comparator<Item>() {

    
   /******************************************************************************
    * Method Name: insert                                             
    * Purpose: Processes insert command                                     
    * Returns: None                                                          
    ******************************************************************************/	
    	
	public int compare(Item n1, Item n2) {
	   String Name1 = n1.getName();
	   String Name2 = n2.getName();
	   return Name1.compareTo(Name2);
    }};
	
    /******************************************************************************
     * Method Name: calculatePrice                                             
     * Purpose: Calculates item price                                     
     * Returns: Price as a float                                                          
     ******************************************************************************/	
    
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float) (quantity*price*1.1 + (20*weight)*quantity);
		return final_price;
	}
	
	
	/******************************************************************************
     * Method Name: printItemAttributes                                             
     * Purpose: Prints attributes of item on console                                     
     * Returns: None                                          
     ******************************************************************************/	
	
	void printItemAttributes () 
	{
		System.out.println(
				"Item: " + this.name + 
				" Quantity: " + this.quantity + 
				" Price: " + String.format("%.02f", this.calculatePrice())
				);
	}
	
	
	/******************************************************************************
     * Method Name: getName                                             
     * Purpose: Fetches item name                                     
     * Returns: String name                                          
     ******************************************************************************/
	
	String getName ()
	{
		return this.name;
	}
	
	
	/******************************************************************************
     * Method Name: getQuantity                                             
     * Purpose: Fetches item quantity                                     
     * Returns: Int quantity                                         
     ******************************************************************************/
	
	int getQuantity ()
	{
		return this.quantity;
	}
	
	
	/******************************************************************************
     * Method Name: getPrice                                             
     * Purpose: Fetches item price                                     
     * Returns: Real value of price                                        
     ******************************************************************************/
	
	float getPrice ()
	{
		return this.price;
	}
	
	
	/******************************************************************************
     * Method Name: getWeight                                             
     * Purpose: Fetches item weight                                     
     * Returns: Real value of weight                                     
     ******************************************************************************/
	
	float getWeight ()
	{
		return this.weight;
	}
	
	/******************************************************************************
     * Method Name: setWeight                                             
     * Purpose: Updates item weight                                     
     * Returns: None                                     
     ******************************************************************************/
	
	void setQuantity (int quantity)
	{
		this.quantity = quantity;
	}

}
