package org.egemen.osgi.turkishconverter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TurkishNumberConverter {
	private static final Map<String, Integer> turkishNumberMap = new HashMap<>();

	static {
		turkishNumberMap.put("sıfır", 0);
		turkishNumberMap.put("bir", 1);
		turkishNumberMap.put("iki", 2);
		turkishNumberMap.put("üç", 3);
		turkishNumberMap.put("dört", 4);
		turkishNumberMap.put("beş", 5);
		turkishNumberMap.put("altı", 6);
		turkishNumberMap.put("yedi", 7);
		turkishNumberMap.put("sekiz", 8);
		turkishNumberMap.put("dokuz", 9);
		turkishNumberMap.put("on", 10);
		turkishNumberMap.put("yirmi", 20);
		turkishNumberMap.put("otuz", 30);
		turkishNumberMap.put("kırk", 40);
		turkishNumberMap.put("elli", 50);
		turkishNumberMap.put("altmış", 60);
		turkishNumberMap.put("yetmiş", 70);
		turkishNumberMap.put("seksen", 80);
		turkishNumberMap.put("doksan", 90);
		turkishNumberMap.put("yüz", 100);

	}
	// This method computes 3 digit numbers until detecting a multiplier
	// If a multiplier has been detected, it multiplies the 3 digit number with it
	public static BigInteger convertTurkishToNumber(String turkishText) {
		boolean isNegative = false;
		String[] words = turkishText.split(" ");
		BigInteger total = BigInteger.ZERO;
		BigInteger temp = BigInteger.ZERO;
		BigInteger finalTotal = BigInteger.ZERO;
		for (String word : words) {
			if (word.equalsIgnoreCase("bin")) {
				if (total.compareTo(BigInteger.ZERO) == 0) {
					total = BigInteger.ONE;
				}
				total = total.multiply(BigInteger.valueOf(1000));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("milyon")) {
				total = total.multiply(BigInteger.valueOf(1000000));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("milyar")) {
				total = total.multiply(BigInteger.valueOf(1000000000));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("trilyon")) {
				total = total.multiply(new BigInteger("1000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("katrilyon")) {
				total = total.multiply(new BigInteger("1000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("kentilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("sekstilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("septilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("oktilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("nonilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("desilyon")) {
				total = total.multiply(new BigInteger("1000000000000000000000000000000000"));
				finalTotal = finalTotal.add(total);
				total = BigInteger.ZERO;
				continue;
			} else if (word.equalsIgnoreCase("eksi") || word.equalsIgnoreCase("negatif")) {
				isNegative = true;
				continue;
			}

			temp = BigInteger.valueOf(turkishNumberMap.get(word));

			if (temp.compareTo(BigInteger.TEN) == -1) {

				total = total.add(temp);
			}
			if (temp.compareTo(BigInteger.valueOf(100)) == 0) {

				total = total.multiply(temp);
			}
			if (temp.compareTo(BigInteger.valueOf(9)) == 1 && temp.compareTo(BigInteger.valueOf(100)) == -1) {

				total = total.add(temp);
			}

		}
		finalTotal = finalTotal.add(total);
		if (isNegative) {
			return finalTotal.multiply(BigInteger.valueOf(-1));
		}
		return finalTotal;
	}

	public static void main(String[] args) {
		String turkishText = "eksi beş desilyon bir milyon bin dokuz yüz doksan sekiz";
		BigInteger number = convertTurkishToNumber(turkishText);
		System.out.println("Türkçe: " + turkishText);
		System.out.println("Sayı: " + number);
	}
}
