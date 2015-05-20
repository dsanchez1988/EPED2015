package structs;

import Ifaces.ComparatorIF;
import Ifaces.ListIF;
import Ifaces.QueryDepot;
import Ifaces.TreeIF;
import structs.list.ListDynamic;
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
        //Obtenemos el treeNode correspondiente al prefijo
        TreeDynamic<TreeNode> node = getPrefixNode(prefix,queryTree);
        if (node == null) {
            return new ListDynamic<Query>();
        }

        return populateListOfQueries(prefix,node,new String(),new ListDynamic<Query>());
    }

    private TreeDynamic<TreeNode> getPrefixNode (String prefix, TreeDynamic<TreeNode> tree){
        if (tree == null || prefix.isEmpty())
            return tree;

        return getPrefixNode(prefix.substring(1), getChild(tree.getChildren(), prefix.charAt(0)));

    }

    private ListIF<Query> populateListOfQueries(String prefix, TreeDynamic<TreeNode> tree, String str, ListIF<Query> list){
        ListIF<TreeIF<TreeNode>> children = tree.getChildren();
        TreeLeaf l = getLeaf(children);
        if (l != null) {
            Query q = new Query(prefix + str);
            q.setFreq(l.getValue());
            list = sortInsert(q, (ListDynamic<Query>) list, new ComparatorQuery());
        }

        if (children.isEmpty())
            return list;

        ListIterator<TreeIF<TreeNode>> it = (ListIterator<TreeIF<TreeNode>>) children.getIterator();
        while (it.hasNext()){
            TreeIF<TreeNode> child = it.getNext();
            if(!child.getRoot().isLeaf()){
                TreeInternalNode n = (TreeInternalNode) child.getRoot();
                list = populateListOfQueries(prefix,
                        (TreeDynamic<TreeNode>) child,
                        str+n.getValue(),
                        list);
            }
        }
        return list;
    }


    //Función sortinsert de QueryDepotList para insertar los elementos a devolver por listOfQueries de manera ordenada
    private ListDynamic<Query> sortInsert(Query q,ListDynamic<Query> list, ComparatorIF<Query> comp){
        if(list.isEmpty())
            return (ListDynamic<Query>) list.insert(q);
        else if(comp.isGreater(q,list.getFirst()))
            return (ListDynamic<Query>)list.insert(q);

        return (ListDynamic<Query>) sortInsert(q, (ListDynamic<Query>) list.getTail(), comp).insert(list.getFirst());
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
                node.addChild(createTree(query));
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
        intDecFrecQuery(q,queryTree);
    }

    private boolean intDecFrecQuery (String q, TreeDynamic<TreeNode> tree){
        TreeDynamic<TreeNode> child = getChild(tree.getChildren(),q.charAt(0));
        //Si es un nodo hoja decrementamos la frecuencia y si esta es 1 le mandamos True para que elimine la hoja
        if (child == null) {
            TreeLeaf leaf= (TreeLeaf) tree.getRoot();
            if(leaf.getValue()==1)
                return true;
            else{
                leaf.setValue(leaf.getValue()-1);
                return false;
            }
        }

        //Si no es un nodo hoja comprobamos si tenemos que devolver el nodo anterior usando una llamada recursiva
        //En caso de que no haya hijos colgando los eliminamos y comprobamos si quedan hijos
        boolean removeNextNode = intDecFrecQuery(q.substring(1),child);
        if(removeNextNode){
            removeChildByChar(tree,q.charAt(0));
        }
        if(tree.getChildren().isEmpty())
            return true;
        else
            return false;
    }

    private void removeChildByChar(TreeDynamic<TreeNode> tree, char c){
        ListIterator<TreeIF<TreeNode>> childIterator = (ListIterator<TreeIF<TreeNode>>) tree.getChildren().getIterator();
        int index=0;
        boolean found=false;
        while(!found && childIterator.hasNext()){
            TreeIF<TreeNode> node = childIterator.getNext();
            if(!node.getRoot().isLeaf()){
                TreeInternalNode n = (TreeInternalNode) node.getRoot();
                if(n.getValue() == c)
                    found = true;
            }
            if(!found)
                index++;
        }
        tree.removeChild(index);
    }
}
