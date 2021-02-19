/**
 * Program Name:RPG_Items
 * Purpose: 
 * Coder: Charles Ayeni
 * Date: Jan. 3, 2021
 *
 * Version: 1.0
 */

package equipment;


import java.util.ArrayList;
import java.util.Map;

import player.player;



public class wepons extends Inventory{
	//Weapons
	private String currentEquipedWepon;
	private int currentEquipedWeponDmg;
	private boolean weponEquiped = false;

	private static int columns = 3;
	private static int rows = 5;


	private static final String CSV_FILE_PATH = "wepons_info.csv";
	
	private String[][] itemsFixed = new String[0][];
	

	public wepons() {
		super(rows, columns, CSV_FILE_PATH);
		this.itemsFixed = arReturn();
	}
	
	

	/**
	 * Shows the player the information about the weapons and adds a price for the
	 * weapons based on the starting characters
	 * @param Map<Integer, String> inputData, ArrayList<String> count, ArrayList<String> wepName, ArrayList<Integer> itemPrice
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 * @see store.store
	 */
	public void showBow (Map<Integer, String> inputData, ArrayList<String> count, ArrayList<String> wepName, ArrayList<Integer> itemPrice) {
		int counter = 0;//counter for the choice they player will pick
		int price = 0;//weapon price
		
		for(int i = 0; i < itemsFixed.length; ++i) {
			if (itemsFixed[i][0].equals("Bow")) {
				for (int j = 0; j < 1;++j) {
					if (itemsFixed[i][j+1].contains("Starter")) {//starter price
						price = (int)(Math.random() * (20 - 13 + 1)) + 13;
					}
					if (itemsFixed[i][j+1].contains("Novice")) {//novice price
						price = (int)(Math.random() * (30 - 21 + 1)) + 21;
					}
					//display the weapon information for the user to decide which one they want
					System.out.print((counter + 1) + ". " + itemsFixed[i][j+1] + ", +"
					+ itemsFixed[i][2] + " Damage ------ Price: " + price);
					
					itemPrice.add(price);//add the price to the arrayList
					//name may be misleading but the count is used to keep track of 
					//the amount of damage provided by the weapon
					count.add(itemsFixed[i][2]);//add the damage stat to arraylist
					//add the counter as a key of the map and use damage as a values
					wepName.add(itemsFixed[i][1]);//add the name of the weapon to the arraylist
					inputData.put(counter + 1,count.get(counter));
					++counter;//increment counter
					
				}
				System.out.println();
			}
		}
		
	}
	
	/**
	 * Shows the player the information about the weapons and adds a price for the
	 * weapons based on the starting characters
	 * @param Map<Integer, String> inputData, ArrayList<String> count, ArrayList<String> wepName, ArrayList<Integer> itemPrice
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 * @see store.store
	 */
	
	
	public void showSword (Map<Integer, String> inputData, ArrayList<String> count, ArrayList<String> wepName, ArrayList<Integer> itemPrice) {
		int counter = 0;//counter for the choice they player will pick
		int price = 0;//weapon price
		
		for(int i = 0; i < itemsFixed.length; ++i) {
			if (itemsFixed[i][0].equals("Sword")) {
				for (int j = 0; j < 1;++j) {
					if (itemsFixed[i][j+1].contains("Starter")) {//starter price
						price = (int)(Math.random() * (20 - 13 + 1)) + 13;
					}
					if (itemsFixed[i][j+1].contains("Novice")) {//novice price
						price = (int)(Math.random() * (30 - 21 + 1)) + 21;
					}
					//display the weapon information for the user to decide which one they want
					System.out.print((counter + 1) + ". " + itemsFixed[i][j+1] + ", +"
					+ itemsFixed[i][2] + " Damage ------ Price: " + price);
					
					itemPrice.add(price);//add the price to the arrayList
					//name may be misleading but the count is used to keep track of 
					//the amount of damage provided by the weapon
					count.add(itemsFixed[i][2]);//add the damage stat to arraylist
					//add the counter as a key of the map and use damage as a values
					wepName.add(itemsFixed[i][1]);//add the name of the weapon to the arraylist
					inputData.put(counter + 1,count.get(counter));
					++counter;//increment counter
					
				}
				System.out.println();
			}
		}
	}
	
	/**
	 * unequip weapon
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	public void unequipeWepon(player player1) {
		setCurrentEquipedWepon("");
		player1.subtractPlayerAttackDamage(getCurrentEquipedWeponDmg());
		setWeponEquiped(false);
	}
	
	/**
	 * equip weapon
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	public void equipeWepon(player player1) {
		player1.addPlayerAttackDamage(getCurrentEquipedWeponDmg());
		setWeponEquiped(true);
	}

	public boolean isWeponEquiped() {
		return weponEquiped;
	}

	public void setWeponEquiped(boolean weponEquiped) {
		this.weponEquiped = weponEquiped;
	}

	public String getCurrentEquipedWepon() {
		return currentEquipedWepon;
	}

	public void setCurrentEquipedWepon(String currentEquipedWepon) {
		this.currentEquipedWepon = currentEquipedWepon;
	}

	public int getCurrentEquipedWeponDmg() {
		return currentEquipedWeponDmg;
	}

	public void setCurrentEquipedWeponDmg(int currentEquipedWeponDmg) {
		this.currentEquipedWeponDmg = currentEquipedWeponDmg;
	}
}
