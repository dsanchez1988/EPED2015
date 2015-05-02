package structs.tree;

/**
 * Created by Daniel Sánchez Paz on 28/4/15.
 */
public class TreeLeaf extends TreeNode{
    private int value;

    public TreeLeaf() {
        this.value = 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isLeaf(){return true;}
}
