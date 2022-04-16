package com.maitrefrancois.countingcardgame.model;

/**
 * This class defines an interface and common methods for all CardSet builders.
 * A CardSetBuilder is meant to build a CardSet according to user parameters
 * (number of players, minimum card value, maximum card value) and internal
 * parameters related to the builder's difficulty (easy, medium, hard, harder).
 * 
 * @author Francois
 *
 */
public abstract class CardSetBuilder {

	private int players = 0;
	private int lowerBound = 0;
	private int upperBound = 0;

	/**
	 * Returns the number of cards used by this builder.
	 * 
	 * @return Number of cards used by this builder.
	 */
	public int getCardSetSize() {
		return players;
	}

	/**
	 * Returns lowest possible card value used by this builder.
	 * 
	 * @return Lowest possible card value.
	 */
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * Returns highest possible card value used by this builder.
	 * 
	 * @return Highest possible card value.
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * Sets the number of players we need to generate a game for.
	 * 
	 * @param players Number of players the CardSet is aimed at.
	 * @return this CardSetBuilder.
	 */
	public CardSetBuilder setPlayers(int players) {
		this.players = players;
		return this;
	}

	/**
	 * Sets minimum / maximum card value used for CardSet generation.
	 * 
	 * @param lowerBound Minimum card value to use.
	 * @param upperBound Maximum card value to use.
	 * @return this CardSetBuilder
	 */
	public CardSetBuilder setBounds(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		return this;
	}

	/**
	 * To be implemented by subclasses
	 * 
	 * @return The generated CardSet
	 */
	public abstract CardSet build();

}
