package converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberByteArrayConverter {

	/**
	 * Items of tuple identified by upperBound.
	 */
	List<BigInteger> tuple = new ArrayList<BigInteger>();

	BigInteger currentMax;

	/**
	 * Add an item for tuple.
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
		System.out.println("Current max: " + result);
		currentMax = result;
	}

	public static int log2(long n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		return 63 - Long.numberOfLeadingZeros(n);

	}

	public int getSizeInBits() {
		int l = currentMax.bitLength();
		if (l % 8 != 0) {
			System.out.println("Note: When storing a tuple of this type to a "
					+ ((l / 8) + 1) + "-byte Array " + (8 - (l % 8))
					+ " bits are unsed.");
		}
		return l;
	}

	public void addTupleEntry(String max) {
		addTupleEntry(new BigInteger(max));
	}

	public void addTupleEntry(int max) {
		addTupleEntry(String.valueOf(max));
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
		if (!checkSize(items))
			return null;

		if (!checkItems(items))
			return null;

		BigInteger result = new BigInteger("0");
		BigInteger factor = new BigInteger("1");
		for (int i = 0; i < items.length; i++) {
			BigInteger multiplieditem = items[i].multiply(factor);
			result = result.add(multiplieditem);
			factor = factor.multiply(tuple.get(i));
		}
		// System.out.println("result: " + result);
		byte[] resultArray = result.toByteArray();
		// drop most-significant byte if it is 0. this happens because
		// BigInteger supports negative numbers.
		if (resultArray[0] == 0) {
			byte[] tmp = new byte[resultArray.length - 1];
			System.arraycopy(resultArray, 1, tmp, 0, tmp.length);
			resultArray = tmp;
			// System.out.println("dropped MSB which is 0-byte");
		}
		// System.out.println("as hex: " + bytesToHex(resultArray));
		// System.out.println("length of byte array: " + resultArray.length);
		return resultArray;
	}

	public List<BigInteger> toNumberArray(byte[] byteArray) {
		List<BigInteger> output = new ArrayList<>(tuple.size());

		// check if first bit is 1. in this case byteArray would be converted to
		// negative number. to avoid that, add 0-byte
		// as most-significant byte.
		if (byteArray[0] < 0) {
			byte[] tmp = new byte[byteArray.length + 1];
			System.arraycopy(byteArray, 0, tmp, 1, byteArray.length);
			byteArray = tmp;
			System.out.println("add 0-byte as MSB");
		}
		BigInteger input = new BigInteger(byteArray);

		System.out.println("Input: " + input);
		BigInteger factor = new BigInteger("1");

		for (int i = 0; i < tuple.size(); i++) {
			factor = factor.multiply(tuple.get(i));
			BigInteger item = input.mod(tuple.get(i));
			input = input.divide(tuple.get(i));
			output.add(item);
		}

		for (BigInteger item : output) {
			System.out.print(item + ", ");
		}
		System.out.println("");
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
			System.out.println("Size of items != size of tuple");
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
				System.out.println("Entry " + i
						+ " to big. Must be smaller than; " + tuple.get(i)
						+ " is: " + items[i]);
				return false;
			}
			if (items[i].compareTo(new BigInteger("0")) < 0) {
				System.out
						.println("Entry " + i + " must be greater or equal 0");
				return false;
			}

		}
		return true;
	}

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

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
