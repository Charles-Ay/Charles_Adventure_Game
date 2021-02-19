package equipment;


import java.util.ArrayList;
import java.util.Map;

import player.player;

public class Armour extends Inventory{
	private static final String CSV_FILE_PATH = "armour.csv";
	
	private String[][] itemsFixed = new String[2][2];
	
	private static int columns = 2;
	private static int rows = 3;
	
	//Armour
	private String currentEquipedArmour;
	private int currentEquipedArmourHealth;
	private boolean armourEquiped = false;

	
	
	/**
	 * Armour constructor
	 * @since 1.0
	 * @author Charles Ayeni
	 * @param player1 
	 */
	public Armour() {
		super(rows, columns, CSV_FILE_PATH);
		this.itemsFixed = arReturn();
		/*
		for(int i = 0; i < itemsFixed.length; ++i) {
			for (int j = 0; j < 2;++j) {
				System.out.print(itemsFixed[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}

	
	/**
	 * Shows the player the information about the armour and adds a price for the
	 * armour based on the starting characters
	 * @param ArrayList<String> count, Map<Integer, String> armourHP, ArrayList<Integer> itemPrice, ArrayList<String> armourName
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 * @see store.store
	 */
	public void showArmour(ArrayList<String> count, Map<Integer, String> armourHP, ArrayList<Integer> itemPrice, ArrayList<String> armourName) {
		int counter = 0;//counter for the choice they player will pick
		int price = 0;//armour price
		
		for(int i = 0; i < itemsFixed.length; ++i) {
			for (int j = 0; j < 1;++j) {
				if (itemsFixed[i][j].contains("Starter")) {//starter price
					price = (int)(Math.random() * (20 - 13 + 1)) + 13;
				}
				if (itemsFixed[i][j].contains("Novice")) {//novice price
					price = (int)(Math.random() * (30 - 21 + 1)) + 21;
				}
				//display the armour information for the user to decide which one they want
				System.out.println((counter + 1) + ". " + itemsFixed[i][0] + ", +"
						+ itemsFixed[i][1] + " Health ------ Price: " + price);
				itemPrice.add(price);//add the price to the arrayList
				//name may be misleading but the count is used to keep track of 
				//the amount of health provided by the armour
				count.add(itemsFixed[i][1]);//add the health stat to arraylist
				//add the counter as a key of the map and use health as a values
				armourHP.put(counter + 1,count.get(counter));
				//add the name of the armour to the arraylist
				armourName.add(itemsFixed[i][0]);
				++counter;//increment counter
			}
		}
	}
	
	/**
	 * unequip armour
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	public void unequipArmour(player player1) {
		setCurrentEquipedArmour("");
		player1.subtractMaxHP(getCurrentEquipedArmourHealth());
		setArmourEquiped(false);
	}
	/**
	 * equip armour
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	public void equipArmour(player player1) {
		player1.addMaxHP(getCurrentEquipedArmourHealth());
		setArmourEquiped(true);
	}

	public String getCurrentEquipedArmour() {
		return currentEquipedArmour;
	}

	public void setCurrentEquipedArmour(String currentEquipedArmour) {
		this.currentEquipedArmour = currentEquipedArmour;
	}

	public boolean isArmourEquiped() {
		return armourEquiped;
	}

	public void setArmourEquiped(boolean armourEquiped) {
		this.armourEquiped = armourEquiped;
	}

	public int getCurrentEquipedArmourHealth() {
		return currentEquipedArmourHealth;
	}

	public void setCurrentEquipedArmourHealth(int currentEquipedArmourHealth) {
		this.currentEquipedArmourHealth = currentEquipedArmourHealth;
	}
}
