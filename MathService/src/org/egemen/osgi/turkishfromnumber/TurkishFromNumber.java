package org.egemen.osgi.turkishfromnumber;

public class TurkishFromNumber {

	public static String converterFromNumToTr(String number) {
		if (number.charAt(0) == '-') { // Eğer negatif sayıysa başa "-" ekler ve kendini kalan kısımla tekrar çağırır
			return "eksi " + converterFromNumToTr(number.substring(1));
		}
		if (number.equals("0")) {
			return "sıfır";
		}
		int maxLength = 36; // Desilyon 33 basamaklıdır
		int lengthOfNumber = number.length();
		int remainder = maxLength - lengthOfNumber;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < remainder; i++) {
			sb.append("0");
		}
		sb.append(number);
		number = sb.toString();
		String[] birler = { "", "bir", "iki ", "üç ", "dört ", "beş ", "altı ", "yedi ", "sekiz ", "dokuz " };
		String[] onlar = { "", "on ", "yirmi ", "otuz ", "kırk ", "elli ", "altmış ", "yetmiş ", "seksen ", "doksan " };
		String[] binler = { "desilyon ", "nonilyon ", "oktilyon ", "septilyon ", "seksilyon ", "kentilyon ",
				"katrilyon ", "trilyon ", "milyar ", "milyon ", "bin", "" };
		int[] basamaklar = new int[3];
		String result = "";
		for (int i = 0; i < maxLength / 3; i++) {
			String temp = "";
			// yüzler basamağı
			basamaklar[0] = (int) (number.charAt(i * 3) - '0');
			// onlar basamağı
			basamaklar[1] = (int) (number.charAt(i * 3 + 1) - '0');
			// birler basamağı
			basamaklar[2] = (int) (number.charAt(i * 3 + 2) - '0');
			if (basamaklar[0] == 0)
				temp = ""; // yüzler basamağı boş
			else if (basamaklar[0] == 1)
				temp = "yüz"; // yüzler basamağında 1 varsa
			else
				temp = birler[basamaklar[0]] + "yüz "; // birleştir
			// Yüzler onlar birler basamağını birleştir
			temp += onlar[basamaklar[1]] + birler[basamaklar[2]];
			// basamak değeri oluşmadıysa yani 000 ise binler basamağını ekle
			if (!temp.isEmpty())
				temp += binler[i];
			// birbin yerine bin kullanılır
			if ((i > 1) && (temp.equalsIgnoreCase("birbin")))
				temp = "bin";
			if (temp != "")
				result += temp + " ";
		}

		return result.trim();
	}

	public static void main(String[] args) {

		System.out.println(converterFromNumToTr("0"));

	}
}