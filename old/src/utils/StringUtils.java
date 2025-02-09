package utils;

public class StringUtils {
    public static String swap(String dancers, int index) {
        return dancers.substring(index) + dancers.substring(0, index);
    }

    public static String swap(String dancers, int source, int target) {
        if (target < source) {
            int temp = source;
            source = target;
            target = temp;
        }
        if (target == dancers.length() - 1)
            return dancers.substring(0, source) + dancers.charAt(target)
                    + dancers.substring(source + 1, target) + dancers.charAt(source);

        return dancers.substring(0, source) + dancers.charAt(target)
                + dancers.substring(source + 1, target) + dancers.charAt(source)
                + dancers.substring(target + 1, dancers.length());
    }

    public static String swap(String dancers, String partnerA, String partnerB) {
        return swap(dancers, dancers.indexOf(partnerA), dancers.indexOf(partnerB));
    }
}
