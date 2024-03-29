package org.egemen.osgi.converterfromnumber;

public class ConverterFromNumber {
	static public class Power {
		private int exponent;
		private String name;

		private Power(int exponent, String name) {
			this.exponent = exponent;
			this.name = name;
		}

		public int getExponent() {
			return exponent;
		}

		public String getName() {
			return name;
		}
	}

	static private Power[] SCALE_UNITS = new Power[] { new Power(33, "decillion"), new Power(30, "nonillion"),
			new Power(27, "octillion"), new Power(24, "septillion"), new Power(21, "sextillion"),
			new Power(18, "quintillion"), new Power(15, "quadrillion"), new Power(12, "trillion"),
			new Power(9, "billion"), new Power(6, "million"), new Power(3, "thousand"), new Power(2, "hundred"), };

	public enum Scale {
		SHORT;

		public String getName(int exponent) {
			for (Power unit : SCALE_UNITS) {
				if (unit.getExponent() == exponent) {
					return unit.getName();
				}
			}
			return "";
		}
	}

	static public Scale SCALE = Scale.SHORT;

	static abstract public class AbstractProcessor {
		static protected final String SEPARATOR = " ";
		static protected final int NO_VALUE = -1;

		public String getName(long value) {
			return getName(Long.toString(value));
		}

		abstract public String getName(String value);
	}

	static public class UnitProcessor extends AbstractProcessor {
		static private final String[] TOKENS = new String[] { "one", "two", "three", "four", "five", "six", "seven",
				"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen" };

		@Override
		public String getName(String value) {
			StringBuilder buffer = new StringBuilder();
			int offset = NO_VALUE;
			int number;
			if (value.length() > 3) {
				number = Integer.valueOf(value.substring(value.length() - 3), 10);
			} else {
				number = Integer.valueOf(value, 10);
			}
			number %= 100;
			if (number < 10) {
				offset = (number % 10) - 1;
			} else if (number < 20) {
				offset = (number % 20) - 1;
			}
			if (offset != NO_VALUE && offset < TOKENS.length) {
				buffer.append(TOKENS[offset]);
			}
			return buffer.toString();
		}
	}

	static public class TensProcessor extends AbstractProcessor {
		static private final String[] TENS = new String[] { "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
				"eighty", "ninety" };
		static private final String UNION_SEPARATOR = "-";
		private UnitProcessor unitProcessor = new UnitProcessor();

		@Override
		public String getName(String value) {
			StringBuilder buffer = new StringBuilder();
			boolean tensFound = false;
			int number;
			if (value.length() > 3) {
				number = Integer.valueOf(value.substring(value.length() - 3), 10);
			} else {
				number = Integer.valueOf(value, 10);
			}
			number %= 100; // keep only two digits
			if (number >= 20) {
				buffer.append(TENS[(number / 10) - 2]);
				number %= 10;
				tensFound = true;
			} else {
				number %= 20;
			}
			if (number != 0) {
				if (tensFound) {
					buffer.append(UNION_SEPARATOR);
				}
				buffer.append(unitProcessor.getName(number));
			}
			return buffer.toString();
		}
	}

	static public class HundredProcessor extends AbstractProcessor {
		private int EXPONENT = 2;
		private UnitProcessor unitProcessor = new UnitProcessor();
		private TensProcessor tensProcessor = new TensProcessor();

		@Override
		public String getName(String value) {
			StringBuilder buffer = new StringBuilder();
			int number;
			if (value.isEmpty()) {
				number = 0;
			} else if (value.length() > 4) {
				number = Integer.valueOf(value.substring(value.length() - 4), 10);
			} else {
				number = Integer.valueOf(value, 10);
			}
			number %= 1000; // keep at least three digits
			if (number >= 100) {
				buffer.append(unitProcessor.getName(number / 100));
				buffer.append(SEPARATOR);
				buffer.append(SCALE.getName(EXPONENT));
			}
			String tensName = tensProcessor.getName(number % 100);
			if (!tensName.isEmpty() && (number >= 100)) {
				buffer.append(SEPARATOR);
			}
			buffer.append(tensName);
			return buffer.toString();
		}
	}

	static public class CompositeBigProcessor extends AbstractProcessor {
		private HundredProcessor hundredProcessor = new HundredProcessor();
		private AbstractProcessor lowProcessor;
		private int exponent;

		public CompositeBigProcessor(int exponent) {
			if (exponent <= 3) {
				lowProcessor = hundredProcessor;
			} else {
				lowProcessor = new CompositeBigProcessor(exponent - 3);
			}
			this.exponent = exponent;
		}

		public String getToken() {
			return SCALE.getName(getPartDivider());
		}

		protected AbstractProcessor getHighProcessor() {
			return hundredProcessor;
		}

		protected AbstractProcessor getLowProcessor() {
			return lowProcessor;
		}

		public int getPartDivider() {
			return exponent;
		}

		@Override
		public String getName(String value) {
			StringBuilder buffer = new StringBuilder();
			String high, low;
			if (value.length() < getPartDivider()) {
				high = "";
				low = value;
			} else {
				int index = value.length() - getPartDivider();
				high = value.substring(0, index);
				low = value.substring(index);
			}
			String highName = getHighProcessor().getName(high);
			String lowName = getLowProcessor().getName(low);
			if (!highName.isEmpty()) {
				buffer.append(highName);
				buffer.append(SEPARATOR);
				buffer.append(getToken());
				if (!lowName.isEmpty()) {
					buffer.append(SEPARATOR);
				}
			}
			if (!lowName.isEmpty()) {
				buffer.append(lowName);
			}
			return buffer.toString();
		}
	}

	static public class DefaultProcessor extends AbstractProcessor {
		static private String MINUS = "minus";
		static private String UNION_AND = "and";
		static private String ZERO_TOKEN = "zero";
		private AbstractProcessor processor = new CompositeBigProcessor(63);

		@Override
		public String getName(String value) {
			boolean negative = false;
			if (value.startsWith("-")) {
				negative = true;
				value = value.substring(1);
			}
			int decimals = value.indexOf(".");
			String decimalValue = null;
			if (0 <= decimals) {
				decimalValue = value.substring(decimals + 1);
				value = value.substring(0, decimals);
			}
			String name = processor.getName(value);
			if (name.isEmpty()) {
				name = ZERO_TOKEN;
			} else if (negative) {
				name = MINUS.concat(SEPARATOR).concat(name);
			}
			if (!(null == decimalValue || decimalValue.isEmpty())) {
				name = name.concat(SEPARATOR).concat(UNION_AND).concat(SEPARATOR)
						.concat(processor.getName(decimalValue)).concat(SEPARATOR)
						.concat(SCALE.getName(-decimalValue.length()));
			}
			return name;
		}
	}

	// static public AbstractProcessor processor;

}