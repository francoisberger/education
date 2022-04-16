package com.maitrefrancois.countingcardgame.model;

import java.util.ArrayList;

/**
 * A CardSet is basically an ArrayList of cards that ensures no duplicate gets
 * added to the CardSet (just like a Set or HashSet does but we could not use
 * these classes as we need to retrieve the first element of our set of cards).
 * 
 * @author Francois
 *
 */
public class CardSet extends ArrayList<PlayingCard> {

	private static final long serialVersionUID = 6292466515423517177L;

	/**
	 * Adds a card to the set.
	 * 
	 * @return true if card was added to the set false otherwise.
	 */
	@Override
	public boolean add(PlayingCard card) {
		if (!super.contains(card)) {
			super.add(card);
			return true;
		}
		return false;
	}

}
