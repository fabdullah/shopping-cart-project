package assignment3;
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

	public int compare(Item n1, Item n2) {
	   String Name1 = n1.getName();
	   String Name2 = n2.getName();
	   return Name1.compareTo(Name2);
    }};
	
	float calculatePrice () 
	{
		float final_price = 0;
		final_price = (float) (quantity*price*1.1 + (20*weight)*quantity);
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.println(
				"Item: " + this.name + 
				" Quantity: " + this.quantity + 
				" Price: " + String.format("%.02f", this.calculatePrice())
				);
	}
	
	String getName ()
	{
		return this.name;
	}
	
	int getQuantity ()
	{
		return this.quantity;
	}
	
	float getPrice ()
	{
		return this.price;
	}
	
	float getWeight ()
	{
		return this.weight;
	}
	
	void setQuantity (int quantity)
	{
		this.quantity = quantity;
	}

}
