package Classes;

import Ifaces.ComparatorIF;

/**
 * Created by dansanp on 18/3/15.
 */
public class Query{
    private String text;
    private int freq;
    public Query(String text){
        setText(text);
        setFreq(1);
    }

    public void setFreq(int f){
        this.freq=f;
    }

    public void setText(String s){
        this.text=s;
    }

    public String getText(){
        return text;
    }

    public int getFreq(){
        return freq;
    }

}
