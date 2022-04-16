package com.maitrefrancois.countingcardgame.model;

/**
 * This Factory class is basically a shortcut to use proper CardSet builder
 * (depending on difficulty) with parameters specified during Factory's
 * initialization.
 * 
 * @author Francois
 *
 */
public class CardSetFactory {
	private int players;
	private int lowerBound;
	private int upperBound;

	/**
	 * Creates a Factory that can be used to generate CardSets.
	 * 
	 * @param players    The number of players that will use the CardSet.
	 * @param lowerBound Minimum card value.
	 * @param upperBound Maximum card value.
	 */
	public CardSetFactory(int players, int lowerBound, int upperBound) {
		this.players = players;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	/**
	 * Builds a CardSet as per parameters used during factory initialization and
	 * according to specified difficulty.
	 * 
	 * @param difficulty EASY, MEDIUM, HARD, HARDER
	 * @return The generated CardSet
	 */
	public CardSet build(Difficulty difficulty) {
		CardSetBuilder builder = null;
		switch (difficulty) {
		case EASY:
			builder = new EasyCardSetBuilder();
			break;
		case MEDIUM:
			builder = new MediumCardSetBuilder();
			break;
		case HARD:
			builder = new HardCardSetBuilder();
			break;
		case HARDER:
			builder = new HarderCardSetBuilder();
			break;
		default:
			break;
		}
		return builder.setPlayers(players).setBounds(lowerBound, upperBound).build();
	}
}
