package com.maitrefrancois.countingcardgame.model;

/**
 * Represents game difficulty : easy, medium, hard, harder.
 * 
 * @author Francois
 *
 */
public enum Difficulty {
	/**
	 * Easy will generate game with only easy to read numbers.
	 */
	EASY,
	/**
	 * Medium will generate a game with some numbers quite hard to read (in french
	 * mostly within 70 80 and 90 or numbers with zeros where every digit is not
	 * pronounced like 1032).
	 */
	MEDIUM,
	/**
	 * Hard will generate a game with more numbers quite hard to read (in french
	 * mostly within 70 80 and 90 or numbers with zeros where every digit is not
	 * pronounced).
	 */
	HARD,
	/**
	 * Harder will generate a game with even more complex numbers plus the card's
	 * numbers will be displayed with no spaces.
	 */
	HARDER
}
