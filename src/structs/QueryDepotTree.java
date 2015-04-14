package structs;

import Ifaces.ListIF;
import Ifaces.QueryDepot;

/**
 * Created by Daniel on 12/04/2015.
 */
public class QueryDepotTree implements QueryDepot {
    /**
     * Devuelve el n�mero de consultas diferentes (sin contar repeticiones)
     * que hay almacenadas en el dep�sito
     *
     * @return el n�mero de consultas diferentes almacenadas
     */
    @Override
    public int numQueries() {
        return 0;
    }

    /**
     * Consulta la frecuencia de una consulta en el dep�sito
     *
     * @param q el texto de la consulta
     * @return la frecuencia de la consulta. Si no est� devolver� 0
     */
    @Override
    public int getFreqQuery(String q) {
        return 0;
    }

    /**
     * Dado un prefijo de consulta, devuelve una lista ordenada por
     * frecuencias de mayor a menor, de todas las consultas almacenadas
     * en el dep�sito que comiencen por dicho prefijo
     *
     * @param prefix el prefijo
     * @return la lista de consultas ordenadas por frecuencias y orden lexicogr�fico en caso de coincidencia de frecuencia
     */
    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        return null;
    }

    /**
     * Incrementa en uno la frecuencia de una consulta en el dep�sito
     * Si la consulta no exist�a en la estructura, la deber� a�adir
     *
     * @param q el texto de la consulta
     */
    @Override
    public void incFreqQuery(String q) {

    }

    /**
     * Decrementa en uno la frecuencia de una consulta en el dep�sito
     * Si la frecuencia decrementrada resultase ser 0, deber� eliminar
     * la informaci�n referente a la consulta del dep�sito
     * (Precondici�n) la consulta debe estat ya en el dep�sito
     *
     * @param q el texto de la consulta
     */
    @Override
    public void decFreqQuery(String q) {

    }
}
