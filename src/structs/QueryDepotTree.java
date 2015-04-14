package structs;

import Ifaces.ListIF;
import Ifaces.QueryDepot;

/**
 * Created by Daniel on 12/04/2015.
 */
public class QueryDepotTree implements QueryDepot {
    /**
     * Devuelve el número de consultas diferentes (sin contar repeticiones)
     * que hay almacenadas en el depósito
     *
     * @return el número de consultas diferentes almacenadas
     */
    @Override
    public int numQueries() {
        return 0;
    }

    /**
     * Consulta la frecuencia de una consulta en el depósito
     *
     * @param q el texto de la consulta
     * @return la frecuencia de la consulta. Si no está devolverá 0
     */
    @Override
    public int getFreqQuery(String q) {
        return 0;
    }

    /**
     * Dado un prefijo de consulta, devuelve una lista ordenada por
     * frecuencias de mayor a menor, de todas las consultas almacenadas
     * en el depósito que comiencen por dicho prefijo
     *
     * @param prefix el prefijo
     * @return la lista de consultas ordenadas por frecuencias y orden lexicográfico en caso de coincidencia de frecuencia
     */
    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        return null;
    }

    /**
     * Incrementa en uno la frecuencia de una consulta en el depósito
     * Si la consulta no existía en la estructura, la deberá añadir
     *
     * @param q el texto de la consulta
     */
    @Override
    public void incFreqQuery(String q) {

    }

    /**
     * Decrementa en uno la frecuencia de una consulta en el depósito
     * Si la frecuencia decrementrada resultase ser 0, deberá eliminar
     * la información referente a la consulta del depósito
     * (Precondición) la consulta debe estat ya en el depósito
     *
     * @param q el texto de la consulta
     */
    @Override
    public void decFreqQuery(String q) {

    }
}
