package utils.print;

import java.util.List;

public interface TreePrintable<T extends TreePrintable<T>> {

    List<T> getChildren();

    void print();
}
