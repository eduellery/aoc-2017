package year2018.day07;

import utils.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SumOfParts {

    public static void main(String[] args) {
        calculate(FileUtils.getLines("2018day07.txt", SumOfParts.class));
//        calculate2(FileUtils.getLines("2018day07.txt", SumOfParts.class));
    }

    private static void calculate(List<String> lines) {
        Set<Step> steps = new HashSet<>();
        for (String line : lines) {
            char current = line.charAt(5);
            char next = line.charAt(36);
            steps.add(new Step(current, next));
        }
        Set<Character> currents = new TreeSet<>();
        Set<Character> nexts = new TreeSet<>();
        while (steps.size() > 0) {
            for (Step step : steps) {
                currents.add(step.getCurrent());
                nexts.add(step.getNext());
            }
            if (currents.removeAll(nexts)) {
                System.out.println(currents);
            }
            Set<Step> newSteps = new HashSet<>();
            Set<Character> candidates = new TreeSet<>();
            for (Step step : steps) {
                boolean found = false;
                for (Character current : currents) {
                    if (step.getCurrent() == current) {
                        found = true;
                    }
                }
                if (found) {
                    candidates.add(step.getNext());
                } else {
                    newSteps.add(step);
                }
            }
            steps = newSteps;
            currents = candidates;
            nexts = new TreeSet<>();
        }
    }
    //ACEUBDLSXYZIKMNTWFGJVPOHR
    private static void calculate2(List<String> lines) {
        Set<Step> steps = new HashSet<>();
        for (String line : lines) {
            char current = line.charAt(5);
            char next = line.charAt(36);
            steps.add(new Step(current, next));
        }
        Set<Character> currents = new TreeSet<>();
        Set<Character> nexts = new TreeSet<>();
        while (steps.size() > 0) {
            for (Step step : steps) {
                currents.add(step.getCurrent());
                nexts.add(step.getNext());
            }
            if (currents.removeAll(nexts)) {
                System.out.println(currents);
            }
            Set<Step> newSteps = new HashSet<>();
            Set<Character> candidates = new TreeSet<>();
            for (Step step : steps) {
                boolean found = false;
                for (Character current : currents) {
                    if (step.getCurrent() == current) {
                        found = true;
                    }
                }
                if (found) {
                    candidates.add(step.getNext());
                } else {
                    newSteps.add(step);
                }
            }
            steps = newSteps;
            currents = candidates;
            nexts = new TreeSet<>();
        }
    }
    //ACEUBDLSXYZIKMNTWFGJVPOHR
}
