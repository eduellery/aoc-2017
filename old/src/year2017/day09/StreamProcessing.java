package year2017.day09;

import utils.FileUtils;

public class StreamProcessing {

    private static final char OG = '{';
    private static final char CG = '}';
    private static final char OT = '<';
    private static final char CT = '>';
    private static final char IG = '!';

    public static void main(String[] args) {
        String line = FileUtils.getLine("2017day09.txt", new Object() { }.getClass().getEnclosingClass());
        String[] values1 = {"{}","{{{}}}","{{},{}}","{{{},{},{{}}}}","{<a>,<a>,<a>,<a>}","{{<ab>},{<ab>},{<ab>},{<ab>}}","{{<!!>},{<!!>},{<!!>},{<!!>}}","{{<a!>},{<a!>},{<a!>},{<ab>}}"};
        int[] results1 = {1,6,5,16,1,9,9,3};
        System.out.println(testResults(values1, results1, true));
        String[] values2 = {"{<>}","{<random characters>}","{<<<<>}","{<{!>}>}","{<!!>}","{<!!!>>}","{<{o\"i!a,<{i<a>}"};
        int[] results2 = {0,17,3,2,0,0,10};
        System.out.println(testResults(values2, results2, false));
        System.out.println(getGroupsNumber(line).getCount());
        System.out.println(getGroupsNumber(line).getTrash());
    }

    private static boolean testResults(String[] values, int[] results, boolean firstPart) {
        if (values.length != results.length) {
            System.out.println("Different input sizes: " + values.length + " and " + results.length);
            return false;
        }
        for (int i = 0; i < values.length; i++) {
            int test = firstPart ? getGroupsNumber(values[i]).getCount() : getGroupsNumber(values[i]).getTrash();
            if (results[i] != test) {
                System.out.println("Expecting " + results[i] + " for " + values[i] + " instead of " + test);
                return false;
            }
        }
        return true;
    }

    private static Result getGroupsNumber(String value) {
        int count = 0;
        int level = 0;
        int trash = 0;
        boolean trashOpen = false;
        boolean ignoreNxt = false;
        for (char c : value.toCharArray()) {
            if (ignoreNxt) {
                ignoreNxt = false;
                continue;
            }
            switch (c) {
                case IG:
                    ignoreNxt = true;
                    break;
                case OT:
                    if (!trashOpen) {
                        trashOpen = true;
                    } else {
                        trash++;
                    }
                    break;
                case CT:
                    if (trashOpen) {
                        trashOpen = false;
                    }
                    break;
                case OG:
                    if (!trashOpen) {
                        level++;
                    } else {
                        trash++;
                    }
                    break;
                case CG:
                    if (!trashOpen) {
                        count += level;
                        level--;
                    } else {
                        trash++;
                    }
                    break;
                default:
                    if (trashOpen) {
                        trash++;
                    }
            }
        }
        return new Result(count,trash);
    }

    private static class Result {

        int count = -1;
        int trash = -1;

        public Result(int count, int trash) {
            this.count = count;
            this.trash = trash;
        }

        public int getCount() {
            return count;
        }

        public int getTrash() {
            return trash;
        }
    }
}
