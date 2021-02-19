/**
 * Program Name:RPG_Items
 * Purpose: 
 * Coder: Charles Ayeni
 * Date: Jan. 3, 2021
 *
 * Version: 1.0
 */

package enemies;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;



public class enemies{
	//enemy variables
	protected int enemyDmg = 25;
	private String enemyName;
	private int maxEnemyHP = 75;

	private int defeated;
	private String[][] enemiesFixed = new String[5][3];
	
	private static final String CSV_FILE_PATH = "enemies.csv";
	
	public enemies () {
		String[][] enemies = new String[6][3];
		
		try {
			createEneimes(enemies);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transferArray(getEnemiesFixed(), enemies);
		
		getNewEnemyInfo(getEnemiesFixed());
	}
	
	/**
	 * Create array containing all enemies
	 * @param String[][] to
	 * @since 1.0
	 * @author Charles Ayeni
	 * @throws IOException, CsvException
	 */
	private void createEneimes(String[][] to) throws IOException, CsvException {
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
	            String[] nextRecord;
	            int column = 0;
	            int row = 0;
	            while ((nextRecord = csvReader.readNext()) != null) {
	                to[row][column] = nextRecord[0];//enemy name
	                to[row][column+1] = nextRecord[1];//enemy dmg
	                to[row][column+2] = nextRecord[2];//health
	                ++row;
	            }
	        }
		}
	
	/**
	 * Gets the information of a new enemy and stores it in corresponding Value
	 * @param String[][] enemiesFixed
	 * @since 1.0
	 * @author Charles Ayeni
	 * @see game.Adventure_Game
	 */
	public void getNewEnemyInfo(String[][] enemiesFixed) {
		Random rand = new Random();
		int randInt = rand.nextInt(5);
		setEnemyName(enemiesFixed[randInt][0]);
		enemyDmg = Integer.parseInt(enemiesFixed[randInt][1]);
		setMaxEnemyHP(Integer.parseInt(enemiesFixed[randInt][2]));
	}
	
	/**
	 * Removes the header and store elements in new array
	 * @param String[][] enemiesFixed, String[][] enemies 
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	
	private void transferArray(String[][] enemiesFixed, String[][] enemies){
		for(int row = 0; row < 5;++row) {
			for(int column = 0; column < 3; ++column) {
				enemiesFixed[row][column] = enemies[row+1][column];
				
			}
		}
	}

	public String[][] getEnemiesFixed() {
		return enemiesFixed;
	}

	public void setEnemiesFixed(String[][] enemiesFixed) {
		this.enemiesFixed = enemiesFixed;
	}

	public int getDefeated() {
		return defeated;
	}

	public void addDefeated(int defeated) {
		this.defeated += defeated;
	}
	public void resetDefeated() {
		this.defeated = 0;
	}

	public int getMaxEnemyHP() {
		return maxEnemyHP;
	}

	public void setMaxEnemyHP(int maxEnemyHP) {
		this.maxEnemyHP = maxEnemyHP;
	}

	public String getEnemyName() {
		return enemyName;
	}

	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}
	public int getEnemyDamage() {
		return enemyDmg;
	}
	public void setEnemyDamage(int enemyDmg) {
		this.enemyDmg = enemyDmg;
	}
}
