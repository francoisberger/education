package com.maitrefrancois.countingcardgame.view;

import java.util.Scanner;

import com.maitrefrancois.countingcardgame.controller.Controller;

/**
 * An implementation of the View class for user interaction through console.
 * 
 * @author Francois
 *
 */
public class ConsoleView implements View {
	private Scanner keyboard = new Scanner(System.in);
	private Controller controller;

	/**
	 * Sets the controller for this view (used for feedback to the controller).
	 * 
	 * @param controller The controller to be used by the view (for feedback).
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Prompts the user for numbers of players using the generated game. User may
	 * input a number (for example 12) or a range using X-Y (for example 12-30).
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	public void promptForPlayers(int defaultValue) {
		System.out.format("Please enter number of players (default=%d use X-Y to generate games from X to Y): ",
				defaultValue);
		String userInput = keyboard.nextLine();
		if (userInput.isEmpty()) {
			controller.setPlayers(defaultValue);
		} else {
			if (!userInput.contains("-")) {
				// Single game creation
				controller.setPlayers(Integer.parseInt(userInput));
			} else {
				// Multiple games creation
				String[] bounds = userInput.split("-", 2);
				controller.setPlayers(Integer.parseInt(bounds[0]), Integer.parseInt(bounds[1]));
			}
		}
	}

	/**
	 * Prompts the user for lowest number in the game.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	public void promptForLowerBound(int defaultValue) {
		System.out.format("Please enter lower bound for your card game (default=%d): ", defaultValue);
		String userInput = keyboard.nextLine();
		if (userInput.isEmpty()) {
			controller.setLowerBound(defaultValue);
		} else {
			controller.setLowerBound(Integer.parseInt(userInput));
		}
	}

	/**
	 * Prompts the user for highest number in the game.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	public void promptForUpperBound(int defaultValue) {
		System.out.format("Please enter upper bound for your card game (default=%d): ", defaultValue);
		String userInput = keyboard.nextLine();
		if (userInput.isEmpty()) {
			controller.setUpperBound(defaultValue);
		} else {
			controller.setUpperBound(Integer.parseInt(userInput));
		}
	}

	/**
	 * Prompts the user where to save generated games.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	public void promptForOutputDirectory(String defaultValue) {
		System.out.format("Please enter desired output directory (default=%s): ", defaultValue);
		String userInput = keyboard.nextLine();
		if (userInput.isEmpty()) {
			controller.setOutputDirectory(defaultValue);
		} else {
			controller.setOutputDirectory(userInput);
		}
	}

	/**
	 * Prompts the user for exit (after games have been generated).
	 * 
	 */
	public void promptForExit() {
		System.out.print("Generate new card set [Y/N] (default=N): ");
		String userInput = keyboard.nextLine();
		if (userInput.isEmpty() || userInput.equalsIgnoreCase("N")) {
			controller.exit();
		}
	}

	/**
	 * Prints a message (display only).
	 * 
	 * @param message Message to be displayed.
	 */
	public void print(String message) {
		System.out.println(message);
	}

}
