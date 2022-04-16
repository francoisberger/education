package com.maitrefrancois.countingcardgame.view;

import com.maitrefrancois.countingcardgame.controller.Controller;

/**
 * Interface for views in the Model / View / Controller model.
 * 
 * @author Francois
 *
 */
public interface View {
	/**
	 * Sets the controller for this view (used for feedback to the controller).
	 * 
	 * @param controller Controller to be used by the view (for feedback).
	 */
	void setController(Controller controller);

	/**
	 * Prompts the user for numbers of players using the generated game.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	void promptForPlayers(int defaultValue);

	/**
	 * Prompts the user for lowest number in the game.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	void promptForLowerBound(int defaultValue);

	/**
	 * Prompts the user for highest number in the game.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	void promptForUpperBound(int defaultValue);

	/**
	 * Prompts the user where to save generated games.
	 * 
	 * @param defaultValue Default value to be used if user input is null
	 *                     (controller given).
	 */
	void promptForOutputDirectory(String defaultValue);

	/**
	 * Prompts the user for exit (after games have been generated).
	 * 
	 */
	void promptForExit();

	/**
	 * Prints a message (display only).
	 * 
	 * @param message Message to be displayed.
	 */
	void print(String message);
}
