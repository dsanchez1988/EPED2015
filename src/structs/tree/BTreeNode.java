package structs.tree;

import java.util.HashMap;

/**
 * Created by Daniel on 15/04/2015.
 */
public class BTreeNode {
    private Integer freq;
    private Character aChar;

    public BTreeNode() {
        this.freq = 1;
        this.aChar = null;
    }

    public BTreeNode(Character aChar) {
        this.freq = null;
        this.aChar = aChar;
    }

    public Integer getFreq() {
        return freq;
    }

    public void increaseFreq() {
        if (this.aChar == null)
            this.freq++;
    }

    public void decreaseFreq() {
        if (this.aChar == null && this.freq > 1) {
            this.freq--;
        }
    }

    public Character getChar() {
        return aChar;
    }


    public boolean equals(Character str) {
        if (aChar == null || !aChar.equals(str))
            return false;
        return true;
    }

    public boolean isFinalNode(){
        return this.freq!=null;
    }
}

