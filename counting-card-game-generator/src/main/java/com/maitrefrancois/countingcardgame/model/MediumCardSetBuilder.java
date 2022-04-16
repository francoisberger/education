package com.maitrefrancois.countingcardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.maitrefrancois.countingcardgame.util.RandomNumberGenerator;

/**
 * MediumCardSetBuilder, as its name suggested, will generate a CardSet
 * following medium rules. With medium numbers, we are trying to avoid 70 and 90
 * which are quite hard to read in French but we will use a few 80. We are also
 * trying to avoid numbers with zeros like 1200, 1005 or 1050 so that every
 * digit is pronounced.
 * 
 * @author Francois
 *
 */
public class MediumCardSetBuilder extends CardSetBuilder {

	/**
	 * Builds a CardSet.
	 */
	@Override
	public CardSet build() {
		// Prepare random number generator
		RandomNumberGenerator random = new RandomNumberGenerator();
		// Filter 1 -> With medium numbers, avoid 70 and 90 which are quite hard to
		// read in French
		// Flter 2 -> With medium numbers, try to avoid numbers with two zeros like
		// 1200, 1005 or 1050 so that every digit is pronounced
		random = random.notLike("([79].)|(00)|(0.0)");

		// Create random numbers and store them if not already in collection
		List<Number> generatedNumbers = new ArrayList<Number>();

		// First, ensure we get a few 80 (roughly 20%)
		for (int i = 0; i < (super.getCardSetSize() * 0.20); i++) {
			Number randomNumber = random.like(".8.").nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}
		// Ensure we get some numbers with one 0
		for (int i = 0; i < (super.getCardSetSize() * 0.20); i++) {
			Number randomNumber = random.like("(.0.)|(..0)").nextInt(getLowerBound(), getUpperBound());
			if (!generatedNumbers.contains(randomNumber)) {
				generatedNumbers.add(randomNumber);
			}
		}
		// Reset the like rule and fill with any number matching the notLike rule
		random = random.like("");
		// Also we did already generate some 80 and some numbers with 0 so no more...
		random = random.notLike("(.[789].)|(0)");
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
			// Create a card with easy to read numbers and add it to the cardset
			cardSet.add(new EasyToReadPlayingCard(thisCardNumber, nextCardNumber));
		}

		return cardSet;
	}
}
