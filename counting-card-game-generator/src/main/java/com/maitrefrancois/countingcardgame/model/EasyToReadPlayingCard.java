package com.maitrefrancois.countingcardgame.model;

/**
 * An easy to read version of the PlayingCard class (includes EasyToReadNumber
 * fields).
 * 
 * @author Francois
 *
 */
public class EasyToReadPlayingCard extends PlayingCard {

	/**
	 * Constructs an EasyToReadPlayingCard with specified numbers.
	 * 
	 * @param thisCardNumber This card's number.
	 * @param nextCardNumber Next player's card number.
	 */
	public EasyToReadPlayingCard(Number thisCardNumber, Number nextCardNumber) {
		super(new EasyToReadCardNumber(thisCardNumber), new EasyToReadCardNumber(nextCardNumber));
	}

}
