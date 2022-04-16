package com.maitrefrancois.countingcardgame.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for RandomNumberGenerator class.
 * 
 * @author Francois
 *
 */
class RandomNumberGeneratorTest {

	@ParameterizedTest(name = "Random should be between {0} and {1}")
	@CsvSource({ "0,1", "1,1", "1,5", "1,100", "100,999", "1000,9999", "10000,99999" })
	void Check_In_Bound(int lowerBound, int upperBound) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		// Act
		int randomNumber = random.nextInt(lowerBound, upperBound);
		// Assert
		assertThat(randomNumber).isBetween(lowerBound, upperBound);
	}

	@ParameterizedTest(name = "Random between {0} and {1} like {2} should be {3}")
	@CsvSource({ "0,10,5,5", "6,24,5,15", "82,100,1,91", "82,100,1,91", "1,1000,291,291" })
	void Check_Like(int lowerBound, int upperBound, String likeString, int expected) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		random = random.like(likeString);
		// Act
		int randomNumber = random.nextInt(lowerBound, upperBound);
		// Assert
		assertThat(randomNumber).isEqualTo(expected);
	}

	@ParameterizedTest(name = "Random between {0} and {1} like {2} should be between {3} and {4}")
	@CsvSource({ "0,100,5.,50,59", "0,200,15.,150,159", "1000,2000,1..,1100,1200" })
	void Check_Like_Range(int lowerBound, int upperBound, String likeString, int expectedLower, int expectedUpper) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		random = random.like(likeString);
		// Act
		int randomNumber = random.nextInt(lowerBound, upperBound);
		// Assert
		assertThat(randomNumber).isBetween(expectedLower, expectedUpper);
	}

	@ParameterizedTest(name = "Random between {0} and {1} not like {2} should not be like {3}")
	@CsvSource({ "0,10,5,5", "6,24,.5,15", "82,100,1,91", "1,1000,291,291" })
	void Check_Not_Like(int lowerBound, int upperBound, String likeString, int expected) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		random = random.notLike(likeString);
		// Act
		int randomNumber = random.nextInt(lowerBound, upperBound);
		// Assert
		assertThat(randomNumber).isNotEqualTo(expected);
	}

	@ParameterizedTest(name = "Pattern {0} should not be authourized.")
	@ValueSource(strings = { "1a2", "abc" })
	void Check_Non_Authorized_Like_Patterns(String pattern) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		boolean aPatternSyntaxExceptionOccured = false;
		// Act
		try {
			random = random.like(pattern);
			aPatternSyntaxExceptionOccured = false;
		} catch (PatternSyntaxException e) {
			aPatternSyntaxExceptionOccured = true;
		}
		// Assert
		assertThat(aPatternSyntaxExceptionOccured).isTrue();
	}

	@ParameterizedTest(name = "Pattern {0} should be authourized.")
	@ValueSource(strings = { "1", "12", "123", "034", "234", "512", ".14", "1.4", "14.", "1..", ".2.", "..3", "..." })
	void Check_Authorized_Like_Patterns(String pattern) {
		// Arrange
		RandomNumberGenerator random = new RandomNumberGenerator();
		boolean aPatternSyntaxExceptionOccured = true;
		// Act
		try {
			random = random.like(pattern);
			aPatternSyntaxExceptionOccured = false;
		} catch (PatternSyntaxException e) {
			aPatternSyntaxExceptionOccured = true;
		}
		// Assert
		assertThat(aPatternSyntaxExceptionOccured).isFalse();
	}
}
