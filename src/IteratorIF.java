/**
 * Created by Daniel Sánchez Paz on 23/3/15.
 */
public interface IteratorIF<T> {

    /**
     * Devuelve el siguiente elemento de la iteración
     * @return elemento siguiente
     */
    public T getNext();

    /**
     * Indica si hay más elementos en la iteración
     * @return verdadero si existe más elementos
     */
    public boolean hasNext();

    /**
     * Restablece el iterador para volver al inicio
     */
    public void reset();
}
