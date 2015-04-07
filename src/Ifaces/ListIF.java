package Ifaces;

/**
 * Created by Daniel Sánchez Paz on 23/3/15.
 */
public interface ListIF<T> {

    /**
     * Devuelve la cabecera de una lista
     * @return
     */
    public T getFirst();

    /**
     * Obtiene los elementos de la lista excepto la cabecera
     * @return
     */
    public ListIF<T> getTail();

    /**
     * Inserta un nuevo elemento a la lista
     * @param element El elemento a añadir
     * @return la lista incluyendo el nuevo elemento
     */
    public ListIF<T> insert (T element);

    /**
     * Devuelve verdadero si la lista está vacia
     * @return True si la lista esta vacia
     */
    public boolean isEmpty();

    /**
     * Devuelve verdadero si la lista está llena
     * @return verdadero si la lista está llena
     */
    public boolean isFull();

    /**
     * Obtiene el número de lementos de la lista
     * @return el número de elementos de la lista
     */
    public int getLength();

    /**
     * Devuelve un iterador para la lista
     * @return Un iterador para la lista
     */
    public IteratorIF<T> getIterator();

    /**
     * Comprueba si el elemento "element" existe en la lista
     * @param element el elemento a comprobar si existe en la lista
     * @return cierto si existe
     */
    public boolean contains(T element);

    /**
     * Ordena los elementos de la lista
     * @param comparator el comparador de los elementos
     * @return la lista ordenada
     */
    public ListIF<T> sort (ComparatorIF<T> comparator);
}
