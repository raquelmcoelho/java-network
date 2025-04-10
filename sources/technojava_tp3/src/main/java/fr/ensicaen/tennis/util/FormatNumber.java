package fr.ensicaen.tennis.util;

public class FormatNumber {
    public static String formatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (i%2 == 0 && i!=0) {
                sb.append(' ');
            }
            sb.append(number.charAt(i));
        }
        return sb.toString();
    }
}
