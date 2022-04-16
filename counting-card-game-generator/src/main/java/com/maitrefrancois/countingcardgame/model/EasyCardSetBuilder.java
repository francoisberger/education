package com.maitrefrancois.countingcardgame.model;

import java.util.ArrayList;
import java.util.List;

import com.maitrefrancois.countingcardgame.util.RandomNumberGenerator;

/**
 * EasyCardSetBuilder, as its name suggested, will generate a CardSet following
 * easy rules. With easy numbers, we are trying to avoid 70, 80 and 90 which are
 * quite hard to read in French. We are also trying to avoid numbers with zeros
 * like 1200, 1005 or 1050 so that every digit is pronounced.
 * 
 * @author Francois
 *
 */
public class EasyCardSetBuilder extends CardSetBuilder {

	/**
	 * Builds a CardSet.
	 */
	@Override
	public CardSet build() {
		// Prepare random number generator
		RandomNumberGenerator random = new RandomNumberGenerator();
		// Filter 1 -> With easy numbers, try to avoid 70, 80 and 90 which are quite
		// hard to read in French
		// Flter 2 -> With easy numbers, try to avoid numbers with zeros like 1200, 1005
		// or 1050 so that every digit is pronounced
		random = random.notLike("([789].)|0");

		// Create random numbers and store them if not already in collection
		List<Number> generatedNumbers = new ArrayList<Number>();
		while (generatedNumbers.size() < super.getCardSetSize()) {
			Number randomNumber = random.nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}

		// Now populate the card set with cards
		CardSet cardSet = new CardSet();
		Number firstNumber = generatedNumbers.get(0);
		Number lastNumber = generatedNumbers.get(generatedNumbers.size() - 1);
		for (int i = 0; i < generatedNumbers.size(); i++) {
			Number thisCardNumber = generatedNumbers.get(i);
			// Get next number unless we're on the last one -> get the first one
			Number nextCardNumber = (thisCardNumber != lastNumber) ? generatedNumbers.get(i + 1) : firstNumber;
			// Create a card with easy to read numbers and add it to the cardset
			cardSet.add(new EasyToReadPlayingCard(thisCardNumber, nextCardNumber));
		}

		return cardSet;
	}

}
