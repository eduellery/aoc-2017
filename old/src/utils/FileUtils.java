package utils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> getLines(String fileName, Class<?> clazz) {
        BufferedReader br = readFile(fileName, clazz);
        return getLines(br);
    }

    public static String getLine(String fileName, Class<?> clazz) {
        BufferedReader br = readFile(fileName, clazz);
        return getLine(br);
    }

    private static BufferedReader readFile(String fileName, Class<?> clazz) {
        BufferedReader br = null;
        URL path = clazz.getResource(fileName);
        File file = new File(path.getPath());
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    private static List<String> getLines(BufferedReader br) {
        List<String> lines = new ArrayList<>();
        String line;

        try {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static String getLine(BufferedReader br) {
        String line;
        String result = null;
        int count = 0;

        try {
            while ((line = br.readLine()) != null) {
                count++;
                result = line;
                if (count > 1) {
                    throw new RuntimeException("More than one line");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
