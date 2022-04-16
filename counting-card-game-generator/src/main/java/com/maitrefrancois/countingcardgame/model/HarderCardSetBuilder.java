package com.maitrefrancois.countingcardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.maitrefrancois.countingcardgame.util.RandomNumberGenerator;

/**
 * HarderCardSetBuilder, as its name suggested, will generate a CardSet
 * following harder rules. Harder rules are basically hard rules but more
 * complex numbers will be used in the CardSet.
 * 
 * @author Francois
 *
 */
public class HarderCardSetBuilder extends CardSetBuilder {

	/**
	 * Builds a CardSet.
	 */
	@Override
	public CardSet build() {
		// Prepare random number generator
		RandomNumberGenerator random = new RandomNumberGenerator();
		// No notLike filters any number is welcomed

		// Create random numbers and store them if not already in collection
		List<Number> generatedNumbers = new ArrayList<Number>();

		// First, ensure we get a few 70 80 90 (roughly 30%)
		for (int i = 0; i < (super.getCardSetSize() * 0.30); i++) {
			Number randomNumber = random.like(".[789].").nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}
		// Ensure we get some numbers with two consecutive 0
		for (int i = 0; i < (super.getCardSetSize() * 0.30); i++) {
			Number randomNumber = random.like("00.").nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}
		// Reset the like rule and fill with any number matching the notLike rule
		random = random.like("");
		// Also we did already generate some 70 80 90 and some numbers with two 0 so no
		// more...
		random = random.notLike("(.[789].)|(00)");
		while (generatedNumbers.size() < super.getCardSetSize()) {
			Number randomNumber = random.nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}
		// Shuffle the list as we have rule 1 first, then rule 2, then any number
		Collections.shuffle(generatedNumbers);

		// Now populate the card set with cards
		CardSet cardSet = new CardSet();
		Number firstNumber = generatedNumbers.get(0);
		Number lastNumber = generatedNumbers.get(generatedNumbers.size() - 1);
		for (int i = 0; i < generatedNumbers.size(); i++) {
			Number thisCardNumber = generatedNumbers.get(i);
			// Get next number unless we're on the last one -> get the first one
			Number nextCardNumber = (thisCardNumber != lastNumber) ? generatedNumbers.get(i + 1) : firstNumber;
			// Create a card and add it to the cardset
			cardSet.add(new PlayingCard(thisCardNumber, nextCardNumber));
		}

		return cardSet;
	}

}
