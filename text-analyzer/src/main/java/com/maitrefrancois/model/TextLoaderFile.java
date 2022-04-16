package com.maitrefrancois.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implements text loading from text file.
 * 
 * @author Francois
 *
 */
public class TextLoaderFile implements TextLoader {
	private File file;

	/**
	 * Initializes a loader that may be used to load a Text from file.
	 * 
	 * @param path Path to file.
	 */
	public TextLoaderFile(String path) {
		file = new File(path);
	}

	/**
	 * Checks if file exists.
	 * 
	 * @return True if exits false otherwise.
	 */
	public boolean exists() {
		return file.exists();
	}

	/**
	 * Loads a Text from a data source.
	 * 
	 * @return Text loaded from data source.
	 */
	public Text load() {
		Text text = new Text();
		if (file.exists()) {
			StringBuffer buffer = new StringBuffer();
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				reader.lines().forEach(buffer::append);
			} catch (IOException e) {
				e.printStackTrace();
			}
			text = new Text(buffer.toString());
		}
		return text;
	}

}
