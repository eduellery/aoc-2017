package year2017.day06;

import java.util.ArrayList;

public class MemoryBlock {

    private int size;
    private ArrayList<Integer> blocks = new ArrayList<Integer>();

    public MemoryBlock(int size) {
        this.size = size;
    }

    public void setBlocks(int[] values) {
        if (values.length != size) {
            throw new RuntimeException("Was expecting " + size + ", found " + values.length);
        }
        for (int value : values) {
            this.blocks.add(value);
        }
    }

    public MemoryBlock getReorderedBlock() {
        try {
            MemoryBlock memoryBlock = (MemoryBlock) this.clone();
            int maxIndex = 0;
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < this.blocks.size(); i++) {
                int value = this.blocks.get(i);
                if (value > maxValue) {
                    maxIndex = i;
                    maxValue = value;
                }
            }
            memoryBlock.reorder(maxIndex, maxValue);

            return memoryBlock;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void reorder(int maxIndex, int maxValue) {
        this.blocks.set(maxIndex, 0);
        maxIndex++;
        while (maxValue > 0) {
            this.increaseValue(maxIndex % this.size);
            maxValue--;
            maxIndex++;
        }
    }

    private void increaseValue(int i) {
        int value = this.blocks.get(i);
        this.blocks.set(i, value+1);
    }

    @Override
    public String toString() {
        String result = "{ ";
        for (int value : blocks) {
            result += (value + " ");
        }
        result += ("} " + size);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryBlock that = (MemoryBlock) o;

        return blocks.equals(that.blocks);
    }

    @Override
    public int hashCode() {
        return blocks.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MemoryBlock clone = new MemoryBlock(this.size);
        clone.blocks = (ArrayList<Integer>) this.blocks.clone();
        return clone;
    }
}
