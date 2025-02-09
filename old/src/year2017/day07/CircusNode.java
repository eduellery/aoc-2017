package year2017.day07;

import utils.print.PrintUtils;
import utils.print.TreePrintable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CircusNode implements TreePrintable<CircusNode> {

    private List<CircusNode> children = new ArrayList<>();
    private CircusNode parent = null;
    private String name;
    private int weight;

    public CircusNode(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public void removeChild(CircusNode child) {
        this.children.remove(child);
    }

    public void addChild(CircusNode child) {
        this.children.add(child);
    }

    public List<CircusNode> getChildren() {
        return children;
    }

    public CircusNode getParent() {
        return parent;
    }

    public void setParent(CircusNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CircusNode that = (CircusNode) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CircusNode{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    public int getSupportedWeight() {
        int currentWeight = this.weight;
        if (this.children.size() == 0) {
            return currentWeight;
        } else {
            for (CircusNode c : this.children) {
                currentWeight += c.getSupportedWeight();
            }
            return currentWeight;
        }
    }

    public List<Integer> getChildrenSupportedWeights() {
        ArrayList<Integer> weights = new ArrayList<>();
        for (CircusNode c : this.getChildren()) {
            weights.add(c.getSupportedWeight());
        }
        return weights;
    }

    public void print() {
        PrintUtils.print(this, "", true);
    }

    public CircusNode getUnbalancedWeight() {
        if (this.hasUnbalancedChildren()) {
            for (CircusNode node : this.getChildren()) {
                if (node.hasUnbalancedChildren()) {
                    return node.getUnbalancedWeight();
                }
                else {
                    System.out.println("Discarding things down " + node);
                }
            }
            return getUnbalancedChild(this.getChildren());
        }
        return null;
    }

    private CircusNode getUnbalancedChild(List<CircusNode> children) {
        Set<Integer> weights = new HashSet<>();
        CircusNode someValue = null;
        CircusNode anotherValue = null;
        CircusNode repeatedValue = null;
        for (CircusNode node : children) {
            if (someValue != null && anotherValue != null) {
                return someValue.getSupportedWeight() == node.getSupportedWeight() ? anotherValue : someValue;
            }
            weights.add(node.getSupportedWeight());
            if (weights.size() == 1 && someValue == null) {
                someValue = node;
            }
            if (weights.size() == 1 && someValue != null) {
                repeatedValue = node;
            }
            if (weights.size() == 2 && repeatedValue != null) {
                return node;
            }
            if (weights.size() == 2 && repeatedValue == null) {
                anotherValue = node;
            }
        }
        return null;
    }

    private boolean hasUnbalancedChildren() {
        Set<Integer> damn = new HashSet<>();
        for (Integer i : this.getChildrenSupportedWeights()) {
            damn.add(i);
        }
        return damn.size() > 1;
    }
}
