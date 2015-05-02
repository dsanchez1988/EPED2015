package structs.tree;

/**
 * Created by Daniel Sánchez Paz on 28/4/15.
 */
public class TreeInternalNode extends TreeNode {
    private char value;

    public TreeInternalNode(char c){
        this.value = c;
    }

    public char getValue(){
        return value;
    }

    public void setValue(char c){
        this.value = c;
    }

    public boolean isLeaf(){return super.isLeaf();}
}
