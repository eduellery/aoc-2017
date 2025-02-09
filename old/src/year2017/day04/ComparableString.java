package year2017.day04;

import java.util.Arrays;

public class ComparableString {

    private final String value;

    ComparableString(String value) {
        char[] word = value.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(word);
        this.value = new String(word);
    }

    public String getValue() {
        return value;
    }

    public boolean equals(Object o) {
        return (o instanceof ComparableString) && (((ComparableString)o).getValue()).equals(this.getValue());
    }

    public int hashCode() {
        return value.hashCode();
    }
}
