package structs;

import Ifaces.ListIF;
import Ifaces.QueryDepot;
import Ifaces.TreeIF;
import structs.list.ListIterator;
import structs.tree.*;

/**
 * Created by Daniel on 12/04/2015.
 */
public class QueryDepotTree implements QueryDepot {

    private TreeDynamic<TreeNode> queryTree;

    public QueryDepotTree() {
        queryTree = new TreeDynamic<TreeNode>();
    }

    @Override
    public int numQueries() {
        int i=0;
        TreeIterator<TreeNode> it = (TreeIterator<TreeNode>) queryTree.getIterator(TreeIF.PREORDER);
        while(it.hasNext()){
            if(it.getNext().isLeaf())
                i++;
        }
        return i;
    }


    @Override
    public int getFreqQuery(String q) {
        //Hacemos la lista empezando por el nodo raiz
        return int_getFreq(q,queryTree);
    }

    public int int_getFreq(String q, TreeDynamic<TreeNode> tree){
        //Si el String está vacio comprobamos si contiene un nodo hoja,
        // en caso afirmativo retornamos el valor de frecuencia,
        // en caso contrario devolvemos 0
        if (q.length() == 0){
            TreeLeaf leaf;
            if((leaf = getLeaf(tree.getChildren())) != null)
                return leaf.getValue();
            else
                return 0;
        }
        //En caso de que la longitud del String sea mayor y no haya más hijos retornamos 0
        else if(tree.getChildren().isEmpty())
            return 0;
        //En caso contrario buscamos en los hijos por el siguiente caracter
        else{
            TreeDynamic<TreeNode> child = getChild(tree.getChildren(),q.charAt(0));
            //Si no existe un hijo con ese caracter devolvemos 0
            if(child == null)
                return 0;

            //En caso contrario seguimos la busqueda con un caracter menos por el hijo obtenido
            else{
                return int_getFreq(q.substring(1),child);
            }
        }

    }


    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        //TODO:Implementar
        return null;
    }



    @Override
    public void incFreqQuery(String q) {
        incFrec(q,queryTree);
    }

    private void incFrec(String query, TreeDynamic<TreeNode> node){
        //Si la String de consulta esta vacia y existe como hijo un nodo hoja le aumentamos el valor en uno, si el nodo
        // hoja no existe lo creamos
        if(query.isEmpty() ) {
            TreeLeaf leaf = getLeaf(node.getChildren());
            if(leaf !=null) {
                leaf.setValue(leaf.getValue() + 1);
                return;
            }
            else{
                node.addChild(createTree(""));
                return;
            }
        }
        //Si no esta vacia intentamos recuperar el hijo con el siguiente caracter de la cadena, si existe hacemos
        //recursividad sobre esta funcion, en caso contrario creamos un subarbol con lo que queda de String y
        //lo añadimos a los hijos del nodo actual.
        else{
            TreeDynamic<TreeNode> child = getChild(node.getChildren(),query.charAt(0));
            if (child == null) {
                node.addChild(createTree(query.substring(1)));
                return;
            }
            else{
                incFrec(query.substring(1),child);
                return;
            }
        }
    }



    //Devuelve el LeafNode de la lista de hijos dada
    private TreeLeaf getLeaf(ListIF<TreeIF<TreeNode>> childList){
        if (childList == null || childList.isEmpty())
            return null;
        else{
            ListIterator<TreeIF<TreeNode>> childIterator = (ListIterator <TreeIF<TreeNode>>)childList.getIterator();
            while(childIterator.hasNext()){
                TreeDynamic<TreeNode> t = (TreeDynamic) childIterator.getNext();
                if(t.getRoot().isLeaf())
                    return (TreeLeaf) t.getRoot();
            }
            return null;
        }
    }

    //Crea un arbol a partír del String pasado
    private TreeDynamic<TreeNode> createTree (String query){
        if (query.length() == 0){
            return new TreeDynamic<TreeNode>(new TreeLeaf());
        }
        else{
            TreeDynamic<TreeNode> a = new TreeDynamic<TreeNode>(new TreeInternalNode(query.charAt(0)));
            a.addChild(createTree(query.substring(1)));
            return a;
        }
    }

    //Obtiene el hijo que concuerde con el caracter pasado
    private TreeDynamic<TreeNode> getChild(ListIF<TreeIF<TreeNode>> childList,char c){
        ListIterator<TreeIF<TreeNode>> childIterator = (ListIterator <TreeIF<TreeNode>>) childList.getIterator();
        while(childIterator.hasNext()){
            TreeDynamic<TreeNode> child = (TreeDynamic<TreeNode>) childIterator.getNext();
            if(!child.isLeaf() && ((TreeInternalNode)child.getRoot()).getValue() == c)
                return child;
        }
        return null;
    }
    @Override
    public void decFreqQuery(String q) {
        //TODO:Implementar
    }
}
