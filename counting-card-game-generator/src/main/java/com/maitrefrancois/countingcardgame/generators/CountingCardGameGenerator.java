package com.maitrefrancois.countingcardgame.generators;

import com.maitrefrancois.countingcardgame.controller.Controller;
import com.maitrefrancois.countingcardgame.view.ConsoleView;
import com.maitrefrancois.countingcardgame.view.View;

/**
 * This is the class used to run the program. It will create a controller and a
 * view so that the program can interact with user and generate games as per
 * user requirements.
 * 
 * @author Francois
 *
 */
public class CountingCardGameGenerator {

	/**
	 * Starts the program. Currently args are ignored and no command line arguments
	 * will be processed.
	 * 
	 * @param args Parameters from the command line.
	 */
	public static void main(String[] args) {
		// TODO Handle args
		// If no args.length we'll need an UI
		View view = new ConsoleView();
		Controller controller = new Controller(view);
		controller.start();
	}

}
