package year2018.day05;

import utils.FileUtils;

public class Alchemy {

    public static void main(String[] args) {
        String entry = "2018day05.txt";
        System.out.println("Part 1: " + checksum(FileUtils.getLine(entry, Alchemy.class)));
    }

    private static String checksum(String line) {
        String result = line.substring(0,1);
        int length = line.length();
        while (length > 0) {
            for (int i = 1; i < line.length(); i++) {
//                System.out.println(i);
                if (!line.substring(i - 1, i).equals(line.substring(i, i + 1)) && line.substring(i - 1, i).equalsIgnoreCase(line.substring(i, i + 1))) {
                    result = result.substring(0, result.length() - 1);
                    result += line.substring(i+1, i+2);
                    i++;
                } else {
                    result += line.substring(i, i + 1);
                }
            }
            if (result.equalsIgnoreCase(line)) {
                System.out.println("L: " + result.length());
                return result;
            }
//            System.out.println(length);
            length--;
            line = result;
            result = line.substring(0,1);
        }
        return null;
    }
}
