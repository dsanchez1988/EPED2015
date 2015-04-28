package structs;

import Ifaces.ComparatorIF;

/**
 * Created by Daniel on 09/04/2015.
 */
public class ComparatorQuery implements ComparatorIF<Query> {

    //REVISE: Revisar comparador y ver si hace falta cambiar el orden de las operaciones
    /**
     * Compara dos elementos para indicar si el primero es menor, igual o mayor que el segundo elemento
     *
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return el orden de los elementos
     */
    @Override
    public int compare(Query e1, Query e2) {
        if (e1.getFreq() < e2.getFreq() ||
                (e1.getFreq() == e2.getFreq() && e1.getText().compareTo(e2.getText()) > 0))
            return this.LESS;
        else if (e1.getFreq() > e2.getFreq() ||
                (e1.getFreq() == e2.getFreq() && e1.getText().compareTo(e2.getText()) < 0))
            return this.GREATER;
        return this.EQUAL;
    }

    /**
     * Indica si un elemento es menor que otro
     *
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si el e1 es menor que e2
     */
    @Override
    public boolean isLess(Query e1, Query e2) {
        return this.compare(e1,e2) == this.LESS;
    }

    /**
     * Indica si un elemento es igual a otro
     *
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si e1 es igual a e2
     */
    @Override
    public boolean isEqual(Query e1, Query e2) {
        //Devuelve siempre falso, en una lista no puede haber dos querys iguales
        return this.compare(e1,e2) == this.EQUAL;
    }

    /**
     * Indica si un elemento es mayot que otro
     *
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si e1 es mayor que e2
     */
    @Override
    public boolean isGreater(Query e1, Query e2) {
        return this.compare(e1,e2) == this.GREATER;
    }
}
