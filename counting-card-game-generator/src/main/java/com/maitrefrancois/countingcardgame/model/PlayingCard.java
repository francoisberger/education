package com.maitrefrancois.countingcardgame.model;

/**
 * In this simple game a PlayingCard consists of two numbers : my number and
 * next player's number. First player needs to call for next player's number.
 * 
 * @author Francois
 *
 */
public class PlayingCard {
	private Number thisCardNumber;
	private Number nextCardNumber;

	/**
	 * Creates a PlayingCard with given numbers.
	 * 
	 * @param thisCardNumber Number to be used for this card.
	 * @param nextCardNumber Next player's card number.
	 */
	public PlayingCard(Number thisCardNumber, Number nextCardNumber) {
		this.thisCardNumber = thisCardNumber;
		this.nextCardNumber = nextCardNumber;
	}

	/**
	 * Returns this card's number.
	 * 
	 * @return This card's number.
	 */
	public String getThisCardNumber() {
		return thisCardNumber.toString();
	}

	/**
	 * Returns next player's card number.
	 * 
	 * @return Next player's card number.
	 */
	public String getNextCardNumber() {
		return nextCardNumber.toString();
	}
}
