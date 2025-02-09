package year2018.day07;

import java.util.Objects;

public class Step {
    
    private char current, next;
    
    public Step(char current, char next) {
        this.current = current;
        this.next = next;
    }

    public char getCurrent() {
        return current;
    }

    public char getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return current == step.current &&
                next == step.next;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, next);
    }
}
