package structs.list;

import Ifaces.IteratorIF;
import Ifaces.ListIF;

/**
 * Created by Daniel Sánchez Paz on 29/3/15.
 */
public class ListIterator<T> implements IteratorIF<T> {

    private ListIF<T> handler;
    private ListIF<T> restart;

    /**
     * Constructor para structs.list.ListIterator
     * @param handler el manejador de listas.
     */
    public ListIterator(ListIF<T> handler) {
        this.handler = handler;
        this.restart = handler;
    }

    /**
     * Devuelve el siguiente elemento de la iteración
     *
     * @return elemento siguiente
     */
    @Override
    public T getNext() {
        T next = handler.getFirst();
        handler = handler.getTail();
        return next;
    }

    /**
     * Indica si hay más elementos en la iteración
     *
     * @return verdadero si existe más elementos
     */
    @Override
    public boolean hasNext() {
        return !handler.isEmpty();
    }

    /**
     * Restablece el iterador para volver al inicio
     */
    @Override
    public void reset() {
        handler=restart;
    }
}
