package com.maitrefrancois.countingcardgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a container used by the Controller to generate and store the
 * games as the user may need to generate several games at the same time. Note:
 * Note: This class actually groups container / factory since desired number of
 * games are created within the constructor. We may want to clean this up.
 * 
 * TODO Clean this up (separate container and build).
 * 
 * @author Francois
 *
 */
public class CardGames {

	private List<CardGame> games = new ArrayList<CardGame>();

	/**
	 * Builds card games for different players.
	 * 
	 * @param lowerPlayerBound Low bound for number of players.
	 * @param upperPlayerBound High bound for number of players.
	 * @param lowerBound       Low bound for card numbers.
	 * @param upperBound       High bound for card numbers.
	 */
	public CardGames(int lowerPlayerBound, int upperPlayerBound, int lowerBound, int upperBound) {
		for (int i = lowerPlayerBound; i <= upperPlayerBound; i++) {
			games.add(new CardGame(i, lowerBound, upperBound));
		}
	}

	/**
	 * Returns the generated games as a List.
	 * 
	 * @return List of generated games.
	 */
	public List<CardGame> asList() {
		// Actually a read only or a copy of the games would be safer...
		return games;
	}

}
