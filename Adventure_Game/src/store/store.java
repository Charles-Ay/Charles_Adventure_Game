/**
 * Program Name:Adventure_Game
 * Purpose: 
 * Coder: Charles Ayeni
 * Date: Jan. 11, 2021
 *
 * Version: 1.0
 */

package store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import game.Adventure_Game;
import player.player;
import equipment.Armour;
import equipment.wepons;

public class store extends Adventure_Game{

	/**
	 * Creates a store to purchase items
	 * @returns void
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	public store(player player1) {
		if (player1.getPlayerMoney() > 0) {//as long as player has money they can access store
			Scanner scnr = new Scanner(System.in);
			System.out.println("WELCOME UNCLE J's ALL AROUND STORE");
			System.out.println("\t*note: equipment prices may vary based on current economy*");
			
			boolean running = true;
			
			while (running) {//run
				System.out.println("----------------------------------------------");
				options();//show options
				String userChoice = scnr.nextLine();
				if (userChoice.equals("1")) {
					wepons wep = new wepons();//new weapon object
					
					System.out.println("What type of weapon would you like?: "
							+ "\n1. Sword"
							+ "\n2. Bow"
							+ "\n3. Cancel");
					userChoice = scnr.nextLine();
					
					WEPON:
					while (true) {
						if(userChoice.equals("1")) {
							ArrayList<String> count = new ArrayList <String>();//list to hold damage
							
							Map <Integer, String> weponBonusDmg = new HashMap<Integer, String>();//map for damage and reference value
	
							if (getSword(weponBonusDmg, count, wep, scnr, player1) == true) {//call getSword
								userChoice = scnr.nextLine();//buffer flush
								System.out.println("Gold Left: " + player1.getPlayerMoney());
								break WEPON;//break loop
							}
							else {
								continue;
							}
						}
						else if(userChoice.equals("2")) {
							ArrayList<String> count = new ArrayList <String>();//list to hold damage
							
							Map <Integer, String> weponBonusDmg = new HashMap<Integer, String>();//map for damage and reference value
							
							if (getBow(weponBonusDmg, count, wep, scnr, player1) == true) {//call get Bow
								userChoice = scnr.nextLine();//buffer flush
								System.out.println("Gold Left: " + player1.getPlayerMoney());
								break WEPON;//break loop
							}
							else {
								continue;
							}
						}
						else if(userChoice.equals("3")) {
							break WEPON;//break loop
						}
						else {
							System.out.println("INVALID INPUT");
							System.out.println("What type of wepon would you like?: "
									+ "\n1. Sword"
									+ "\n2. Bow"
									+ "\n3. Cancel");
							userChoice = scnr.nextLine();
						}
					}
				}
				else if (userChoice.equals("2")) {
					Armour ar = new Armour();
					
					ARMOUR://armour label
						while (true) {
							ArrayList<String> count = new ArrayList <String>();//list to hold health
							
							Map <Integer, String> armourHP = new HashMap<Integer, String>();//map for health and reference value
							if(getArmour(count, armourHP, ar, scnr, player1) == true) {//call get armour
								userChoice = scnr.nextLine();//buffer flush
								System.out.println("Gold Left: " + player1.getPlayerMoney());
								break ARMOUR;
							}
						}
				}
				else if (userChoice.equals("3")) {
					System.out.println("Potion price is: 10 gold each\nHow many would you like to buy");
					int amount = scnr.nextInt();
					if (amount > 0) {
						if(player1.getPlayerMoney() >= amount * 10) {
							player1.addNumHealthPotions(amount);
							player1.subtractMoney(amount * 10);
							System.out.println("Current potions amount is: " + player1.getNumHealthPotions() );
							System.out.println("Gold Left: " + player1.getPlayerMoney());
							userChoice = scnr.nextLine();
						}
						else System.err.println("INSUFFICENT FUNDS");
					}
					else {
						System.err.println("INVALID. PLEASE TRY AGAIN");
						amount = scnr.nextInt();
					}
					
				}
				else if (userChoice.equals("4")) {
					break;
				}
			}
			
		}
		else {
			System.out.println("Go to the dungeon to make some money");
		}
	}
	private static void options() {
		System.out.println("What would you like to do?: "
				+ "\n1. Buy wepons"
				+ "\n2. Buy armour"
				+ "\n3. Buy potions"
				+ "\n4. EXIT");
	}
	
	private static boolean getSword(Map<Integer, String> weponBonusDmg , ArrayList<String> count, wepons wep, Scanner scnr, player player1) {
		ArrayList<String> wepName = new ArrayList <String>();//store weapon name
		ArrayList<Integer> itemPrice = new ArrayList <Integer>();//store the price
		
		wep.showSword(weponBonusDmg, count, wepName, itemPrice);//call the show sword method
		System.out.println();
		System.out.println("Which would you like to buy(Enter -1 to cancel)");
		int wepChoice = scnr.nextInt();
		while(weponBonusDmg.get(wepChoice) != null) {//ensure the there is input
			if(weponBonusDmg.get(wepChoice) != null) {
				if(player1.getPlayerMoney() >= itemPrice.get(wepChoice -1)) {//check if player has enough money
					player1.subtractMoney(itemPrice.get(wepChoice -1));//subtract money
					if(wep.isWeponEquiped()) {//check if weapon is equipped
						wep.unequipeWepon(player1);//unequip
						wep.setCurrentEquipedWepon(wepName.get(wepChoice-1));//change the current weapon name
						System.out.println("You have equiped: " + wep.getCurrentEquipedWepon());//tell user they have equipped the item
						wep.setCurrentEquipedWeponDmg(Integer.parseInt(weponBonusDmg.get(wepChoice)));//add the weapon damage
						wep.equipeWepon(player1);//equip
					}
					else {//if weapon is not equipped
						wep.setCurrentEquipedWepon(wepName.get(wepChoice-1));//change the current weapon name
						System.out.println("You have equiped: " + wep.getCurrentEquipedWepon());//tell user they have equipped the item
						wep.setCurrentEquipedWeponDmg(Integer.parseInt(weponBonusDmg.get(wepChoice)));//add the weapon damage
						wep.equipeWepon(player1);//equip
					}
				}
				else {
					System.err.println("INSUFFICENT FUNDS");
					return true;
				}
				return true;
			}
		}
		if(wepChoice == -1)return true;
		else if(weponBonusDmg.get(wepChoice) == null)System.err.println("INVALID OPTION. TRY AGAIN");
		return false;
	}
	private static boolean getBow(Map<Integer, String> weponBonusDmg , ArrayList<String> count, wepons wep, Scanner scnr, player player1) {
		ArrayList<String> wepName = new ArrayList <String>();//store weapon name
		ArrayList<Integer> itemPrice = new ArrayList <Integer>();//store the price
		
		wep.showBow(weponBonusDmg, count, wepName, itemPrice);//call the show bow method
		
		System.out.println();
		System.out.println("Which would you like to buy(Enter -1 to cancel)");
		int wepChoice = scnr.nextInt();
		while(weponBonusDmg.get(wepChoice) != null) {//ensure the there is input
			if(weponBonusDmg.get(wepChoice) != null) {
				if(player1.getPlayerMoney() >= itemPrice.get(wepChoice -1)) {//check if player has enough money
					player1.subtractMoney(itemPrice.get(wepChoice -1));//subtract money
					if(wep.isWeponEquiped()) {//check if weapon is equipped
						wep.unequipeWepon(player1);//unequip
						wep.setCurrentEquipedWepon(wepName.get(wepChoice-1));//change the current weapon name
						System.out.println("You have equiped: " + wep.getCurrentEquipedWepon());//tell user they have equipped the item
						wep.setCurrentEquipedWeponDmg(Integer.parseInt(weponBonusDmg.get(wepChoice)));//add the weapon damage
						wep.equipeWepon(player1);//equip
					}
					else {//if weapon is not equipped
						wep.setCurrentEquipedWepon(wepName.get(wepChoice-1));//change the current weapon name
						System.out.println("You have equiped: " + wep.getCurrentEquipedWepon());//tell user they have equipped the item
						wep.setCurrentEquipedWeponDmg(Integer.parseInt(weponBonusDmg.get(wepChoice)));//add the weapon damage
						wep.equipeWepon(player1);//equip
					}
				}
				else {
					System.err.println("INSUFFICENT FUNDS");
					return true;
				}
				return true;
			}
		}
		if(wepChoice == -1)return true;
		else if(weponBonusDmg.get(wepChoice) == null)System.err.println("INVALID OPTION. TRY AGAIN");
		return false;
	}
	
	private static boolean getArmour(ArrayList<String> count, Map<Integer, String> armourHP, Armour ar, Scanner scnr, player player1) {
		ArrayList<Integer> itemPrice = new ArrayList <Integer>();//store armour price
		ArrayList<String> armourName = new ArrayList <String>();//store armour name
		
		ar.showArmour(count, armourHP, itemPrice, armourName);//call show armour
		
		System.out.println();
		System.out.println("Which would you like to buy(Enter -1 to cancel)");
		
		int arChoice = scnr.nextInt();
		while(armourHP.get(arChoice) != null) {//ensure the there is input
			if(armourHP.get(arChoice) != null) {
				if(player1.getPlayerMoney() >= itemPrice.get(arChoice -1)) {//check if player has enough money
					player1.subtractMoney(itemPrice.get(arChoice -1));//subtract money
					if(ar.isArmourEquiped()) {//check if armour is equipped
						ar.unequipArmour(player1);//unequip
						ar.setCurrentEquipedArmour(armourName.get(arChoice-1));//change the current armour name
						System.out.println("You have equiped: " + ar.getCurrentEquipedArmour());//tell user they have equipped the item
						ar.setCurrentEquipedArmourHealth(Integer.parseInt(armourHP.get(arChoice)));//add armour health
						ar.equipArmour(player1);//equip
					}
					else {
						ar.setCurrentEquipedArmour(armourName.get(arChoice-1));//change the current armour name
						System.out.println("You have equiped: " + ar.getCurrentEquipedArmour());//tell user they have equipped the item
						ar.setCurrentEquipedArmourHealth(Integer.parseInt(armourHP.get(arChoice)));//add armour health
						ar.equipArmour(player1);//equip
					}
				}
				else {
					System.err.println("INSUFFICENT FUNDS");
					return true;
				}
				return true;
			}
		}
		if(arChoice == -1)return true;
		else if(armourHP.get(arChoice) == null)System.err.println("INVALID OPTION. TRY AGAIN");
		return false;
	}

	
}
