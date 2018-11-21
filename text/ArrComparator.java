package text;
import java.util.Comparator;

/**
 * @author 肖柯
 */
class ArrComparator implements Comparator<Object> {//这个接口是为了比较大小
    int column = 2;

    int sortOrder = -1; // 这个类就是为了比较大小

    public ArrComparator() {
    }
    public int compare(Object a, Object b) {
        if (a instanceof int[]) {
            return sortOrder * (((int[]) a)[column] - ((int[]) b)[column]);
        }
        throw new IllegalArgumentException("param a,b must int[].");
    }
}