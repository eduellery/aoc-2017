package year2017.day07;
import utils.FileUtils;

import java.util.*;

public class RecursiveCircus {

    private static Set<CircusNode> circusNodeSet = new HashSet<>();

    public static void main (String args[]) {
        List<String> phrases = FileUtils.getLines("2017day07.txt", RecursiveCircus.class);
        CircusNode lastInsertedNode = createSet(phrases);
        System.out.println(circusNodeSet.size());
        System.out.println(circusNodeSet);
        System.out.println(lastInsertedNode);
        CircusNode root = getRoot(lastInsertedNode);
        System.out.println(root);
        List<Integer> weights = root.getChildrenSupportedWeights();
        root.print();
        System.out.println(weights);
        System.out.println(root.getUnbalancedWeight());
    }

    private static CircusNode getRoot(CircusNode lastInsertedNode) {
        CircusNode root = lastInsertedNode;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return  root;
    }

    private static CircusNode createSet(List<String> lines) {
        CircusNode lastInsertedNode = null;
        int lPar;
        int rPar;
        int arrowSign;
        for (String line : lines) {
            lPar = line.indexOf(" (");
            rPar = line.indexOf(")");
            arrowSign = line.indexOf(" -> ");
            String name = line.substring(0,lPar);
            int weight = Integer.valueOf(line.substring(lPar + 2, rPar));
            CircusNode circusNode = new CircusNode(name, weight);
            if (arrowSign != -1) {
                List<String> children = getChildren(line.substring(arrowSign + 4));
                for (String childName : children) {
                    CircusNode child = new CircusNode(childName.trim(), -1);
                    if (!circusNodeSet.contains(child)) {
                        circusNodeSet.add(child);
                    } else {
                        child = getRightChild(child);
                    }
                    child.setParent(circusNode);
                    circusNode.addChild(child);
                }
            }
            if (!circusNodeSet.contains(circusNode)) {
                circusNodeSet.add(circusNode);
            } else {
                updateNode(circusNode);
            }
            lastInsertedNode = circusNode;
        }
        return lastInsertedNode;
    }

    private static void updateNode(CircusNode newFound) {
        for (Iterator<CircusNode> it = circusNodeSet.iterator(); it.hasNext(); ) {
            CircusNode f = it.next();
            if (f.equals(newFound)) {
                CircusNode parent = f.getParent();
                newFound.setParent(parent);
                parent.removeChild(f);
                parent.addChild(newFound);
                circusNodeSet.remove(f);
                circusNodeSet.add(newFound);
                return;
            }
        }
        throw new RuntimeException("Expecting to find a node in the set");
    }

    private static CircusNode getRightChild(CircusNode child) {
        for (Iterator<CircusNode> it = circusNodeSet.iterator(); it.hasNext(); ) {
            CircusNode f = it.next();
            if (f.equals(child)) {
                return f;
            }
        }
        throw new RuntimeException("Expecting to find a child in the set");
    }

    private static List<String> getChildren(String substring) {
        String[] children = substring.trim().split(",");
        return Arrays.asList(children);
    }

}