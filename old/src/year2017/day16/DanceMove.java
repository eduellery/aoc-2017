package year2017.day16;

import utils.StringUtils;

public class DanceMove {

    private Move move;
    private int index;
    private int source;
    private int target;
    private String partnerA;
    private String partnerB;

    private DanceMove(Builder builder) {
        this.move = builder.move;
        this.index = builder.index;
        this.source = builder.source;
        this.target = builder.target;
        this.partnerA = builder.partnerA;
        this.partnerB = builder.partnerB;
    }

    @Override
    public String toString() {
        return move.toString() + " " + source + " " + target;
    }

    public String dance(String dancers) {
        switch (this.move) {
            case S:
                dancers = StringUtils.swap(dancers, dancers.length() - index);
                break;
            case X:
                dancers = StringUtils.swap(dancers, source, target);
                break;
            case P:
                dancers = StringUtils.swap(dancers, partnerA, partnerB);
                break;
            default:
                throw new RuntimeException("Unknown dance move: " + this.move);
        }
        return dancers;
    }

    public enum Move {
        S, X, P
    }

    public static class Builder {

        private Move move;
        private int index;
        private int source;
        private int target;
        private String partnerA;
        private String partnerB;

        public Builder withMove(Move move) {
            this.move = move;
            return this;
        }

        public Builder withIndex(int index) {
            this.index = index;
            return this;
        }

        public Builder withSource(int source) {
            this.source = source;
            return this;
        }

        public Builder withTarget(int target) {
            this.target = target;
            return this;
        }

        public Builder withPartnerA(String partnerA) {
            this.partnerA = partnerA;
            return this;
        }

        public Builder withPartnerB(String partnerB) {
            this.partnerB = partnerB;
            return this;
        }

        public DanceMove build() {
            return new DanceMove(this);
        }
    }

}
