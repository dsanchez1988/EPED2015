package structs;

import Ifaces.ComparatorIF;

/**
 * Created by Daniel on 27/04/2015.
 */
public class ComparatorQueryAlphabet implements ComparatorIF<Query>{
    @Override
    public int compare(Query e1, Query e2) {
        if (e1.getText().compareTo(e2.getText()) == 0)
            return this.EQUAL;
        else if (e1.getText().compareTo(e2.getText()) > 0)
            return this.LESS;
        else return this.GREATER;

    }

    @Override
    public boolean isLess(Query e1, Query e2) {
        return this.compare(e1,e2) == this.LESS;
    }

    @Override
    public boolean isEqual(Query e1, Query e2) {
        return this.compare(e1,e2) == this.EQUAL;
    }

    @Override
    public boolean isGreater(Query e1, Query e2) {
        return this.compare(e1,e2) == this.GREATER;
    }
}
