package year2017.day04;

import utils.FileUtils;

import java.util.*;

public class HighEntropyPassphrases {

    public static void main(String[] args) {
        List<String> phrases = FileUtils.getLines("2017day04.txt", HighEntropyPassphrases.class);
        int valid = 0;
        for (String phrase : phrases) {
            if (isValidRepeated(phrase)) {
                valid++;
            }
        }
        System.out.println(valid);
        valid = 0;
        for (String phrase : phrases) {
            if (isValidAnagram(phrase)) {
                valid++;
            }
        }
        System.out.println(valid);
    }

    private static boolean isValidRepeated(String phrase) {
        String[] words = phrase.split("\\s");
        List<String> list = Arrays.asList(words);
        Set<String> set = new HashSet<>(Arrays.asList(words));
        return list.size() == set.size();
    }

    private static boolean isValidAnagram(String phrase) {
        String[] words = phrase.split("\\s");
        List<ComparableString> list = new ArrayList<>();
        for (String word : words) {
            list.add(new ComparableString(word));
        }
        Set<ComparableString> set = new HashSet<>(list);
        return list.size() == set.size();
    }

}

