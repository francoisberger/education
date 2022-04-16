package com.maitrefrancois.countingcardgame.util;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Generate pseudo random ints matching specified rules.
 * 
 * @author Francois
 *
 */
public class RandomNumberGenerator {
	// The MAX_TRIES constant is meant to avoid infinite loops in
	// case likes and not likes rules are opposite
	private static final int MAX_TRIES = 5000;

	private Random random = new Random(System.currentTimeMillis());
	private NumberFormat formatter = NumberFormat.getIntegerInstance(Locale.US);
	private Pattern likePattern = Pattern.compile("");
	private Pattern notLikePattern = Pattern.compile("");

	/**
	 * Adds the opportunity to generate a random according to a certain pattern.
	 * Pattern is defined as a 3 digit string with any number being "." For example
	 * to generate a number like in the 170 to 179 range one may use the "17."
	 * pattern. To generate a number like 253, 353, 453, one may is to use the ".53"
	 * pattern.
	 * 
	 * @param regex A regular expression used to filter this random generator
	 *              output.
	 * @return this RandomNumberGenerator.
	 */
	public RandomNumberGenerator like(String regex) {
		likePattern = Pattern.compile(checkRegex(regex));
		return this;
	}

	/**
	 * Adds the opportunity to generate a random that does not match a pattern.
	 * Pattern is defined as a 3 digit string with any number being "." For example
	 * to generate a number not like the 170 to 179 range one may use the "17."
	 * pattern. To generate a number not like 253, 353, 453, one may is to use the
	 * ".53" pattern.
	 * 
	 * @param regex A regular expression used to filter this random generator
	 *              output.
	 * @return this RandomNumberGenerator.
	 */
	public RandomNumberGenerator notLike(String regex) {
		notLikePattern = Pattern.compile(checkRegex(regex));
		return this;
	}

	/**
	 * Ensures the regular expression used to generate numbers is only 3 digits long
	 * or contains a "." The method will transform the "." into [0-9].
	 * 
	 * @param regex The regular expression to be used.
	 * @return A modified version of the regular expression where . are replaced
	 *         with [0-9] so that only digits are used.
	 */
	private String checkRegex(String regex) {
		Pattern patternChecker = Pattern.compile("[^0-9\\.\\|\\(\\)\\[\\]]");
		if (patternChecker.matcher(regex).find()) {
			throw new PatternSyntaxException("Please use only digits and \". | [] ()\" special characters", regex, -1);
		}
		// Replace "." with [0-9]
		return regex.replace(".", "[0-9]");
	}

	/**
	 * Returns an int within specified bounds.
	 * 
	 * @param lowerBound Low bound value (inclusive).
	 * @param upperBound High bound value (exclusive).
	 * @return Generated random value.
	 */
	public int nextInt(int lowerBound, int upperBound) {
		// Should not be used
		if (lowerBound == upperBound) {
			return lowerBound;
		}

		// No patterns, return any number
		if (likePattern.pattern().isEmpty() && notLikePattern.pattern().isEmpty()) {
			return lowerBound + random.nextInt(upperBound - lowerBound);
		}

		for (int i = 0; i < MAX_TRIES; i++) {
			int nextInt = lowerBound + random.nextInt(upperBound - lowerBound);
			// Using a formatted number so that we have , as separators for units,
			// thousands, millions
			String formattedNumber = formatter.format(nextInt);

			// Apply rules to determine if one is matched
			boolean matchesRules = false;
			if (likePattern.pattern().isEmpty() || likePattern.matcher(formattedNumber).find()) {
				matchesRules = true;
			}

			// No need to check notLikePattern if number does not meet prior requirements
			// of if there are no notLikePattern
			if (matchesRules && !notLikePattern.pattern().isEmpty()) {
				matchesRules = false;
				if (!notLikePattern.matcher(formattedNumber).find()) {
					matchesRules = true;
				}
			}

			if (matchesRules) {
				return nextInt;
			}
		}
		// As a default returns any random number
		return lowerBound + random.nextInt(upperBound - lowerBound);
	}
}
