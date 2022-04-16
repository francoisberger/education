package com.maitrefrancois.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * Test class for the TextLoaderFile
 * 
 * @author Francois
 *
 */
class TextLoaderFileTest {
	/**
	 * Tests that an empty text is loaded as empty.
	 */
	@Test
	void testThat_anEmptyFile_isAnEmptyText() {
		// Arrange
		File ressource = new File("src/test/resources/empty-text.txt");
		TextLoaderFile loader = new TextLoaderFile(ressource.getAbsolutePath());

		// Act
		Text emptyText = loader.load();

		// Assert
		assertThat(emptyText.isEmpty()).isEqualTo(true);
	}

	/**
	 * Tests that an empty text is loaded as empty.
	 */
	@Test
	void testThat_aNonEmptyFile_isANonEmptyText() {
		// Arrange
		File ressource = new File("src/test/resources/test-text1.txt");
		TextLoaderFile loader = new TextLoaderFile(ressource.getAbsolutePath());

		// Act
		Text text = loader.load();

		// Assert
		assertThat(text.isEmpty()).isEqualTo(false);
	}
}
