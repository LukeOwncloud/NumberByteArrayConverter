package converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for converting multiple numbers (tuple) to byte array and back.
 * 
 * Usage: First, use addTupleEntry() to define upper bounds for each number in
 * your tuple.
 * 
 * Next: Use toByteArray() to convert your tuple to byte array. Use
 * toNumberArray() to convert back.
 * 
 * This file originates from:
 * https://github.com/LukeOwncloud/NumberByteArrayConverter
 * 
 * Make sure your copy is up-to-date!
 */
public class NumberByteArrayConverter {

	private final static Logger LOGGER = Logger
			.getLogger(NumberByteArrayConverter.class.getName());

	/**
	 * Items of tuple identified by upperBound.
	 */
	List<BigInteger> tuple = new ArrayList<BigInteger>();

	BigInteger currentMax;

	/**
	 * Add an item for tuple by specifying its upper bound.
	 * 
	 * @param upperBound
	 *            Upper bound for tuple item. I.e. items for for tuple item must
	 *            be smaller than upperBound.
	 */
	public void addTupleEntry(BigInteger upperBound) {
		tuple.add(upperBound);
		BigInteger result = new BigInteger("1");
		for (BigInteger item : tuple) {
			result = result.multiply(item);
		}
		result = result.subtract(new BigInteger("1"));
		LOGGER.finest("addTupleEntry(): Current max: " + result);
		currentMax = result;
	}

	/**
	 * Add an item for tuple by specifying its upper bound.
	 * 
	 * @param upperBound
	 *            Upper bound for tuple item. I.e. items for for tuple item must
	 *            be smaller than upperBound.
	 */
	public void addTupleEntry(String upperBound) {
		addTupleEntry(new BigInteger(upperBound));
	}

	/**
	 * Add an item for tuple by specifying its upper bound.
	 * 
	 * @param upperBound
	 *            Upper bound for tuple item. I.e. items for for tuple item must
	 *            be smaller than upperBound.
	 */
	public void addTupleEntry(int upperBound) {
		addTupleEntry(String.valueOf(upperBound));
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static int log2(long n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 63 - Long.numberOfLeadingZeros(n);

	}

	/**
	 * Returns the number of bits required to store an instance of current
	 * tuple.
	 * 
	 * @return number of bits for current tuple
	 */
	public int getSizeInBits() {
		int l = currentMax.bitLength();
		if (l % 8 != 0) {
			LOGGER.fine("Note: When storing a tuple of this type to a "
					+ ((l / 8) + 1) + "-byte Array " + (8 - (l % 8))
					+ " bits are unsed.");
		}
		return l;
	}

	public byte[] toByteArray(String[] pos) {
		BigInteger[] array = new BigInteger[pos.length];
		int i = 0;
		for (String string : pos) {
			array[i++] = new BigInteger(string);
		}
		return toByteArray(array);
	}

	public byte[] toByteArray(List<BigInteger> pos) {
		return toByteArray(pos.toArray(new BigInteger[pos.size()]));
	}

	public byte[] toByteArray(BigInteger[] items) {
		LOGGER.fine("toByteArray()");

		if (!checkSize(items))
			return null;

		if (!checkItems(items))
			return null;

		String log = "";
		for (BigInteger item : items) {
			log += item + ", ";
		}
		LOGGER.fine("toByteArray(): Input: " + log);

		BigInteger result = new BigInteger("0");
		BigInteger factor = new BigInteger("1");
		for (int i = 0; i < items.length; i++) {
			BigInteger multiplieditem = items[i].multiply(factor);
			result = result.add(multiplieditem);
			factor = factor.multiply(tuple.get(i));
		}
		byte[] resultArray = result.toByteArray();
		// drop most-significant byte if it is 0. this happens because
		// BigInteger supports negative numbers.
		if (resultArray[0] == 0) {
			byte[] tmp = new byte[resultArray.length - 1];
			System.arraycopy(resultArray, 1, tmp, 0, tmp.length);
			resultArray = tmp;
			LOGGER.finer("toByteArray(): dropped MSB which is 0-byte");
		}
		LOGGER.fine("toByteArray(): output as hex: " + bytesToHex(resultArray));
		LOGGER.finest("toByteArray(): length of output in bytes: "
				+ resultArray.length);
		return resultArray;
	}

	public List<BigInteger> toNumberArray(byte[] byteArray) {
		LOGGER.fine("toNumberArray()");

		List<BigInteger> output = new ArrayList<>(tuple.size());

		LOGGER.finest("toNumberArray(): Input as hex: " + bytesToHex(byteArray));

		// check if first bit is 1. in this case byteArray would be converted to
		// negative number. to avoid that, add 0-byte
		// as most-significant byte.
		if (byteArray[0] < 0) {
			byte[] tmp = new byte[byteArray.length + 1];
			System.arraycopy(byteArray, 0, tmp, 1, byteArray.length);
			byteArray = tmp;
			LOGGER.finer("toNumberArray(): added 0-byte as MSB");
		}
		BigInteger input = new BigInteger(byteArray);

		LOGGER.fine("toNumberArray(): Input: " + input);
		BigInteger factor = new BigInteger("1");

		for (int i = 0; i < tuple.size(); i++) {
			factor = factor.multiply(tuple.get(i));
			BigInteger item = input.mod(tuple.get(i));
			input = input.divide(tuple.get(i));
			output.add(item);
		}

		String log = "";
		for (BigInteger item : output) {
			log += item + ", ";
		}
		LOGGER.fine("toNumberArray(): Output: " + log);
		return output;
	}

	/**
	 * Checks that items is of same size as tuple.
	 * 
	 * @param items
	 *            list of items
	 * @return true if same size
	 */
	private boolean checkSize(BigInteger[] items) {
		boolean result = items.length == tuple.size();
		if (!result)
			LOGGER.warning("checkSize(): Size of items != size of tuple");
		return result;
	}

	/**
	 * Checks that all items in items are smaller than their corresponding entry
	 * in tuple.
	 * 
	 * @param items
	 *            list of items
	 * @return true, if all items are okay (smaller than tuple)
	 */
	private boolean checkItems(BigInteger[] items) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareTo(tuple.get(i)) >= 0) {
				LOGGER.warning("checkItems(): Entry " + i
						+ " to big. Must be smaller than; " + tuple.get(i)
						+ " is: " + items[i]);
				return false;
			}
			if (items[i].compareTo(new BigInteger("0")) < 0) {
				LOGGER.warning("checkItems(): Entry " + i
						+ " must be greater or equal 0");
				return false;
			}

		}
		return true;
	}

	/**
	 * Helper for bytesToHex()
	 */
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	/**
	 * Converts a byte[] to an hexadecimal string.
	 * 
	 * @param bytes
	 *            byte[] to be returned as string
	 * @return hexadecimal string
	 */
	private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
