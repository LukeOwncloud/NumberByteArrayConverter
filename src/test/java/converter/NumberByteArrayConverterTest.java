package converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for NumberByteArrayConverter.
 */
public class NumberByteArrayConverterTest extends TestCase {

	private final static Logger LOGGER = Logger
			.getLogger(NumberByteArrayConverter.class.getName());

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public NumberByteArrayConverterTest(String testName) {
		super(testName);

		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINEST);
		Formatter formatterTxt = new LogFormatter();
		handler.setFormatter(formatterTxt);
		Handler[] handlers = LOGGER.getHandlers();
		for (Handler handler2 : handlers) {
			LOGGER.removeHandler(handler2);
		}
		LOGGER.setUseParentHandlers(false);
		LOGGER.addHandler(handler);
		LOGGER.setLevel(Level.WARNING);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NumberByteArrayConverterTest.class);
	}

	public void testSmallInput() {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(2);
		c.addTupleEntry(2);

		List<BigInteger> input = new ArrayList<BigInteger>();
		input.add(new BigInteger("1"));
		input.add(new BigInteger("0"));

		conversionTester(c, input);
	}

	public void testBadInput() {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(2);
		c.addTupleEntry(2);

		List<BigInteger> input = new ArrayList<BigInteger>();
		input.add(new BigInteger("4"));
		input.add(new BigInteger("-1"));

		// Temporarily ignore level WARNING. toByteArray() SHOULD raise such a
		// warning.
		Level tempLevel = LOGGER.getLevel();
		LOGGER.setLevel(Level.SEVERE);
		byte[] tuple = c.toByteArray(input);
		LOGGER.setLevel(tempLevel);

		if (tuple != null) {
			fail("toByteArray() did not return null even though input was bad.");
		}
	}

	public void testBigInput() {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);

		List<BigInteger> input = new ArrayList<BigInteger>();
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("2535"));

		conversionTester(c, input);
	}

	public void testHugeInput() {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(10001000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000023400);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10000300);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		c.addTupleEntry(10003000);
		c.addTupleEntry(100002100);
		c.addTupleEntry(10000000);
		c.addTupleEntry(100021000);

		List<BigInteger> input = new ArrayList<BigInteger>();
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("205"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("319"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("1355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("0"));
		input.add(new BigInteger("2535"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("2535"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("2535"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("2535"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("191929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("122349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("1229"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("2535"));
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1929"));
		input.add(new BigInteger("25"));
		input.add(new BigInteger("2349"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("2355"));
		input.add(new BigInteger("0"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("192929"));
		input.add(new BigInteger("255"));
		input.add(new BigInteger("1219"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("1955929"));
		input.add(new BigInteger("0"));

		conversionTester(c, input);
	}

	/**
	 * GeoCoordiate test.
	 */
	public void testGeoCoordiateInput() {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		// with 6 decimals -> resolution of 11cm

		List<BigInteger> input = new ArrayList<BigInteger>();
		input.add(new BigInteger("129"));
		input.add(new BigInteger("329"));
		input.add(new BigInteger("19929"));
		input.add(new BigInteger("255"));

		conversionTester(c, input);
	}

	private void conversionTester(NumberByteArrayConverter c,
			List<BigInteger> input) {
		// TEST: toByteArray(List<BigInteger> items) fits
		// toNumberArray(byte[] byteArray)
		byte[] tuple = c.toByteArray(input);

		if (tuple == null) {
			fail("toByteArray() returned null.");
		}

		List<BigInteger> output = c.toNumberArray(tuple);
		if (!output.equals(input)) {
			fail("output of toNumberArray() not equal to input of toByteArray()");
		}

		// TEST: toByteArray(List<BigInteger> items) ==
		// toByteArrayAsStringList(List<String> items)
		List<String> inputStr = new ArrayList<String>();
		for (BigInteger bigInt : input) {
			inputStr.add(bigInt.toString());
		}
		byte[] tupleFromString = c.toByteArrayAsStringList(inputStr);
		if (!equalsByteArray(tuple, tupleFromString)) {
			fail("output of toByteArrayAsStringList(List<String> items) wrong.");
		}

		// TEST: toByteArray(List<BigInteger> items) ==
		// toByteArray(BigInteger[] items)
		BigInteger[] inputArray = input.toArray(new BigInteger[input.size()]);
		byte[] inputFromByteArray = c.toByteArray(inputArray);
		if (!equalsByteArray(tuple, inputFromByteArray)) {
			fail("output of toByteArray(BigInteger[] items) wrong.");
		}

		// TEST: toByteArray(List<BigInteger> items) ==
		// toByteArray(String[] items)
		String[] inputStringArray = inputStr
				.toArray(new String[inputStr.size()]);
		byte[] inputFromStringArray = c.toByteArray(inputStringArray);
		if (!equalsByteArray(tuple, inputFromStringArray)) {
			fail("output of toByteArray(String[] items) wrong.");
		}

		// TEST: toNumberArray(byte[] byteArray) ==
		// toNumberArray(BigInteger byteArrayAsBigInteger)
		BigInteger tupleAsBigInt = new BigInteger(tuple);
		List<BigInteger> output2 = c.toNumberArray(tupleAsBigInt);
		if (!output.equals(output2)) {
			fail("output of toNumberArray(BigInteger byteArrayAsBigInteger) not equal to output of toNumberArray(byte[] byteArray)");
		}

		// TEST: toNumberArray(byte[] byteArray) ==
		// toNumberArray(long byteArrayAsLong) if applicable.
		int tempResult = tupleAsBigInt.compareTo(new BigInteger(String
				.valueOf(Long.MAX_VALUE)));
		int tempResult2 = tupleAsBigInt.compareTo(new BigInteger(String
				.valueOf(Long.MIN_VALUE)));
		if (1 != tempResult && -1 != tempResult2) {
			long tupleAsLong = tupleAsBigInt.longValue();
			List<BigInteger> output3 = c.toNumberArray(tupleAsLong);
			if (!output.equals(output3)) {
				fail("output of toNumberArray(long byteArrayAsLong) not equal to output of toNumberArray(byte[] byteArray)");
			}
		}

	}

	private boolean equalsByteArray(byte[] array1, byte[] array2) {
		String string1 = NumberByteArrayConverter.bytesToHex(array1);
		String string2 = NumberByteArrayConverter.bytesToHex(array2);
		return string1.equals(string2);
	}
}
