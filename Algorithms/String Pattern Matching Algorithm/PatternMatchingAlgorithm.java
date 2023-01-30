import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class PatternMatchingAlgorithm {

    /**
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> knuthMorrisPratt(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid Input Detected");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        List<Integer> list = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        int idx = 0;
        int pIdx = 0;
        int[] shift = buildFailureTable(pattern, comparator);
        while (idx + pattern.length() <= text.length()) {
            while (pIdx < pattern.length() && comparator.compare(pattern.charAt(pIdx),
                    text.charAt(idx + pIdx)) == 0) {
                pIdx++;
            }
            if (pIdx == 0) {
                idx++;
            } else {
                if (pattern.length() == pIdx) {
                    list.add(idx);
                }
                idx += pIdx - shift[pIdx - 1];
                pIdx = shift[pIdx - 1];
            }
        }
        return list;
    }

    /**
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        if (pattern.length() == 0) {
            return new int[0];
        }
        int i = 0;
        int j = i + 1;
        int[] failure = new int[pattern.length()];
        failure[0] = 0;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                failure[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    failure[j] = 0;
                    j++;
                } else {
                    i = failure[i - 1];
                }
            }
        }
        return failure;
    }

    /**
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid Input Detected");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        List<Integer> list = new ArrayList<>();
        Map<Character, Integer> lastT = buildLastTable(pattern);
        int i = 0;
        int j;
        while (i <= text.length() - pattern.length()) {
            j = pattern.length() - 1;
            while (j >= 0 && comparator.compare(pattern.charAt(j), text.charAt(i + j)) == 0) {
                j--;
            }
            if (j == -1) {
                list.add(i);
                i++;
            } else {
                char word = text.charAt(i + j);
                int shift = lastT.getOrDefault(word, -1);
                if (shift < j) {
                    i = i + j - shift;
                } else {
                    i++;
                }
            }
        }
        return list;
    }

    /**
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        Map<Character, Integer> lastTable = new HashMap<>();
        for (int i = 0;  i < pattern.length(); i++) {
            lastTable.put(pattern.charAt(i), i);
        }
        return lastTable;
    }
    private static final int BASE = 113;

    /**
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid Input Detected");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        List<Integer> list = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        int hashText = 0;
        int hashPattern = 0;
        int pow = 1;
        for (int i = pattern.length() - 1; i > -1; i--) {
            hashText += text.charAt(i) * pow;
            hashPattern += pattern.charAt(i) * pow;
            if (i != 0) {
                pow *= BASE;
            }
        }
        int i = 0;
        while (text.length() - pattern.length() >= i) {
            if (hashText == hashPattern) {
                int j = 0;
                while ((pattern.length() > j) && comparator.compare(
                        text.charAt(i + j), pattern.charAt(j)) == 0) {
                    j++;
                }
                if (j == pattern.length()) {
                    list.add(i);
                }
            }
            if (text.length() - pattern.length() - 1 >= i) {
                hashText = (hashText - text.charAt(i) * pow) * BASE
                        + text.charAt(i + pattern.length());
            }
            i++;
        }
        return list;
    }

    /**
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMooreGalilRule(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Invalid Input Detected");
        }
        if (text == null || comparator == null) {
            throw new IllegalArgumentException("Invalid Null Input Detected");
        }
        List<Integer> list = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        Map<Character, Integer> lastT = buildLastTable(pattern);
        int[] fail = buildFailureTable(pattern, comparator);
        int k = pattern.length() - fail[pattern.length() - 1];
        int i = 0;
        int p = 0;
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= p && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j--;
            }
            if (j < p) {
                list.add(i);
                p = pattern.length() - k;
                i += k;
            } else {
                p = 0;
                int move = lastT.getOrDefault(text.charAt(i + j), -1);
                if (j > move) {
                    i = i + j - move;
                } else {
                    i++;
                }
            }
        }
        return list;
    }
}
