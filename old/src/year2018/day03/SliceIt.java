package year2018.day03;

import utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SliceIt {

    public static void main(String[] args) {
//        String[] values = {"abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"};
//        System.out.println(checksum(List.of(values)));
        String entry = "2018day03.txt";
        System.out.println("Part 1: " + checksum(FileUtils.getLines(entry, SliceIt.class)));
//        System.out.println("Part 2: " + findId(FileUtils.getLines(entry, SliceIt.class)));
    }

    private static String findId(List<String> input) {
        for (String aValue : input) { 
            for (String bValue : input) {
                if (aValue != bValue) {
                    if (levenshteinDistance(aValue, bValue) == 1) {
                        return printSameChars(aValue, bValue);
                    }
                }
            }
        }
        throw new RuntimeException("No id found");
    }

    private static String printSameChars(String aValue, String bValue) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < aValue.length(); i++) {
            if (aValue.charAt(i) == bValue.charAt(i)) {
                builder.append(aValue.charAt(i));
            }
        }
        return builder.toString();
    }

    private static long checksum(List<String> input) {
        Map<Integer,Map<Integer,Integer>> tissue = new HashMap<>();
        int id = 0;
        for (String value : input) {
            id++;
            System.out.println(value);
            int column = Integer.valueOf(value.substring(value.indexOf("@") + 2, value.indexOf(",")));
            int row = Integer.valueOf(value.substring(value.indexOf(",") + 1, value.indexOf(":")));
            int width = Integer.valueOf(value.substring(value.indexOf(":") + 2, value.indexOf("x")));
            int height = Integer.valueOf(value.substring(value.indexOf("x") + 1));
            System.out.println("#" + id + " @ " + column + "," + row + ": " + width + "x" + height);
            for (int i = row; i < height; i++) {
                if (!tissue.containsKey(i)) {
                    tissue.put(i, new HashMap<>());
                }
                Map<Integer, Integer> rip = tissue.get(i);
                for (int j = column; j < width; j++) {
                    if (!rip.containsKey(j)) {
                        rip.put(j, 1);
                    } else {
                        rip.put(j, rip.get(j) + 1);
                    }
                }
            }
        }
        return 0;
    }

    private static int countTuples(Map<Character, Integer> charCount, int duplicates) {
        for (char key : charCount.keySet()) {
            if (charCount.get(key) == duplicates) {
                return 1;
            }
        }
        return 0;
    }
    
    private static Map<Character, Integer> getCharCount(String value) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char a : value.toCharArray()) {
            if (charCount.containsKey(a)) {
                charCount.put(a, charCount.get(a) + 1);
            } else {
                charCount.put(a, 1);
            }
        }
        return charCount;
    }

    public static int levenshteinDistance( String s1, String s2 ) {
        return dist( s1.toCharArray(), s2.toCharArray() );
    }

    public static int dist( char[] s1, char[] s2 ) {

        // distance matrix - to memoize distances between substrings
        // needed to avoid recursion
        int[][] d = new int[ s1.length + 1 ][ s2.length + 1 ];

        // d[i][j] - would contain distance between such substrings:
        // s1.subString(0, i) and s2.subString(0, j)

        for( int i = 0; i < s1.length + 1; i++ ) {
            d[ i ][ 0 ] = i;
        }

        for(int j = 0; j < s2.length + 1; j++) {
            d[ 0 ][ j ] = j;
        }

        for( int i = 1; i < s1.length + 1; i++ ) {
            for( int j = 1; j < s2.length + 1; j++ ) {
                int d1 = d[ i - 1 ][ j ] + 1;
                int d2 = d[ i ][ j - 1 ] + 1;
                int d3 = d[ i - 1 ][ j - 1 ];
                if ( s1[ i - 1 ] != s2[ j - 1 ] ) {
                    d3 += 1;
                }
                d[ i ][ j ] = Math.min( Math.min( d1, d2 ), d3 );
            }
        }
        return d[ s1.length ][ s2.length ];
    }
}
