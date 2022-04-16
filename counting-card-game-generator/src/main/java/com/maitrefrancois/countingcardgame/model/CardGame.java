package com.maitrefrancois.countingcardgame.model;

/**
 * Represents a card game for a given number of players (one card per player).
 * It contains several CardSets so that real-life players (once the game has
 * been printed) can choose if they want to use easy, medium, hard or harder
 * difficulty cards.
 * 
 * @author Francois
 *
 */
public class CardGame {
	private CardSet easyCardSet = new CardSet();
	private CardSet mediumCardSet = new CardSet();
	private CardSet hardCardSet = new CardSet();
	private CardSet harderCardSet = new CardSet();

	/**
	 * Creates a CardGame according to specified values.
	 * 
	 * @param players    The number of players that will use the game (so that
	 *                   proper number of cards can be generated).
	 * @param lowerBound Minimum card value (inclusive).
	 * @param upperBound Maximum card value (exclusive).
	 */
	public CardGame(int players, int lowerBound, int upperBound) {
		CardSetFactory factory = new CardSetFactory(players, lowerBound, upperBound);
		easyCardSet = factory.build(Difficulty.EASY);
		mediumCardSet = factory.build(Difficulty.MEDIUM);
		hardCardSet = factory.build(Difficulty.HARD);
		harderCardSet = factory.build(Difficulty.HARDER);
	}

	/**
	 * Returns the CardSet containing easy cards.
	 * 
	 * @return A reference to the easy CardSet
	 */
	public CardSet getEasyCardSet() {
		return easyCardSet;
	}

	/**
	 * Returns the CardSet containing medium cards.
	 * 
	 * @return A reference to the medium CardSet
	 */
	public CardSet getMediumCardSet() {
		return mediumCardSet;
	}

	/**
	 * Returns the CardSet containing hard cards.
	 * 
	 * @return A reference to the hard CardSet
	 */
	public CardSet getHardCardSet() {
		return hardCardSet;
	}

	/**
	 * Returns the CardSet containing harder cards.
	 * 
	 * @return A reference to the easy CardSet
	 */
	public CardSet getHarderCardSet() {
		return harderCardSet;
	}

	/**
	 * Returns the number of player this game is meant to.
	 * 
	 * @return Number of players meant to use the game.
	 */
	public int getPlayers() {
		// All card sets are the same size
		return easyCardSet.size();
	}

}
