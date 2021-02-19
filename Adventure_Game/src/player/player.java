package player;

import java.util.Scanner;




public class player{
	//player variables
	private int health;
	private int maxPlayerHP;
	private int playerAttackDmg;
	private int numHealthPotions = 3;
	private int healthPotionHealAmount = 30;
	protected String playerName;
	private int playerMoney = 40;
	/**
	 * Create new player
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	
	public player() {
		BasicPlayerStats();
		
	}
	
	/**
	 * gets the name a player
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 */
	
	@SuppressWarnings("resource")
	public void getPlayerName() {
	Scanner scnr = new Scanner(System.in);
	System.out.println("What is you Name?: ");
	playerName = scnr.nextLine();
	//scnr.close();
	}
	
	/**
	 * Create array containing all enemies
	 * @param String[][] to
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 */
	
	private void BasicPlayerStats() {
		maxPlayerHP = 100;
		health = maxPlayerHP;
		setPlayerAttackDmg(getPlayerAttackDmg() + 50);
		setMaxPlayerHP(getHealth());
	}

	public int getNumHealthPotions() {
		return numHealthPotions;
	}
	
	/**
	 * subtracts 1 from the amount of health potions
	 * @param int numHealthPotions
	 * @return numHealthPotions -1
	 * @author Charles Ayeni
	 */
	
	//player damage
	public int getPlayerAttackDmg() {
		return playerAttackDmg;
	}

	public void setPlayerAttackDmg(int playerAttackDmg) {
		this.playerAttackDmg = playerAttackDmg;
	}
	public void addPlayerAttackDamage(int amountToAdd) {
		this.playerAttackDmg += amountToAdd;
	}
	public void subtractPlayerAttackDamage(int amountToAdd) {
		this.playerAttackDmg -= amountToAdd;
	}

	
	//potions
	public int getHealthPotionHealAmount() {
		return healthPotionHealAmount;
	}

	public void setHealthPotionHealAmount(int healthPotionHealAmount) {
		this.healthPotionHealAmount = healthPotionHealAmount;
	}

	public void subtractNumHealthPotions(int numHealthPotions) {
		this.numHealthPotions -= numHealthPotions;
	}

	public void addNumHealthPotions(int i) {
		this.numHealthPotions += numHealthPotions;
	}
	
	
	//health
	public void subtractHealth(int damageTaken) {
		health -= damageTaken;
	}
	public int getMaxPlayerHP() {
		return maxPlayerHP;
	}

	public void setMaxPlayerHP(int maxPlayerHP) {
		this.maxPlayerHP = maxPlayerHP;
	}
	
	public void addMaxHP(int amountToAdd) {
		this.maxPlayerHP += amountToAdd;
	}
	public void subtractMaxHP(int amountToSubtract) {
		this.maxPlayerHP -= amountToSubtract;
	}
	
	public void addHP(int healthToAdd) {
		this.health += healthToAdd;
	}

	public void setHealthToMax() {
		this.health = this.maxPlayerHP;
	}
	public int getHealth() {
		return this.health;
	}
	
	
	//money
	public void addMoney(int amountToAdd) {
		this.playerMoney += amountToAdd;
	}
	public void subtractMoney(int amountToSubtract) {
		this.playerMoney -= amountToSubtract;
	}
	public int getPlayerMoney() {
		return this.playerMoney;
	}


	
	
}
