package game;

import java.util.Random;
import java.util.Scanner;

import enemies.enemies;
import player.player;

public class Dungeon extends Adventure_Game{

	private static int dmgDone;
	private static int dmgTaken;
	private static int enemyHP;
	private static int enemyLevel;
	private static int healthPotionDropChance = 50;//%

	
	public Dungeon(player player1, enemies enemy1) {
		enemy1.resetDefeated();
			
		System.out.println("STUB PLAYER! " + player1.getHealth());
		if(player1.getHealth() <= 0) {
			System.err.println("\t#You have 0 health#\n\t#Health has been reset#");
			player1.addHP(player1.getMaxPlayerHP());
		}
		Scanner scnr = new Scanner(System.in);
		Random rand = new Random();
		
		boolean running = true;
		
		System.out.println("WELCOME THE EPIC DUNGEON");
		
		
		GAME://game label
		while (running) {///run
			enemy1.getNewEnemyInfo(enemy1.getEnemiesFixed());//create new enemy
			
			System.out.println("----------------------------------------------");
			enemyHP = enemy1.getMaxEnemyHP();//get enemyHP
			
			enemyLevel();//get enemy level
			enemyDmg(enemy1);//get enemy damage
			
			System.out.println("\t# A level " + enemyLevel + " " + enemy1.getEnemyName() + " has appered: #\n");
			
			while(enemyHP > 0) {
				
				if(player1.getHealth() < 1) {
					System.err.println("\t> You have taken too much damage, you are too weak to go on");
					break GAME;
				}
				
				System.out.println("\tYour HP: " + player1.getHealth());
				System.out.println("\t" + enemy1.getEnemyName() + "'s HP: " + enemyHP + "\n");
				System.out.println("\tWhat would you like to do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Use HP potion");
				System.out.println("\t3. RUN!!!");
				
				String input  = scnr.next();//gets user options

				if (input.equals("1")) {
				attack(rand, player1, enemy1);
				}
				else if (input.equals("2")) {
					potions(rand, player1, enemy1);
				}
					
					
				else if (input.equals("3")) {
					System.out.println("\tEscaped from the " + enemy1.getEnemyName()+ "!");
					continue GAME;
				}
				else {
					System.err.println("\tInvalid Command");
				}
			}
			
			if(player1.getHealth() < 1) {
				System.out.println("\tHealth Low. You bearly limped out of the dungeon");
				break;
			}
			
			enemy1.addDefeated(1);//Amount of enemies defeated
			
			int money = moneyDropped(player1);//add the loot
			
			System.out.println("----------------------------------------------");
			System.out.println(" # " + enemy1.getEnemyName() + " was defeated! #");
			
			System.out.println(" # " + money + " gold was dropped! #");
			
			//potion drop chance
			if(rand.nextInt(100) < healthPotionDropChance){//number between 0 and 100
				player1.addNumHealthPotions(1);
				System.out.println(" # The " + enemy1.getEnemyName() + " dropped a health pot");
				System.out.println(" # You now have " + player1.getNumHealthPotions() + " health potion(s). #");
			}
			
			System.out.println("----------------------------------------------");
			System.out.println("\tWhat would you like to do?");
			System.out.println("\t1. Continue fighting");
			System.out.println("\t2. Exit dungeon");
			
			String input = scnr.nextLine();
			input = scnr.next();
			
			while(!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid Option");
				input = scnr.nextLine();
			}
			
			if(input.equals("1")) {
				System.out.println("You continue on your adventure");
			}
			else if(input.equals("2")) {
				System.out.println("You exit the dungeon suceessfuly");
				break;
			}
		}
		//scnr.close();	
		
		if (enemy1.getDefeated() == 1)System.out.println("You defeated " + enemy1.getDefeated() + " enemy\n");
		else System.out.println("You defeated " + enemy1.getDefeated() + " enemies\n");
		System.out.println("######################");
		System.out.println("# THANKS FOR PLAYING #");
		System.out.println("######################");
		System.out.println();
		scnr.nextLine();
	}

	/**
	 * gets the user attack
	 * @param Random rand
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void attack(Random rand, player player1, enemies enemy1) {
		dmgDone = (int)(Math.random() * (player1.getPlayerAttackDmg() - (player1.getPlayerAttackDmg()/5.5) + 1) + (player1.getPlayerAttackDmg()/5.5));
		dmgTaken = rand.nextInt(enemy1.getEnemyDamage());//random number for damage taken

		enemyHP -= dmgDone;
		player1.subtractHealth(dmgTaken);
		
		
		System.out.println("\t> You hit the " + enemy1.getEnemyName() + " for " + dmgDone + " damage");
		if (dmgTaken > 30)System.out.println("\t> ! ENEMY CRITICAL HIT !");
		System.out.println("\t> You take " + dmgTaken + " points of damage in return");
		
		if(enemyHP < 1) {
			System.err.println("\tYou have slain the " + enemy1.getEnemyName() + "\n");
		}
		
		else {
			System.out.println("");
		}
	}
	
	/**
	 * gets enemy attack
	 * @param Random rand
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void attackEm(Random rand, player player1, enemies enemy1) {
		dmgTaken = rand.nextInt(enemy1.getEnemyDamage());
		player1.subtractHealth(dmgTaken);
		
		System.out.println("\t> You take " + dmgTaken + " points of damage from " + enemy1.getEnemyName());
		
		System.out.println("");
	}
	
	/**
	 * use a potion as long as u have them
	 * @param Random rand
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void potions(Random rand, player player1, enemies enemy1) {
		if (player1.getNumHealthPotions() > 0 && player1.getHealth() + player1.getHealthPotionHealAmount() <= player1.getMaxPlayerHP() ) {
			
			player1.addHP(player1.getHealthPotionHealAmount());
			player1.subtractNumHealthPotions(1);
			System.out.println("\t> Drank health potion. You have healed " 
			+ player1.getHealthPotionHealAmount() + " HP"
			+"\n\t> You have " + player1.getNumHealthPotions() + " potions left\n");
			
		}
		else if (player1.getNumHealthPotions()> 0) {
			
			player1.subtractNumHealthPotions(1);
			System.out.println("\t> Drank health potion. You have healed " 
			+ (player1.getMaxPlayerHP()- player1.getHealth())+ " HP"
			+"\n\t> You have " + player1.getNumHealthPotions() + " potions left\n");
			player1.setHealthToMax() ;
		}
		else {
			System.err.println("\t> No potions left :(\n");
			attackEm(rand, player1, enemy1);	
		};
	}
	/*
	 * FOR FUTURE UPDATE
	public static void itemStats(String[] lowTeirItems) {
		
		
	}
	*/
	
	/**
	 * gives the user the dropped money from enemy
	 * @return money
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static int moneyDropped(player player1) {
		int money = (int)(Math.random() * (8 -3 + 1) + 3);
		player1.addMoney(money);
		return money;
	}
	
	/**
	 * gets enemy level
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void enemyLevel() {
		enemyLevel = (int)(Math.random() * (3 -1 + 1) + 1);
	}

	/**
	 * gets enemy damage
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void enemyDmg(enemies enemy1) {
		if (enemyLevel > 1)	enemy1.setEnemyDamage((int)((enemyLevel * enemy1.getEnemyDamage())/1.32));
	}
}
