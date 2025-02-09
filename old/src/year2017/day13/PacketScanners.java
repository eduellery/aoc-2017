package year2017.day13;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class PacketScanners {

    public static void main(String[] args) {
        List<String> lines = FileUtils.getLines("2017day13test.txt", PacketScanners.class);
        System.out.println("Test 1: " + getSeverity(lines, false));
        System.out.println("Test 2: " + getSeverity(lines, true));
        List<String> input = FileUtils.getLines("2017day13.txt", PacketScanners.class);
        System.out.println("Part 1: " + getSeverity(input, false));
        System.out.println("Part 2: " + getSeverity(input, true));
    }

    private static int getSeverity(List<String> lines, boolean delayed) {
        int severity = 0;
        int delay = -1;
        List<Layer> layers = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(": ");
            Layer layer = new Layer(Integer.valueOf(data[0]),Integer.valueOf(data[1]));
            layers.add(layer);
        }

        if (!delayed) {
            for (Layer layer : layers) {
                if (layer.isCaught(0)) {
                    severity += layer.getSeverity();
                }
            }
        } else {
            boolean caught;
            do {
                caught = false;
                delay++;
                for (Layer layer : layers) {
                    if (layer.isCaught(delay)) {
                        caught = true;
                        break;
                    }
                }
            } while (caught && delay < 100000000);
        }

        return delayed ? delay : severity;
    }
}
