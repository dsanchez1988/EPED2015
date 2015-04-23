package structs;

import Ifaces.ListIF;
import Ifaces.QueryDepot;
import structs.list.ListDynamic;
import structs.list.ListIterator;

/**
 * Created by dansanp on 18/3/15.
 */
public class QueryDepotList implements QueryDepot {

    private ListDynamic<Query> queryList;

    public QueryDepotList(){
        this.queryList = new ListDynamic<Query>();
    }

    /**
     * Devuelve el primer nodo de la lista
     * @return el primer nodo de la lista
     */
    public ListDynamic<Query> getQueryList(){
        return queryList;
    }
    /**
     * Devuelve el número de consultas diferentes (sin contar repeticiones)
     * que hay almacenadas en el depósito
     *
     * @return el número de consultas diferentes almacenadas
     */
    @Override
    public int numQueries() {
        //Obtengo la longitud usando el método de la clase DynamicList<T>
        return queryList.getLength();
    }

    /**
     * Consulta la frecuencia de una consulta en el depósito
     *
     * @param q el texto de la consulta
     * @return la frecuencia de la consulta. Si no está devolverá 0
     */
    @Override
    public int getFreqQuery(String q) {
        if(queryList.isEmpty())
            return 0;
        ListIterator<Query> iter = (ListIterator<Query>) queryList.getIterator();
        do{
            Query tmp = iter.getNext();
            if(tmp.getText().equals(q))
                return tmp.getFreq();
        }while(iter.hasNext());
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
        ListIF<Query> unorderedList = new ListDynamic<Query>();
        ListIterator<Query> queryIT = (ListIterator <Query>) queryList.getIterator();

        //Recorremos toda la lista de consultas
        do{
            Query temp = queryIT.getNext();

            //Comprobamos si la consulta empieza por el prefijo dado y en caso afirmativo lo añadimos a la lista que hemos creado
            if(temp.getText().startsWith(prefix))
                unorderedList.insert(temp);
        }while(queryIT.hasNext());//Hasta el final

        //Devolvemos la lista ordenada ussando el comparador que hemos creado para este fin
        return unorderedList.sort(new ComparatorQuery());
    }

    /**
     * Incrementa en uno la frecuencia de una consulta en el depósito
     * Si la consulta no existía en la estructura, la deberá añadir
     *
     * @param q el texto de la consulta
     */
    @Override
    public void incFreqQuery(String q) {

        //Si la lista esta vacia insertamos directamente el elemento
        if(queryList.isEmpty())
            queryList.insert(new Query(q));
        else {
        //En caso contrario buscamos el elemento y se ha encontrado aumentamos su frecuencia
            boolean found = false;
            ListIterator<Query> lIterator = (ListIterator<Query>) queryList.getIterator();
            do{
                Query tmp = lIterator.getNext();
                if(tmp.getText().equals(q)){
                    found = true;
                    tmp.setFreq(tmp.getFreq()+1);
                }
            }while (!found && lIterator.hasNext());

            //En caso de que no se haya encontrado lo añadimos a la lista
            if(!found)
                queryList.insert(new Query(q));
        }
    }



    /**
     * Decrementa en uno la frecuencia de una consulta en el depósito
     * Si la frecuencia decrementrada resultase ser 0, deberá eliminar
     * la información referente a la consulta del depósito
     * (Precondición) la consulta debe estar ya en el depósito
     *
     * @param q el texto de la consulta
     */
    @Override
    public void decFreqQuery(String q) {
        //REVISE: Revisar funcion decFreqQuery
        boolean found=false;

        ListDynamic<Query> actual = queryList;
        ListDynamic<Query> prev = null;
        Query tmp = null;

        do{
            if( (tmp=actual.getFirst()).getText().equals(q)){
                if(tmp.getFreq() != 1)
                    tmp.setFreq(tmp.getFreq() - 1);
                else{
                    if(prev==null)
                        queryList = (ListDynamic<Query>) actual.getTail();
                    else{
                        prev.setTail(actual.getTail());
                        actual.setTail(null);
                    }
                }
                found = true;
            }
            prev = actual;
            actual = (ListDynamic<Query>) actual.getTail();
        }while(!found && !actual.isEmpty());
    }
}