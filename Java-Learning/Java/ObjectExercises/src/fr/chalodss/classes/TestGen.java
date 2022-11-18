package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
public final class TestGen {

    private TestGen() {

    }

    public static Integer maxInt(Integer[] tab) {
        var max = tab[0];

        for (var i = 1; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        return max;
    }

    public static Integer minInt(Integer[] tab) {
        var min = tab[0];

        for (var i = 1; i < tab.length; i++) {
            if (tab[i] < min) {
                min = tab[i];
            }
        }
        return min;
    }

    public static String maxStr(String[] tab) {
        var max = tab[0];

        for (var i = 1; i < tab.length; i++) {
            if (tab[i].compareTo(max) > 0) {
                max = tab[i];
            }
        }
        return max;
    }

    public static String minStr(String[] tab) {
        var min = tab[0];

        for (var i = 1; i < tab.length; i++) {
            if (tab[i].compareTo(min) < 0) {
                min = tab[i];
            }
        }
        return min;
    }

    public static <T extends Comparable<T>> MinMax<T> minMax(T[] tab) {
        T min = tab[0];
        T max = tab[0];

        for (var i = 1; i < tab.length; i++) {
            if (tab[i].compareTo(min) < 0) {
                min = tab[i];
            }
            if (tab[i].compareTo(max) > 0) {
                max = tab[i];
            }
        }
        return new MinMax<>(min, max);
    }

}
