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
		byte[] t = c.toByteArray(input);

		if (t == null) {
			fail("toByteArray() returned null.");
		}

		List<BigInteger> output = c.toNumberArray(t);
		if (!output.equals(input)) {
			fail("output of toNumberArray() not equal to input of toByteArray()");
		}
	}
}
