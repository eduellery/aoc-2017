package year2017.day17;

import utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class Spinlock {

    public static void main(String[] args) {
        List<Integer> list = createList(9, 3);
        System.out.println(list);
        Timer.startTimer();
        list = createList(2017, 316);
        System.out.println(list.get(list.indexOf(2017) + 1));
        System.out.println(getFirstItem(50000000,316));
        System.out.println(Timer.stopTimer());
    }

    private static List<Integer> createList(int size, int loop) {
        List<Integer> result = new ArrayList<>();
        result.add(0,0);
        int index = 0;
        for (int i = 1; i <= size; i++) {
            index = ((index + loop) % i) + 1;
            result.add(index,i);
        }
        return result;
    }

    private static int getFirstItem(int size, int loop) {
        int index = 0;
        int result = -1;
        for (int i = 1; i <= size; i++) {
            index = ((index + loop) % i) + 1;
            if (index == 1) {
                result = i;
            }
        }
        return result;
    }
}
