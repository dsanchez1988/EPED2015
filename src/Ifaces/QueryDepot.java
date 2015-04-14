package Ifaces;

import structs.Query;



/**
 * Created by dansanp on 18/3/15.
 */
public interface QueryDepot {
    /**
     * Devuelve el número de consultas diferentes (sin contar repeticiones)
     * que hay almacenadas en el depósito
     * @return el número de consultas diferentes almacenadas
     */
    public int numQueries();

    /**
     * Consulta la frecuencia de una consulta en el depósito
     * @return la frecuencia de la consulta. Si no está devolverá 0
     * @param q el texto de la consulta
     */

    public int getFreqQuery(String q);

    /**
     * Dado un prefijo de consulta, devuelve una lista ordenada por
     * frecuencias de mayor a menor, de todas las consultas almacenadas
     * en el depósito que comiencen por dicho prefijo
     * @return la lista de consultas ordenadas por frecuencias y orden lexicográfico en caso de coincidencia de frecuencia
     * @param prefix el prefijo
     */
    public ListIF<Query> listOfQueries(String prefix);

    /**
     * Incrementa en uno la frecuencia de una consulta en el depósito
     * Si la consulta no existía en la estructura, la deberá añadir
     * @param q el texto de la consulta
     */
    public void incFreqQuery(String q);

    /**
     * Decrementa en uno la frecuencia de una consulta en el depósito
     * Si la frecuencia decrementrada resultase ser 0, deberá eliminar
     * la información referente a la consulta del depósito
     * (Precondición) la consulta debe estat ya en el depósito
     * @param q el texto de la consulta
     */
    public void decFreqQuery (String q);

}
