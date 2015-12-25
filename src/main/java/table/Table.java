package table;

import java.util.ArrayList;

/**
 * Created by root on 23.12.15.
 */
public class Table<T extends TableElement> extends ArrayList<T> {

    @Override
    public int indexOf(Object o) {
        int index = -1;
        int count = 0;
        for (TableElement element : this) {
            if (element.getName().equals((String) o)) {
                index = count;
                return index;
            }
            count++;
        }
        return index;
    }
}
