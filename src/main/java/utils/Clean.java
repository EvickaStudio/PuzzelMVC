package utils;

public class Clean {
    public static int cleanSize(int sz){
        // 1 < size < 6
        // limit because there are only 26 characters in the alphabet
        // 36 would be too much
        return Math.max(2, Math.min(sz, 5));
    }
}
