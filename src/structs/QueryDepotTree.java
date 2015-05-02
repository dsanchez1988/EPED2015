package structs;

import Ifaces.IteratorIF;
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
        return 0;
    }


    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        return null;
    }

    @Override
    public void incFreqQuery(String q) {

    }



    //Devuelve el LeafNode de la lista de hijos dada
    private TreeLeaf getLeaf(ListIF<TreeIF<TreeNode>> childList){
        if (childList.isEmpty())
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

    private void internalIncFreqQuery(String q,TreeDynamic<TreeNode> tree){
    }
    @Override
    public void decFreqQuery(String q) {

    }
}
