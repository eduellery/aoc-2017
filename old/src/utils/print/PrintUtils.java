package utils.print;

import java.util.List;

public class PrintUtils {

    public static void print(TreePrintable treePrintable, String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + treePrintable.toString());
        List<TreePrintable> children = treePrintable.getChildren();
        for (int i = 0; i < children.size() - 1; i++) {
            PrintUtils.print(children.get(i), prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            PrintUtils.print(children.get(children.size() - 1),prefix + (isTail ?"    " : "│   "), true);
        }
    }

}
