package utils;

public class Timer {

    private static long startTime;

    public static void startTimer() {
       startTime  = System.nanoTime();
    }

    public static String stopTimer() {
        long duration = System.nanoTime() - startTime;
        return "Completed in " + (duration  / 1000000000.0) +  " seconds.";
    }
}
