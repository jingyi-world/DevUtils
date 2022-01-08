package dev.standard.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * detail: 排序比较器工具类
 * @author Ttt
 */
public final class ComparatorUtils {

    private ComparatorUtils() {
    }

    /**
     * List 反转处理
     * @param list 集合
     * @return {@code true} success, {@code false} fail
     */
    public static boolean reverse(final List<?> list) {
        if (list != null) {
            Collections.reverse(list);
            return true;
        }
        return false;
    }

    /**
     * List 排序处理
     * @param list       集合
     * @param comparator 排序比较器
     * @param <T>        泛型
     * @return {@code true} success, {@code false} fail
     */
    public static <T> boolean sort(
            final List<T> list,
            final Comparator<? super T> comparator
    ) {
        if (list != null) {
            list.sort(comparator);
            return true;
        }
        return false;
    }

    /**
     * List 升序处理
     * @param list 集合
     * @param <T>  泛型
     * @return {@code true} success, {@code false} fail
     */
    public static <T extends Comparable<? super T>> boolean sortAsc(final List<T> list) {
        if (list != null) {
            Collections.sort(list);
            return true;
        }
        return false;
    }

    /**
     * List 降序处理
     * @param list 集合
     * @param <T>  泛型
     * @return {@code true} success, {@code false} fail
     */
    public static <T> boolean sortDesc(final List<T> list) {
        return sort(list, Collections.reverseOrder());
    }
}