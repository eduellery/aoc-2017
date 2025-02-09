package year2017.day06;

import java.util.ArrayList;
import java.util.List;

public class MemoryReallocation {

    private static List<MemoryBlock> blockSet = new ArrayList<MemoryBlock>();

    public static void main(String[] args) {
        int[] testValues = {0, 2, 7, 0};
        MemoryBlock memoryBlock = new MemoryBlock(4);
        memoryBlock.setBlocks(testValues);
        System.out.println(memoryBlock);
        memoryBlock = findLoop(memoryBlock);
        System.out.println(blockSet.size());
        System.out.println(blockSet.size() - blockSet.indexOf(memoryBlock));
        int[] puzzle1 = {4,10,4,1,8,4,9,14,5,1,14,15,0,15,3,5};
        blockSet = new ArrayList<>();
        memoryBlock = new MemoryBlock(puzzle1.length);
        memoryBlock.setBlocks(puzzle1);
        System.out.println(memoryBlock);
        memoryBlock = findLoop(memoryBlock);
        System.out.println(blockSet.size());
        System.out.println(blockSet.size() - blockSet.indexOf(memoryBlock));
    }

    private static MemoryBlock findLoop(MemoryBlock memoryBlock) {
        blockSet.add(memoryBlock);
        memoryBlock = memoryBlock.getReorderedBlock();
        while (!blockSet.contains(memoryBlock)) {
            blockSet.add(memoryBlock);
            memoryBlock = memoryBlock.getReorderedBlock();
        }
        return memoryBlock;
    }
}
