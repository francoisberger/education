package com.maitrefrancois.countingcardgame.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.maitrefrancois.countingcardgame.model.CardGame;
import com.maitrefrancois.countingcardgame.model.CardGameCSVIOStrategy;
import com.maitrefrancois.countingcardgame.model.CardGameIOStrategy;
import com.maitrefrancois.countingcardgame.model.CardGames;
import com.maitrefrancois.countingcardgame.view.View;

/**
 * The Controller class is responsible for card game creation/saving and user
 * interaction through the View. It is based on the MVC architecture where Model
 * View and Controller are separated in different classes.
 * 
 * @author Francois
 *
 */
public class Controller {
	enum State {
		STARTED, BUILDING, BUILT, SAVING, SAVED, STOPPING, STOPPED, EXITING
	}

	private State state;
	private View view;

	// Genetated card set
	private CardGames generatedCardGames;

	// Parameters to generate card games
	private int lowerPlayerBound;
	private int upperPlayerBound;
	private int lowerBound;
	private int upperBound;
	private String outputDirectory;

	/**
	 * Initializes a new Controller with the given view.
	 * 
	 * @param view A view to be used by this controller.
	 */
	public Controller(View view) {
		this.view = view;
		view.setController(this);
		reset();
		state = State.STOPPED;
	}

	/**
	 * Resets the Controller internal variables to their default values.
	 */
	private void reset() {
		// Init default values
		lowerPlayerBound = 20;
		upperPlayerBound = 20;
		lowerBound = 0;
		upperBound = 1000;
		outputDirectory = Paths.get("").toAbsolutePath().toString();
		generatedCardGames = null;
	}

	/**
	 * Sets the number of players to be used during card game generation. The method
	 * is used by this controller's view.
	 * 
	 * @param players Number of players used by game generation.
	 */
	public void setPlayers(int players) {
		this.lowerPlayerBound = players;
		this.upperPlayerBound = players;
	}

	/**
	 * Sets the numbers of players in case we want to create several games at a time
	 * for a different number of players (for example we can create 5 games with 10
	 * to 14 , first will be benerated for 10 players, second for 11, third for 12
	 * players...). The method is used by this controller's view.
	 * 
	 * @param lowerPlayerBound Low value for players.
	 * @param upperPlayerBound High value for players.
	 */
	public void setPlayers(int lowerPlayerBound, int upperPlayerBound) {
		this.lowerPlayerBound = lowerPlayerBound;
		this.upperPlayerBound = upperPlayerBound;
	}

	/**
	 * Sets the lower bound for card game generation i.e. no card will have a value
	 * lower than the specified value (inclusive). The method is used by this
	 * controller's view.
	 * 
	 * @param lowerBound Minimum card value (inclusive).
	 */
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * Sets the upper bound for card game generation i.e. no card will have a value
	 * greater than the specified value (exclusive). The method is used by this
	 * controller's view.
	 * 
	 * @param upperBound Maximum card value (exclusive).
	 */
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * Sets the output directory used for saving according to desired location. The
	 * method is used by this controller's view.
	 * 
	 * @param outputDirectory Path for directory where games will be saved.
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	/**
	 * Starts the controller i.e. the whole user interaction / game creation
	 * process.
	 */
	public void start() {
		if (state != State.STOPPED) {
			throw new IllegalStateException("Can not start a non starting controller !");
		}
		view.promptForPlayers(lowerPlayerBound);
		view.promptForLowerBound(lowerBound);
		view.promptForUpperBound(upperBound);
		state = State.STARTED;
		build();
	}

	private void build() {
		if (state != State.STARTED) {
			throw new IllegalStateException("Can not run a non started controller !");
		}
		state = State.BUILDING;
		generatedCardGames = new CardGames(lowerPlayerBound, upperPlayerBound, lowerBound, upperBound);
		state = State.BUILT;
		save();
	}

	private void save() {
		if (state != State.BUILT) {
			throw new IllegalStateException("Can not save non built card games !");
		}
		state = State.SAVING;

		// Retrieve the list of games to be saved with the CSV Strategy
		List<CardGame> games = generatedCardGames.asList();
		CardGameIOStrategy saver = new CardGameCSVIOStrategy();

		// If no games, nothing to save, just skip the save process
		boolean saved = false;
		if (games.isEmpty()) {
			saved = true;
		}
		// If saved required, prompt for directory then save
		while (!saved) {
			view.promptForOutputDirectory(outputDirectory);
			// Check if directory exists
			if (!outputDirectory.isEmpty() && new File(outputDirectory).exists()) {
				try {
					// Save first game
					String fileName = saver.save(games.get(0), outputDirectory);
					// Append all other games
					for (int i = 1; i < games.size(); i++) {
						saver.append(games.get(i), fileName);
					}
					saved = true;
				} catch (IOException e) {
					view.print("Unable to save game, please fix !");
					view.print(e.getMessage());
				}
			} else {
				view.print("Not a valid directory !");
			}
		}
		state = State.STOPPING;
		stop();
	}

	private void stop() {
		state = State.STOPPING;
		// Do not check current state as we may want to exit at any time
		view.promptForExit();
		if (state != State.EXITING) {
			// Be aware that if user manually generates thousands and thousands of games
			// we may get a HUGE call stack cause we are getting recursive here
			state = State.STOPPED;
			reset();
			start();
		}
	}

	/**
	 * Prepares exist by setting the internal state of the Controller.
	 */
	public void exit() {
		// Having a System.exit(0) is another option
		// But exiting normally with just unpacking the call stack with proper exit
		// shall give us better code coverage perspective
		state = State.EXITING;
	}
}
