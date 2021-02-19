package equipment;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Inventory {

	private String csvFilePath = "";
	
	private int row = 0;
	
	private int column = 0;
	
	private String[][] itemsFixed = new String[0][];
	
	private List<String> ownedItems = new ArrayList<String>();
	
	protected boolean hasItems;
	
	/**
	 * <h1> </h1>
	 * <p>
	 * Inventory constructor that creates arrays besed on the
	 * csv files
	 * @param int row, int column, String csvFilePath
	 * @returns void
	 * @throws none
	 * Jan. 29, 2021
	 *
	 * @see {@link Inventory#Inventory()}
	 * @author Charles Ayeni
	 * @version 1.0
	 * @since 1.5
	 *</p>
	 */
	public Inventory(int row, int column, String csvFilePath) {
		this.row = row;
		this.column = column;
		setCsvFilePath(csvFilePath);
		
		String[][] items = new String[row][column];
		
		String[][] itemsFixed = new String[row-1][column];
		
		this.itemsFixed = itemsFixed;
		try {
			createItems(items);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (CsvException e) {
			
			e.printStackTrace();
		}
		transferArray(this.itemsFixed, items);
		/*
		for(int i = 0; i < itemsFixed.length; ++i) {
			for (int j = 0; j < 3;++j) {
				System.out.print(itemsFixed[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	
	/**
	 * <h1> </h1>
	 * <p>
	 * Inventory constructor that ask the user what options they would like to see
	 * in their inventory
	 * @param void
	 * @returns void
	 * @throws none
	 * Jan. 29, 2021
	 *
	 * @see {@link Inventory#Inventory(int, int, String)}
	 * @author Charles Ayeni
	 * @version 1.0
	 * @since 1.5
	 *</p>
	 */
	@SuppressWarnings("resource")
	public Inventory() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("What would you like to do?:"
				+ "\n1. Check inventory items");
		int userInput = scnr.nextInt();
		if (userInput == 1) {
			viewOwnedItems();
		}
		else {
			
		}
	}


	private void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}
	
	/**
	 * Create array containing all items
	 * @param String[][] to
	 * @since 1.0
	 * @author Charles Ayeni
	 * @throws IOException, CsvException
	 */
	private void createItems(String[][] to) throws IOException, CsvException {
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
	            CSVReader csvReader = new CSVReader(reader);
	        ) {
	            // Reading Records One by One in a String array
	            String[] nextRecord;
	            int column = 0;
	            int row = 0;
	            int i = this.column;
	            int k = 0;
	            while ((nextRecord = csvReader.readNext()) != null) {
	            	for(int j = 0; j < i;++j) {
	            		to[row][column + k] = nextRecord[k];
	            		++k;
	            	}
	                ++row;
	                k = 0;
	            }
	        }
		}
	/**
	 * <h1> </h1>
	 * <p>
	 * addOwnedItems
	 * @param
	 * @return
	 * Jan. 29, 2021
	 * @author
	 *</p>
	 */
	protected void addOwnedItems(String item) {
		ownedItems.add(item);
	}
	/**
	 * <h1> </h1>
	 * <p>
	 * removeOwnedItems
	 * @param
	 * @return
	 * Jan. 29, 2021
	 * @since 1.5
	 * @author
	 *</p>
	 */
	protected void removeOwnedItems(String item) {
		ownedItems.remove(item);
	}
	/**
	 * <h1> </h1>
	 * <p>
	 * viewOwnedItems
	 * @param
	 * @return
	 * Jan. 29, 2021
	 * @since 1.5
	 * @author
	 *</p>
	 */
	protected void viewOwnedItems() {
		System.out.println(ownedItems);;
	}
	

	/**
	 * <h1> </h1>
	 * <p>
	 * transferArray
	 * Removes the header and store elements in new array
	 * @param String[][] weaponsFixed, String[][] weapon
	 * @return none
	 * Jan. 29, 2021
	 * @since 1.0
	 * @author Charles Ayeni
	 *</p>
	 */
	private void transferArray(String[][] itemsFixed, String[][] items){
		for(int row = 0; row < this.row-1;++row) {
			for(int column = 0; column < this.column; ++column) {
				itemsFixed[row][column] = items[row+1][column];
			}
		}
	}
	
	public String[][] arReturn () {
		return this.itemsFixed;
	}
	
	protected void getOwnedItems() {
		
	}
}
