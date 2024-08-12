package exercise;

// BEGIN
public class ReversedSequence implements java.lang.CharSequence{
    char[] charSequence;

    private char[] reverseChars(char[] chars) {
        int charsCount = chars.length;
        char[] reversedChars = new char[charsCount];

        for (int index = 0; index < charsCount; index++) {
            int reversedIndex = charsCount - index - 1;
            reversedChars[reversedIndex] = chars[index];
        }

        return reversedChars;
    }

    public ReversedSequence(String text) {
        charSequence = reverseChars(text.toCharArray());
    }

    @Override
    public int length() {
        return charSequence.length;
    }

    @Override
    public char charAt(int index) {
        return charSequence[index];
    }

    @Override
    public String toString() {
        return String.valueOf(charSequence);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        int subCharsCount = end - start;
        char[] subChars = new char[subCharsCount];

        for (int index = start; index < end; index++) {
            int subIndex = index - start;
            subChars[subIndex] = charAt(index);
        }

        char[] reversedSubChars = reverseChars(subChars);

        return new ReversedSequence(String.valueOf(reversedSubChars));
    }
}
// END
