package structs;

import Ifaces.ListIF;
import Ifaces.QueryDepot;
import structs.tree.BTreeDynamic;
import structs.tree.BTreeIterator;
import structs.tree.BTreeNode;

/**
 * Created by Daniel on 12/04/2015.
 */
public class QueryDepotTree implements QueryDepot {

    private BTreeDynamic<BTreeNode> queryTree;

    public QueryDepotTree() {
        queryTree = new BTreeDynamic<BTreeNode>();
    }

    /**
     * Devuelve el n�mero de consultas diferentes (sin contar repeticiones)
     * que hay almacenadas en el dep�sito
     *
     * @return el n�mero de consultas diferentes almacenadas
     */
    @Override
    public int numQueries() {
        if (queryTree.isEmpty())
            return 0;
        BTreeIterator<BTreeNode> nodeIterator = (BTreeIterator<BTreeNode>) this.queryTree.getIterator(BTreeDynamic.INORDER);
        int finalNodeCount=0;
        do{
            BTreeNode tmpNode = nodeIterator.getNext();
            if(!tmpNode.isFinalNode())
                finalNodeCount++;
        }while(nodeIterator.hasNext());
        return finalNodeCount;
    }

    /**
     * Consulta la frecuencia de una consulta en el dep�sito
     *
     * @param q el texto de la consulta
     * @return la frecuencia de la consulta. Si no est� devolver� 0
     */
    @Override
    public int getFreqQuery(String q) {
        if(q.length()==0 ||
                q == null ||
                (queryTree.getLeftChild() == null && queryTree.getRightChild() == null))
            return 0;
        int freq=inFreqQuery(q,(BTreeDynamic)queryTree.getLeftChild());
        if (freq>0)
            return freq;
        else
            return inFreqQuery(q,(BTreeDynamic)queryTree.getRightChild());
    }

    private int inFreqQuery(String q,BTreeDynamic<BTreeNode> child){
        if (q.isEmpty()){

        }
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
        char[] stringChars = q.toCharArray();
    }

    private void internalIncFreqQuery(String q,BTreeDynamic<BTreeNode> tree){
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