package com.maitrefrancois.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Text class.
 * 
 * @author Francois
 *
 */
class TextTest {
	/**
	 * Tests that an empty text is considered as empty.
	 */
	@Test
	void testThat_anEmptyText_isEmpty() {
		// Arrange
		Text emptyText = new Text();

		// Act / Assert
		assertThat(emptyText.isEmpty()).isEqualTo(true);
	}

	/**
	 * Tests that a two sentences text is considered as two sentences.
	 */
	@Test
	void testThat_2SentencesText_is2Sentences() {
		// Arrange
		Text text = new Text("This is a simple text. This is the second sentence.");

		// Act / Assert
		assertThat(text.getSentences().count()).isEqualTo(2);
	}

	/**
	 * Tests that a text with 10 words is considered as a 10 words text.
	 */
	@Test
	void testThat_10WordsText_is10Words() {
		// Arrange
		Text text = new Text("Another text. It has exaclty 2 sentences and 10 words.");

		// Act / Assert
		assertThat(text.getWords().count()).isEqualTo(10);
	}

	/**
	 * Tests that a text with 10 words is considered as a 10 words text.
	 */
	@Test
	void testThat_textWithPunctuation_hasNoPunctuationInWords() {
		// Arrange
		Text text = new Text("This text has punctuation : punctuation must be ignored, if not there's a problem!");

		// Act / Assert
		assertThat(text.getWords().filter(word -> word.matches("\\p{Punct}")).count()).isEqualTo(0);
	}

	/**
	 * Tests that a text with 10 words is considered as a 10 words text.
	 */
	@Test
	void testThat_repetedWords_areNotCountAsUnique() {
		// Arrange
		Text text = new Text("This text has redundant words : text, word, word, text, punctuation, text");

		// Act / Assert
		assertThat(text.getUniqueWords().count()).isLessThan(text.getWords().count());
	}

	/**
	 * Tests that a text with 10 words is considered as a 10 words text.
	 */
	@Test
	void testThat_repetedWords_areCountedCorrectly() {
		// Arrange
		Text text = new Text("This text has redundant words : text, word, word, text, punctuation, text");
		// Act
		Map<String, Long> countedWords = text.getCountedWords();

		// Assert
		Map<String, Long> expected = new HashMap<String, Long>();
		expected.put("this", Long.valueOf(1));
		expected.put("text", Long.valueOf(4));
		expected.put("has", Long.valueOf(1));
		expected.put("redundant", Long.valueOf(1));
		expected.put("words", Long.valueOf(1));
		expected.put("word", Long.valueOf(2));
		expected.put("punctuation", Long.valueOf(1));

		assertThat(countedWords).isEqualTo(expected);
	}
}
