package com.maitrefrancois.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class representing a text. A text is basically a set of sentences.
 * 
 * @author Francois
 *
 */
public class Text {
	private String rawText;

	/**
	 * Creates an empty text object.
	 */
	public Text() {
		rawText = "";
	}

	/**
	 * Creates a text object from the given string.
	 * 
	 * @param text Text to be used to initialize this object.
	 */
	public Text(String text) {
		rawText = text;
	}

	/**
	 * Tests if a text is empty.
	 * 
	 * @return true if this text is empty, false otherwise
	 */
	public boolean isEmpty() {
		return rawText.isEmpty();
	}

	/**
	 * Returns a stream consisting of the sentences contained in this text.
	 * 
	 * @return Stream of strings.
	 */
	public Stream<String> getSentences() {
		// TODO Add some kind of cache so that sentences do not get computed
		// for each call (for instance an ArrayList member which if empty gets computed.
		return Arrays.stream(rawText.split("[\\.!?]")).map(s -> s.trim());
	}

	/**
	 * Returns a stream consisting of the words contained in this text.
	 * 
	 * @return Stream of strings.
	 */
	public Stream<String> getWords() {

		// TODO Add some kind of cache so that words do not get computed
		// for each call (for instance an ArrayList member which if empty gets computed.

		// Version 1
//		List<String> words = new ArrayList<String>();
//		getSentences().forEach(sentence -> {
//			for (String word : sentence.split(" ")) {
//				word = word.toLowerCase();
//				word = word.replaceAll("\\p{Punct}", "");
//				words.add(word.trim());
//			}
//		});

		// Version 2
//		Pattern wordPattern = Pattern.compile("[^\\p{Punct}\\p{Blank}]+");
//		getSentences().map(sentence -> sentence.toLowerCase()).forEach(sentence -> {
//			Matcher matcher = wordPattern.matcher(sentence);
//			while (matcher.find()) {
//				System.out.println("start() : " + matcher.start());
//				System.out.println("end()   : " + matcher.end());
//				System.out.println("group() : " + matcher.group());
//				words.add(matcher.group());
//			}
//		});
//		return words.stream();
		String regex = "[\\p{Punct}|\\p{Blank}]+";
		return getSentences().map(sentence -> sentence.toLowerCase())
				.map(sentence -> Arrays.asList(sentence.split(regex))).flatMap(List::stream);

	}

	/**
	 * Returns a stream consisting of distinct words contained in this text.
	 * 
	 * @return Stream of strings.
	 */
	public Stream<String> getUniqueWords() {
		return getWords().distinct();
	}

	/**
	 * Group and count word in this text.
	 * 
	 * @return Returns an HashMap with key being the word and value the number of
	 *         times the word is found in the text.
	 */
	public Map<String, Long> getCountedWords() {
//		.peek(map -> map.remove(""))
		return getWords().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
	}

}
