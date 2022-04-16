package com.maitrefrancois.countingcardgame.model;

import java.io.IOException;

/**
 * Based on the Strategy design pattern, this interface is meant to provide an
 * easy way for the Controller to save generated game to disk in various file
 * formats.
 * 
 * @author Francois
 *
 */
public interface CardGameIOStrategy {
	/**
	 * Saves a game to specified location (generated file name).
	 * 
	 * @param cardGame        The game to be saved.
	 * @param outputDirectory The directory where to save the game.
	 * @return File name containing saved game.
	 * @throws IOException Exception thrown by the underlying FileWriter.
	 */
	String save(CardGame cardGame, String outputDirectory) throws IOException;

	/**
	 * Appends contents of a game to an existing file.
	 * 
	 * @param cardGame   The game to be saved.
	 * @param outputFile The file to save the game to.
	 * @throws IOException Exception thrown by the underlying FileWriter.
	 */
	void append(CardGame cardGame, String outputFile) throws IOException;
}
