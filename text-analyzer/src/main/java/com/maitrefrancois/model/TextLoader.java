package com.maitrefrancois.model;

/**
 * Interface to load text from various data source. This is actually the
 * Strategy pattern (proper strategy is retrieved with the TextLoaderFactory).
 * 
 * @author Francois
 *
 */
public interface TextLoader {
	/**
	 * Checks if data source exists.
	 * 
	 * @return True if strategy is pointing to an existing data source.
	 */
	boolean exists();

	/**
	 * Loads a Text from a data source.
	 * 
	 * @return Text loaded from data source.
	 */
	public Text load();
}
