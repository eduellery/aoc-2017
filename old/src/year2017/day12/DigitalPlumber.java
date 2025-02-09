package year2017.day12;

import utils.FileUtils;

import java.util.*;

public class DigitalPlumber {

    public static void main(String[] args) {
        List<String> lines = FileUtils.getLines("2017day12.txt", DigitalPlumber.class);
        List<String[]> nodes = new ArrayList<>();
        for (String line : lines) {
            nodes.add(line.split(" <-> "));
        }
        Map<Integer, Set<Integer>> nodeMap = new HashMap<>();
        Set<Set<Integer>> groups = new HashSet<>();

        for (String[] node : nodes) {
            Integer keyNode = Integer.valueOf(node[0]);
            String[] connectedNodes = node[1].split(", ");
            Set<Integer> nodeValues = new HashSet<>();
            for (String connectedNode : connectedNodes) {
                nodeValues.add(Integer.valueOf(connectedNode));
            }
            nodeMap.put(keyNode, nodeValues);
        }

        for (Integer key : nodeMap.keySet()) {
            for (Integer value : nodeMap.get(key)) {
                Set<Integer> newValues = nodeMap.get(value);
                newValues.addAll(nodeMap.get(key));
                newValues.add(key);
            }
        }

        for (Set<Integer> group : nodeMap.values()) {
            if (!groups.contains(group)) {
                groups.add(group);
            }
        }

        System.out.println("Part 1: " + nodeMap.get(0));
        System.out.println("Part 1: " + nodeMap.get(0).size());
        System.out.println("Part 2: " + groups);
        System.out.println("Part 2: " + groups.size());
    }

}
