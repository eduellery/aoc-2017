package year2017.day16;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class PermutationPromenade {

    public static void main(String[] args) {
        String line = FileUtils.getLine("2017day16.txt", PermutationPromenade.class);
        String dancers = "abcde";
        List<DanceMove> danceMoves = createDanceSteps("s1,x3/4,pe/b".split(","));
        System.out.println(dancers);
        dancers = "abcdefghijklmnop";
        danceMoves = createDanceSteps(line.split(","));
        for (int i = 0 ; i < 1000000000 ; i++){
            dancers = moveDancers(dancers, danceMoves);
            if (i % 1000000 == 0) {
                System.out.println(i + ": " + dancers);
            }
        }
        System.out.println(dancers);
    }

    private static String moveDancers(String dancers, List<DanceMove> danceMoves) {
        for (DanceMove move : danceMoves) {
            dancers = move.dance(dancers);
        }
        return dancers;
    }

    private static List<DanceMove> createDanceSteps(String[] moves) {
        List<DanceMove> danceMoves = new ArrayList<>();
        String[] partners;
        int source, target;
        String partnerA, partnerB;
        for (String move : moves) {
            switch (move.substring(0,1)) {
                case "s":
                    danceMoves.add(new DanceMove.Builder().withMove(DanceMove.Move.S).withIndex(Integer.valueOf(move.substring(1))).build());
                    break;
                case "x":
                    partners = move.substring(1).split("/");
                    source = Integer.valueOf(partners[0]);
                    target = Integer.valueOf(partners[1]);
                    danceMoves.add(new DanceMove.Builder().withMove(DanceMove.Move.X).withSource(source).withTarget(target).build());
                    break;
                case "p":
                    partners = move.substring(1).split("/");
                    partnerA = partners[0];
                    partnerB = partners[1];
                    danceMoves.add(new DanceMove.Builder().withMove(DanceMove.Move.P).withPartnerA(partnerA).withPartnerB(partnerB).build());
                    break;
                default:
                    throw new RuntimeException("Unexpected move " + move.substring(0,1));
            }
        }
        return danceMoves;
    }
}
