package Classes;

/**
 * Created by dansanp on 18/3/15.
 */
public class Query {
    private String text;
    private int freq;
    public Query(String text){

    }

    public void setFreq(int f){
        this.freq=f;
    }

    public String getText(){
        return text;
    }

    public int getFreq(){
        return freq;
    }

}
