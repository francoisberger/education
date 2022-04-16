package com.maitrefrancois.countingcardgame.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is an implementation of the CardGameIOStrategy class. It is used to save
 * a CardGame into a CSV file with a generated file name.
 * 
 * @author Francois
 *
 */
public class CardGameCSVIOStrategy implements CardGameIOStrategy {

	/**
	 * Saves contents of the cardGame to CSV file.
	 * 
	 * @param cardGame        The game to be saved
	 * @param outputDirectory The name of the directory where file will be saved
	 * @return The file name containing saved game.
	 */
	@Override
	public String save(CardGame cardGame, String outputDirectory) throws IOException {
		// Generate file name
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss.SSS").format(new Date());
		String fileName = "cardgame-" + timeStamp + ".csv";
		fileName = outputDirectory + FileSystems.getDefault().getSeparator() + fileName;

		// Writer header to file
		try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName));) {
			output.write("totalPlayers;");
			output.write("currentPlayer;");
			output.write("myEasyNumber;nextEasyNumber;");
			output.write("myMediumNumber;nextMediumNumber;");
			output.write("myHardNumber;nextHardNumber;");
			output.write("myHarderNumber;nextHarderNumber;");
			output.newLine();
		}

		// Use logic within the append method to write contents of the game
		append(cardGame, fileName);
		return fileName;
	}

	/**
	 * Appends contents of the cardGame assuming CSV header has been set in file
	 * 
	 * @param cardGame   The game to be saved
	 * @param outputFile The name of the file to save to
	 */
	@Override
	public void append(CardGame cardGame, String outputFile) throws IOException {
		try (BufferedWriter output = new BufferedWriter(new FileWriter(outputFile, true));) {
			PlayingCard card;
			for (int i = 0; i < cardGame.getPlayers(); i++) {
				// Current line is player number, then easy, medium, hard, harder cards
				output.write(cardGame.getPlayers() + ";");
				output.write((i + 1) + ";");
				card = cardGame.getEasyCardSet().get(i);
				output.write(card.getThisCardNumber() + ";" + card.getNextCardNumber() + ";");
				card = cardGame.getMediumCardSet().get(i);
				output.write(card.getThisCardNumber() + ";" + card.getNextCardNumber() + ";");
				card = cardGame.getHardCardSet().get(i);
				output.write(card.getThisCardNumber() + ";" + card.getNextCardNumber() + ";");
				card = cardGame.getHarderCardSet().get(i);
				output.write(card.getThisCardNumber() + ";" + card.getNextCardNumber() + ";");
				output.newLine();
			}
		}
	}

}
