package Classes;

import java.io.IOException;

/**
 * Created by Daniel Sánchez Paz on 24/3/15.
 */
public class Operations {

    public static final int FREQUENCY=0;
    public static final int SUGGESTION=1;
    private int operationType;
    private String text;

    public Operations(String text) throws IOException{
        String op=text.substring(0,1);
        if(op.equals("F"))
            this.setOperationType(Operations.FREQUENCY);
        else if(op.equals("S"))
            this.setOperationType(Operations.SUGGESTION);
        else
            throw new IOException("Bad File Formatting");

        this.setText(text.substring(2));
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) throws IllegalArgumentException{
        if(operationType==FREQUENCY || operationType==SUGGESTION)
            this.operationType = operationType;
        else throw new IllegalArgumentException ();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
