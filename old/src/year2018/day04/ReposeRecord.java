package year2018.day04;

import utils.FileUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ReposeRecord {
    
    private static String GUARD = "Guard #";
    private static String SHIFT = " begins shift";
    private static String SLEEP = "falls asleep";
    private static String WAKES = "wakes up";

    public static void main(String[] args) {
        try {
            String entry = "2018day04.txt";
            System.out.println("Part 1: " + checksum(FileUtils.getLines(entry, ReposeRecord.class)));
        } catch (ParseException e) {
            throw new RuntimeException("Parsing error");
        }
    }

    private static Integer checksum(List<String> lines) throws ParseException {
        TreeMap<Date,String> history = new TreeMap<>();
        for (String line : lines) {
            String parsedDate = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            String pattern = "yyyy-MM-dd hh:mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(parsedDate);
            history.put(date, line.substring(line.indexOf("]") + 2));
        }
        HashMap<Integer, Integer> minutesMap = new HashMap<>();
        int currentGuard = -1;
        int sleptMinutes = 0;
        int currentMinute = 0;
        for (Date date : history.keySet()) {
            String action = history.get(date);
            if (action.contains(GUARD)) {
                if (currentGuard != -1) {
                    if (minutesMap.containsKey(currentGuard)) {
                        minutesMap.put(currentGuard, minutesMap.get(currentGuard) + sleptMinutes);
                    } else {
                        minutesMap.put(currentGuard, sleptMinutes);
                    }
                }
                currentGuard = Integer.valueOf(action.substring(7, action.indexOf(SHIFT)));
                sleptMinutes = 0;
            } else if (action.contains(SLEEP)) {
                currentMinute = date.getMinutes();
            } else if (action.contains(WAKES)) {
                sleptMinutes += date.getMinutes() - currentMinute;
            }
        }
        int minutesSlept = -1;
        int sleepyGuard = -1;
        for (Integer guard : minutesMap.keySet()) {
            if (minutesMap.get(guard) > minutesSlept) {
                sleepyGuard = guard;
                minutesSlept = minutesMap.get(guard);
            }
//            System.out.println("Guard " + guard + " slept for " + minutesMap.get(guard) + " in total");
        }

        boolean condition = false;
        HashMap<Integer,Integer> sleeping = new HashMap<>(60);
        for (int i = 0; i < 60; i++) {
            sleeping.put(i,0);
        }
        for (Date date : history.keySet()) {
            String action = history.get(date);
            if (action.contains(GUARD)) {
                if (sleepyGuard == Integer.valueOf(action.substring(7, action.indexOf(SHIFT)))) {
                    condition = true;
                } else {
                    condition = false;
                }
            } else {
                if (condition) {
                    if (action.contains(SLEEP)) {
                        currentMinute = date.getMinutes();
                    } else if (action.contains(WAKES)) {
                        for (int i = currentMinute; i < date.getMinutes(); i++) {
                            sleeping.put(i, sleeping.get(i) + 1);
                        }
                    }
                }
            }
        }
        int mostSleptMinute = -1;
        minutesSlept = 0;
        for (Integer minute : sleeping.keySet()) {
            if (minutesSlept < sleeping.get(minute)) {
                minutesSlept = sleeping.get(minute);
                mostSleptMinute = minute;
            }
        }
        System.out.println("Sleepy guard " + sleepyGuard + " slept " + minutesSlept + " minutes on minute " + mostSleptMinute);

        //////////////////////////////////////////////////////////////////////////////////////////
        
        for (Integer minute : sleeping.keySet()) {
            sleeping.put(minute, 0);
        }

        currentMinute = 0;
        for (Date date : history.keySet()) {
            String action = history.get(date);
            if (action.contains(SLEEP)) {                 
                currentMinute = date.getMinutes();             
            } else if (action.contains(WAKES)) {
                for (int i = currentMinute; i < date.getMinutes(); i++) {                     
                    sleeping.put(i, sleeping.get(i) + 1);                 
                }
                currentMinute = 0;
            } 
        }

        mostSleptMinute = -1;
        minutesSlept = -1;
        for (Integer minute : sleeping.keySet()) {
            if (sleeping.get(minute) > minutesSlept) {
                mostSleptMinute = minute;
                minutesSlept = sleeping.get(minute);
            }
//            System.out.println("Slept " + sleeping.get(minute) + " minutes on minute " + minute);
        }
        System.out.println("Most slept minute was " + mostSleptMinute + " with " + minutesSlept + " minutes");

        for (Integer guard : minutesMap.keySet()) {
            minutesMap.put(guard, 0);
        }

        mostSleptMinute = 51;
        currentGuard = -1;
        condition = false;
        for (Date date : history.keySet()) {
            String action = history.get(date);
            if (action.contains(GUARD)) {
                currentGuard = Integer.valueOf(action.substring(7, action.indexOf(SHIFT)));
            } else if (action.contains(SLEEP)) {
                if (date.getMinutes() <= mostSleptMinute) {
                    condition = true;
                }
            } else if (action.contains(WAKES)) {
                if (condition && date.getMinutes() > mostSleptMinute) {
                    minutesMap.put(currentGuard, minutesMap.get(currentGuard) + 1);
                }
                condition = false;
            }
        }

        minutesSlept = -1;
        sleepyGuard = -1;
        for (Integer guard : minutesMap.keySet()) {
            if (minutesMap.get(guard) >= minutesSlept) {
                sleepyGuard = guard;
                minutesSlept = minutesMap.get(guard);
            }
//            System.out.println("Guard " + guard + " slept for " + minutesMap.get(guard) + " in total");
        }
        System.out.println("Sleepy guard: " + sleepyGuard);
        return 0;
    }
}
