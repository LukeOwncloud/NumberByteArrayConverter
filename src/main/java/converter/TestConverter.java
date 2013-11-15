package converter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestConverter {

	static public void main(String[] args) {
		NumberByteArrayConverter c = new NumberByteArrayConverter();
		c.addTupleEntry(180);
		c.addTupleEntry(360);
		c.addTupleEntry(1000000);
		c.addTupleEntry(1000000);
		// with 6 decimals -> resolution of 11cm

		List<BigInteger> items = new ArrayList<BigInteger>();
		items.add(new BigInteger("129"));
		items.add(new BigInteger("329"));
		items.add(new BigInteger("19929"));
		items.add(new BigInteger("255"));

		byte[] t = c.toByteArray(items);

		if (t != null)
			c.toNumberArray(t);

		System.out.println("tuple size in bits: " + c.getSizeInBits());

	}
}
