package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;

public class A3Driver {
  private static ArrayList<Item> shoppingCart = new ArrayList<Item>(); // shopping cart list
	
  public static void main(String[] args){
	  try{  
		  String filename = args[0]; // name of file
		  FileReader reader = new FileReader(filename);
		  BufferedReader fhand = new BufferedReader(reader);
		  
		  String line; 
		  while((line = fhand.readLine()) != null){
			  line = line.trim();
			  String operation, data;
			  int npos = line.indexOf(' ');
			  
			  if(npos > 0){ 
				  operation = line.substring(0, npos).toLowerCase();
				  data = line.substring(npos+1);
			  }
			  else{ 
				  operation = line.toLowerCase();
				  data = null;
			  }
			  
			  try{ 
				  performCommand(operation, data);
			  }catch(IllegalArgumentException err){
				  System.err.println(err.getMessage());
				  continue;
			  }
		  }		  
		  
		  reader.close(); 
		  fhand.close();
	  }catch(ArrayIndexOutOfBoundsException err){
		  System.err.println("Failed to detect file in command line args. exiting...");
		  System.exit(1);
	  }catch(Exception err){
		  System.err.println(err.getMessage());
		  System.exit(1);
	  }
  }
  

  private static void performCommand(String command, String data){
	  command = command.trim();
	  if(command.equals("insert")){
		  insert(data);
	  }
	  else if(command.equals("search")){
		  search(data);
	  }
	  else if(command.equals("delete")){
		  delete(data);
	  }
	  else if(command.equals("update")){
		  update(data);
	  }
	  else if(command.equals("print")){
		  print(); 
	  }
	  else{ // invalid command
		  String errmsg = "Error: " + command + " is an invalid command!\n";
		  errmsg += "valid commands: insert search delete update print";
		  throw new IllegalArgumentException(errmsg);
	  }
  }
  
  private static void insert(String data){ 
	 try{
		 Item cart_item = createCartItem(data);
		 String name = cart_item.getName();
		 
		 addCartItem(cart_item);
		 
		 System.out.println(name + " was inserted into the cart.");
		 
	 }catch(NumberFormatException err){
		 System.err.println("Insert Failed!");
		 System.err.println("Could not interpret a number field.");
	 }catch(Exception err){
		 System.err.println("Insert Failed!");
		 System.err.println(err.getMessage());
	 }

  }
  
  private static void search(String data){
  	// get name
	  String name;
	  int npos = data.indexOf(' ');
	  if(npos > 0){ 
		  name = data.substring(0, npos).toLowerCase();
	  }
	  else{ 
		  name = data;
	  }
	  

	  int object_count = 0;
	  int object_quantity = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  String temp_name = temp.getName().toLowerCase();
		  if(temp_name.equals(name)){
			  object_count++;
			  object_quantity += temp.getQuantity();
		  }
	  }
	  
	  // display results
	  System.out.println("number of " + name + " objects: " + object_count);
	  System.out.println("quantity of " + name + " objects: " + object_quantity);
  }
  
  private static void delete(String data){
	// get name
	  String name;	
	  int npos = data.indexOf(' ');
	  if(npos > 0){ 
		  name = data.substring(0, npos).toLowerCase();
	  }
	  else{ //single parameter
		  name = data;
	  }
	  
	  int object_count = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  String temp_name = temp.getName();
		  if(temp_name.equals(name)){
			  cart_itr.remove();
			  object_count++;
		  }
	  }
	  
	  // display results
	  System.out.println(name + " objects deleted: " + object_count);
  }
  
  private static void update(String data){
	  try{
		  String[] fields = data.trim().split(" +");
		  boolean item_found = false;
		  String name = fields[0]; 
		  int quantity = Integer.parseInt(fields[1]);
		  
		  if(quantity < 0){
			  throw new NumberFormatException();
		  }
		  
		  Iterator<Item> cart_itr = shoppingCart.iterator();
		  while(cart_itr.hasNext()){
			  Item temp = cart_itr.next();
			  String temp_name = temp.getName();
			  if(temp_name.equals(name)){
				  if(quantity == 0){ // remove item 
					  System.out.println(name + " was found and removed from the cart (quantity 0).");
					  cart_itr.remove();
					  return;
				  }
				  else{
					  item_found = true;
					  temp.setQuantity(quantity);
					  break;
				  }
			  }
		  }
		  
		  if(item_found){
			  System.out.println(name + " quantity updated to: " + quantity);
		  }
		  else{
			  System.out.println(name + " was not found in the shopping cart.");
		  }

	  }catch(ArrayIndexOutOfBoundsException err){
		  String errmsg = "Error: invalid update command!\n";
		  errmsg += "Please enter the correct number of update fields.\n";
		  errmsg += "update <name> <quantity>";
		  throw new IllegalArgumentException(errmsg);
	  }
	  catch(NumberFormatException e){
		  String errmsg = "Error: invalid quantity!\n";
		  errmsg += "Please enter quantity as a positive integer.";
		  throw new IllegalArgumentException(errmsg);
	  }

  }
  
  private static void print(){
	  Collections.sort(shoppingCart, Item.NameComparator);
	  
	  System.out.print("\n");
	  System.out.println("Printing Shopping Cart Receipt ... ");
	  System.out.print("\n");
	  
	  float total_price = 0;
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  System.out.print("\t");
		  temp.printItemAttributes();
		  total_price += temp.calculatePrice();
	  }
	  
	  System.out.print("\n");
	  System.out.println("Total: " + String.format("%.02f", total_price));
	  System.out.println("Thank you for shopping at 422CMart. Come again.");
	  System.out.print("\n");
	  
  }

  private static void addCartItem(Item obj){
	  int obj_quantity = obj.getQuantity(); //quantity of new item
	  Iterator<Item> cart_itr = shoppingCart.iterator();
	  while(cart_itr.hasNext()){
		  Item temp = cart_itr.next();
		  int temp_quantity = temp.getQuantity(); //quantity of old item
		  if(temp.equals(obj)){
			  temp.setQuantity(temp_quantity + obj_quantity);
			  return;
		  }
	  }
	  //item does not exist in the cart
	  shoppingCart.add(obj);
  }
  
  private static Item createCartItem(String obj_string){
	  String[] fields = obj_string.trim().split(" +");
	  
	  String life = ""; 
	  String destination = ""; 
	  String category = fields[0].toLowerCase(); 
	  String name = fields[1];
	  float price = Float.parseFloat(fields[2]);
	  int quantity = Integer.parseInt(fields[3]);
	  int weight = Integer.parseInt(fields[4]);
	  		  
	  if(price < 0){
		  throw new IllegalArgumentException(name + " cannot have a negative price!");
	  }
	  else if(quantity < 0){
		  throw new IllegalArgumentException(name + " cannot have a negative quantity!");
	  }
	  else if(weight < 0){
		  throw new IllegalArgumentException(name + " cannot have a negative weight");
	  }
 
	  if(category.equals("groceries")){
		  Grocery grocery;
		  life = fields[5].toUpperCase();
		  if(life.equals("P")){ // perishable groceries
			  grocery = new Grocery(name, price, quantity, weight, true);
		  }
		  else{
			  grocery = new Grocery(name, price, quantity, weight, false);
		  }
		  return grocery;
	  }
	  else if(category.equals("electronics")){
		  Electronics electronic;
		  life = fields[5].toUpperCase();
		  destination = fields[6].toUpperCase();
		  if(Electronics.validateState(destination)){
		      if(life.equals("F")){ // fragile electronics
			      electronic = new Electronics(name, price, quantity, weight, true, destination);
	    	  }
	          else if(life.equals("NF")){
	    	      electronic = new Electronics(name, price, quantity, weight, false, destination);
		      }
	          else{ 
	    	      throw new IllegalArgumentException("Error: Invalid fields in argument");
		      }
	      }
	      else {
		      throw new IllegalArgumentException(name + " cannot ship to " + destination);
	      }
		  return electronic;
	  }

	  else if(category.equals("clothing")){
		  Clothing clothing = new Clothing(name, price, quantity, weight);
		  return clothing;
	  }
	  else{
		  String errmsg = "Error: " + category + " is an invalid item category!\n";
		  errmsg += "valid categories: groceries electronics clothing";
		  throw new IllegalArgumentException(errmsg);
	  }
	  
  }
}
