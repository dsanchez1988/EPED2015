/**
 * Created by Daniel Sánchez Paz on 23/3/15.
 */
public interface ComparatorIF<T> {
    public static int LESS=-1;
    public static int EQUAL=0;
    public static int GREATER=1;

    /**
     * Compara dos elementos para indicar si el primero es menor, igual o mayor que el segundo elemento
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return el orden de los elementos
     */
    public int compare(T e1,T e2);

    /**
     * Indica si un elemento es menor que otro
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si el e1 es menor que e2
     */
    public boolean isLess(T e1, T e2);

    /**
     * Indica si un elemento es igual a otro
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si e1 es igual a e2
     */
    public boolean isEqual(T e1, T e2);

    /**
     * Indica si un elemento es mayot que otro
     * @param e1 el primer elemento
     * @param e2 el segundo elemento
     * @return true si e1 es mayor que e2
     */
    public boolean isGreater(T e1, T e2);
}
