/**
 * Program Name:Adventure_Game
 * Purpose: RPG Adventure game
 * @author: Charles Ayeni
 * Date: Dec. 29, 2020
 *
 *@version: 1.0
 */

package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enemies.enemies;
import equipment.Inventory;
import game.Dungeon;
import player.player;
import store.store;

/**
 * Starts the game
 * @see {@link enemies#enemies()}
 * @see {@link player#player()}
 * {@link store#store()}
 * @see game.Dungeon
 */
@SuppressWarnings("unused")
public class Adventure_Game {


	//main class
	public static void main(String[] args) {
		
		/*
		 * FUTURE UPDATE
		try {
			SimpleAudioPlayer mus = new SimpleAudioPlayer();
			SimpleAudioPlayer.filePath = "acousticbreeze.wav";
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		Scanner scnr = new Scanner(System.in);
		String story = "";//string to gold the story
		int delay = 35;//delay for words
		String fileName = "story.txt" ;//story file
		String s = "Hello there stranger. Would you like to hear a story?(Y/N): ";

		delay(s);//prints the string one at a time
		
		String userChoice = scnr.nextLine();//stores userChoice
		s = playerStart(userChoice);
		
		delay(s);
		if (!s.equals("sigh okay")) {//if the response is not sigh okay
			System.out.println();
			try {
				story = getStory(fileName);//gets the story 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			delay(story, delay);//prints each character of the story
			delay();//give to to load resources
		}
		
		System.out.println();
		
		
		player player1 =  new player();//create new player
		player1.getPlayerName();//get player name
		
		enemies enemy1 = new enemies();//create new enemy
		
		boolean running = true;
		
		while (running) {//run game
			boolean done = false;
			
			while (done != true) {//ensure game loops
				options();//print the options
				userChoice = scnr.nextLine();
				if (userChoice.equals("1")) {
					Dungeon dun = new Dungeon(player1,enemy1);//create new dungeon
				}
				else if (userChoice.equals("2")) {
					store sto = new store(player1);//new store
					System.out.println();
				}
				else if (userChoice.equals("3")) {
					//Inventory in1 = new Inventory();
					System.out.println("UNDER CONSTRUCTION");
				}
				else if (userChoice.equals("4")) {
					System.out.println("Will be added in a future update\n");
				}
				else {
					System.err.println("INVALID OPTION!!\n");
				}
			}
		}
		
		scnr.close();
	}

	
	/**
	 * prints story one at a time of
	 * @param String story, int delay
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void delay(String story, int delay) {
	    for ( int i= 0; i < story.length(); i++) { 
	          // for loop delays individual String characters
	    	
	        System.out.print(story.charAt(i));//print each char
	        try {
				Thread.sleep(delay);//time delay
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    System.out.println(""); // this is the space in between lines
	}
	
	/**
	 * prints loading and delays time
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void delay() {
        try {
        	System.out.println("Loading please wait...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //time is in milliseconds
	}
	
	/**
	 * prints string one at a time
	 * @param String s
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void delay(String s) {
	    for ( int i= 0; i < s.length(); i++) { 
	          // for loop delays individual String characters

	        System.out.print(s.charAt(i));//prints each char
	        try {
				Thread.sleep(60);//sleep
				//time is in milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
	    }
	}
	
	/**
	 * stores the story in a String
	 * @param String fileName
	 * @since 1.0
	 * @author Charles Ayeni
	 * @throws IOException
	 */
	private static String getStory(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));//file reader
	    try {
	        StringBuilder sb = new StringBuilder();//string builder for story
	        String line = br.readLine();//read each line of story

	        while (line != null) {
	            sb.append(line);//add each line
	            sb.append("\n");//new line
	            line = br.readLine();//read each line of story
	        }
	        return sb.toString();//return story
	    } finally {
	        br.close();//close the reader
	    }
	}
	
	/**
	 * generates reply based on user input
	 * @param String userChoice
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static String playerStart(String userChoice) {
		if (userChoice.equals("Y")||userChoice.equals("y"))return ("Alright");
		else if (userChoice.equals("N")||userChoice.equals("n"))return("sigh okay");
		else return ("I will tell you anyway");
	}
	
	/**
	 * shows options
	 * @since 1.0
	 * @author Charles Ayeni
	 */
	private static void options() {
		System.out.println();
		System.out.println("What would you like to do?: "
				+ "\n1. Enter Dungeon"
				+ "\n2. Enter Store\n"
				+ "\n3. Check inventory");
	}

}
