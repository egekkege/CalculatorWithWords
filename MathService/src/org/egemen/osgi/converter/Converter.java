package org.egemen.osgi.converter;

import java.math.BigInteger; // Importing the BigInteger class for mathematical operations
import java.util.Arrays; // Importing Arrays class for array manipulation
import java.util.List; // Importing List interface for handling lists

public class Converter {
	// List of allowed words for number conversion
	protected static List<String> allowedWords = Arrays.asList("zero", "one", "two", "three", "four", "five", "six",
			"seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
			"seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety", "hundred", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion",
			"sextillion", "septillion", "octillion", "nonillion", "decillion", "minus", "negative", "and");

	// Method to convert a sentence of words to a BigInteger number
	public static BigInteger wordToNumber(String s) {
		String[] words = sentenceToArray(s); // Convert the sentence into an array of words
		return wordToNumberHelper(words); // Call the helper method to perform the conversion
	}

	// Helper method to perform word to number conversion
	static BigInteger wordToNumberHelper(String[] words) {
		boolean isNegative = false; // Flag to track if the number is negative
		BigInteger result = BigInteger.valueOf(0); // Temporary result during conversion
		BigInteger finalResult = BigInteger.valueOf(0); // Final result of the conversion

		for (String str : words) {
			if (str.equalsIgnoreCase("zero")) {
				result = result.add(BigInteger.valueOf(0));
			} else if (str.equalsIgnoreCase("one")) {
				result = result.add(BigInteger.valueOf(1));
			} else if (str.equalsIgnoreCase("two")) {
				result = result.add(BigInteger.valueOf(2));
			} else if (str.equalsIgnoreCase("three")) {
				result = result.add(BigInteger.valueOf(3));
			} else if (str.equalsIgnoreCase("four")) {
				result = result.add(BigInteger.valueOf(4));
			} else if (str.equalsIgnoreCase("five")) {
				result = result.add(BigInteger.valueOf(5));
			} else if (str.equalsIgnoreCase("six")) {
				result = result.add(BigInteger.valueOf(6));
			} else if (str.equalsIgnoreCase("seven")) {
				result = result.add(BigInteger.valueOf(7));
			} else if (str.equalsIgnoreCase("eight")) {
				result = result.add(BigInteger.valueOf(8));
			} else if (str.equalsIgnoreCase("nine")) {
				result = result.add(BigInteger.valueOf(9));
			} else if (str.equalsIgnoreCase("ten")) {
				result = result.add(BigInteger.valueOf(10));
			} else if (str.equalsIgnoreCase("eleven")) {
				result = result.add(BigInteger.valueOf(11));
			} else if (str.equalsIgnoreCase("twelve")) {
				result = result.add(BigInteger.valueOf(12));
			} else if (str.equalsIgnoreCase("thirteen")) {
				result = result.add(BigInteger.valueOf(13));
			} else if (str.equalsIgnoreCase("fourteen")) {
				result = result.add(BigInteger.valueOf(14));
			} else if (str.equalsIgnoreCase("fifteen")) {
				result = result.add(BigInteger.valueOf(15));
			} else if (str.equalsIgnoreCase("sixteen")) {
				result = result.add(BigInteger.valueOf(16));
			} else if (str.equalsIgnoreCase("seventeen")) {
				result = result.add(BigInteger.valueOf(17));
			} else if (str.equalsIgnoreCase("eighteen")) {
				result = result.add(BigInteger.valueOf(18));
			} else if (str.equalsIgnoreCase("nineteen")) {
				result = result.add(BigInteger.valueOf(19));
			} else if (str.equalsIgnoreCase("twenty")) {
				result = result.add(BigInteger.valueOf(20));
			} else if (str.equalsIgnoreCase("thirty")) {
				result = result.add(BigInteger.valueOf(30));
			} else if (str.equalsIgnoreCase("forty")) {
				result = result.add(BigInteger.valueOf(40));
			} else if (str.equalsIgnoreCase("fifty")) {
				result = result.add(BigInteger.valueOf(50));
			} else if (str.equalsIgnoreCase("sixty")) {
				result = result.add(BigInteger.valueOf(60));
			} else if (str.equalsIgnoreCase("seventy")) {
				result = result.add(BigInteger.valueOf(70));
			} else if (str.equalsIgnoreCase("eighty")) {
				result = result.add(BigInteger.valueOf(80));
			} else if (str.equalsIgnoreCase("ninety")) {
				result = result.add(BigInteger.valueOf(90));
			} else if (str.equalsIgnoreCase("hundred")) {
				result = result.multiply(BigInteger.valueOf(100));
			} else if (str.equalsIgnoreCase("thousand")) {
				result = result.multiply(BigInteger.valueOf(1000));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("million")) {
				result = result.multiply(BigInteger.valueOf(1000000));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("billion")) {
				result = result.multiply(BigInteger.valueOf(1000000000));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("trillion")) {
				result = result.multiply(BigInteger.valueOf(1000000000000L));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("quadrillion")) {
				result = result.multiply(BigInteger.valueOf(1000000000000000L));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("quintillion")) {
				result = result.multiply(BigInteger.valueOf(1000000000000000000L));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("sextillion")) {
				result = result.multiply(new BigInteger("1000000000000000000000"));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("septillion")) {
				result = result.multiply(new BigInteger("1000000000000000000000000"));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("octillion")) {
				result = result.multiply(new BigInteger("1000000000000000000000000000"));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("nonillion")) {
				result = result.multiply(new BigInteger("1000000000000000000000000000000"));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("decillion")) {
				result = result.multiply(new BigInteger("1000000000000000000000000000000000"));
				finalResult = finalResult.add(result);
				result = BigInteger.valueOf(0);
			} else if (str.equalsIgnoreCase("positive")) {
				isNegative = false;
			} else if (str.equalsIgnoreCase("minus") || str.equalsIgnoreCase("negative")) {
				isNegative = true;
			}
		}

		finalResult = finalResult.add(result); // Adding the temporary result to the final result

		if (isNegative) {
			finalResult = finalResult.multiply(BigInteger.valueOf(-1)); // Making the result negative if needed
		}
		return finalResult; // Returning the final converted number
	}

	// Method to convert a sentence into an array of words
	protected static String[] sentenceToArray(String input) {
		if (input != null && !input.isEmpty()) {
			input = input.replaceAll("-", " "); // Replace hyphens with spaces
			input = input.toLowerCase().replaceAll(" and", " "); // Replace "and" with spaces and convert to lowercase
		}
		assert input != null;
		return input.trim().split("\\s+"); // Splitting the sentence into words based on spaces
	}

	// Method to check if the input words are valid for conversion
	protected static boolean isValidInput(String[] stringArray) {
		for (String str : stringArray) {
			if (!allowedWords.contains(str)) {
				return false; // If a word is not in the allowed list, the input is invalid
			}
		}
		return true; // All words are valid
	}
}
