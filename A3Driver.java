package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class A3Driver
{

	protected static ArrayList<Item> shoppingCart;

	private final static String[] TAX_STATES = {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC",
			"FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MH", "MA", "MI", "FM",
			"MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW",
			"PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"};

	public static void main(String[] args){
		if (args.length != 1) {
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		String filename = args[0];

		BufferedReader reader;
		try {
			FileReader freader = new FileReader(filename);
			reader = new BufferedReader(freader);

			shoppingCart = new ArrayList<Item>();

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				if (!s.equals(""))
					process(s);
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		catch (IOException e)
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void process(String input) {
		String[] cmd = input.split("\\s+");
		if(cmd.length >= 1) {
			// detect input command
			switch (cmd[0].toLowerCase()) {
				case "insert":
					insert(cmd);
					break;
				case "delete":
					delete(cmd);
					break;
				case "search":
					search(cmd);
					break;
				case "update":
					update(cmd);
					break;
				case "print":
					if (cmd.length == 1) {
						print();
					} else {
						System.out.println("Error: Invalid input.");
					}
					break;
				default: System.out.println("Error: Invalid input.");
			}
		} else {
			System.out.println("Error: Invalid input.");
		}
	}


	public static void insert(String[] command) {
		try {
			if (command.length >= 6 && command.length <= 8) {
				String name = command[2];
				double price = Double.parseDouble(command[3]); // might give error
				int quantity = Integer.parseInt(command[4]);
				int weight = Integer.parseInt(command[5]);
				Item item = null;

				// detect input
				switch (command[1].toLowerCase()) {
					case "clothing":
						if (command.length == 6) {
							item = new Clothing(name, price, weight, quantity); // create new clothing item
						} else {
							System.out.println("Error: Invalid input.");
						}
						break;
					case "electronics":
						if (command.length == 8) {
							String fragile = command[6];
							String state = command[7];
							boolean stateValid = false; // make sure valid state
							for(String validStates: TAX_STATES) {
								if(validStates.equals(state.toUpperCase())) {
									stateValid = true;
								}
							}
							if(!stateValid) {
								System.out.println("Error: Invalid input.");
							}
							item = new Electronics(name, price, weight, quantity, fragile, state); // create new electronics item\
						} else {
							System.out.println("Error: Invalid input.");
						}
						break;
					case "groceries":
						if (command.length == 7) {
							String perishable = command[6];
							item = new Grocery(name, price, weight, quantity, perishable); // create new grocery item
						} else {
							System.out.println("Error: Invalid input1.");
						}
						break;
					default: System.out.println("Error: Invalid input.");
				}
				if (item != null) { // sort as items are inserted
					int i = 0;
					while (i < shoppingCart.size() && item.getName().toLowerCase().compareTo(shoppingCart.get(i).getName().toLowerCase()) >= 0) {
						i += 1;
					}
					shoppingCart.add(i, item);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid number input.");
		}
	}
	
	public static void delete(String[] command) {
		if(command.length == 2) {
			int index = 0;
			String temp = command[1]; 
			boolean found = false; // Determines if item is found

			while (index < shoppingCart.size()) { // Loop through arraylist
				String itemName = shoppingCart.get(index).getName(); 
				if(temp.equals(itemName)) { // item is found
					found = true;
					shoppingCart.remove(index);
				}
				else {
					index += 1;
				}
			}
			if(!found) {
				System.out.println("Error: No such item.");
			}
		} else {
			System.out.println("Error: Invalid input.");
		}
	}


	public static void search(String[] command) {
		if (command.length == 2) {
			int counter = 0;
			String temp = command[1]; // name to find
			for (Item i : shoppingCart) { // loop through shopping cart
				String itemName = i.getName();
				if (temp.equals(itemName)) {
					counter += i.getQuantity();
				}
			}
			System.out.println(counter + " objects with name " + command[1] + " found.`");
		} else {
			System.out.println("Error: Invalid input.");
		}
	}


	public static void update(String[] command) {
		try {
			if (command.length == 3) {
				String name = command[1];
				int newQuantity = Integer.parseInt(command[2]);
				boolean found = false;

				for (Item i: shoppingCart) { // loop through array
					String itemName = i.getName();
					if (name.equals(itemName)) { // item is found
						found = true;
						i.setQuantity(newQuantity);
						break;
					}
				}
				if(!found) {
					System.out.println("Error: No such item.");
				}
			} else {
				System.out.println("Error: Invalid input.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid number input.");
		}
	}


	public static void print() {
		Iterator<Item> i = shoppingCart.iterator();
		double total = 0;
		while (i.hasNext()) {
			Item temp = i.next();
			total += temp.calculatePrice();
			temp.printItemAttributes();
		}
		System.out.format("Total Charges: $%.2f\n\n", total);
	}
}