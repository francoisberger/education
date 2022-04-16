package com.maitrefrancois.countingcardgame.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A number with spaces in its string representation for easier reading.
 * 
 * @author Francois
 *
 */
public class EasyToReadCardNumber extends Number {
	private static final long serialVersionUID = -2249906658287548474L;

	/**
	 * We actually do not extend Number, we encapsulate an Integer
	 */
	private Integer value;

	/**
	 * Creates a number representing specified value.
	 * 
	 * @param value The value to be used by this number.
	 */
	public EasyToReadCardNumber(Number value) {
		this.value = value.intValue();
	}

	/**
	 * Returns a String representing the number with spaces for easier reading. i.e.
	 * 123456 will be displayed as 123 456.
	 * 
	 * @return String representation of this number.
	 */
	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getIntegerInstance(Locale.FRENCH);
		return format.format(value);
	}

	/**
	 * Returns the value of this number as an int (desired behavior).
	 * 
	 * @return This value as an int.
	 */
	@Override
	public int intValue() {
		return value.intValue();
	}

	/**
	 * Returns the value of this number as a long (required as per Number
	 * implementation).
	 * 
	 * @return This value as a long.
	 */
	@Override
	public long longValue() {
		return value.longValue();
	}

	/**
	 * Returns the value of this number as a float (required as per Number
	 * implementation).
	 * 
	 * @return This value as an float.
	 */
	@Override
	public float floatValue() {
		return value.floatValue();
	}

	/**
	 * Returns the value of this number as a double (required as per Number
	 * implementation).
	 * 
	 * @return This value as an double.
	 */
	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

}
