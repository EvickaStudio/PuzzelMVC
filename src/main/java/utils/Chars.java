package utils;

public class Chars {
    // get the char at the position
    public static String getCharAtPosition(int position) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(alphabet.charAt(position));
    }
}
