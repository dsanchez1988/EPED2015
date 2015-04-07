package Classes;

import Ifaces.QueryDepot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final double REPETICIONES=10;

    public static void main(String[] args) {
        if (args.length !=3 || !args[0].equals("L") || !args[0].equals("T") ) {
            System.out.println("Número de parametros erroneos\n" +
                    "El programa debe ejecutarse usando el comando " +
                    "\"java -jar eped2015.jar <estructura> <fichero_consultas> <fichero_operaciones>\"\n\n" +
                    "Estructuras\n===========\n" +
                    "L  Usa una implementación mediante Lista\n" +
                    "T  Usa una implementación mediante árbol");
        }else{
            FileReader queryFile=null;
            FileReader operationFile=null;

            try {
                queryFile = new FileReader(args[1]);
                operationFile = new FileReader(args[2]);
            } catch (FileNotFoundException e) {
                e.getLocalizedMessage();
                System.exit(1);
            }
            BufferedReader queryReader = new BufferedReader(queryFile);
            QueryDepot qd=null;
            if (args[0].equals("L")){
                qd = new QueryDepotList();
            }else if(args[0].equals("T")){
                qd = new QueryDepotTree();
            }else {
                System.exit(1);
            }

            //Leemos y almacenamos las consultas en la estructura
            String queryLine;
            try {
                while ((queryLine = queryReader.readLine()) != null){
                    qd.incFreqQuery(queryLine);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            //Cargamos las operaciones en un ArrayList para facilitar el acceso al leer
            BufferedReader operationReader=new BufferedReader(operationFile);
            String operationLine;
            ArrayList<Operations> ops = new ArrayList <Operations>();
            try {
                while ((operationLine=operationReader.readLine())!=null){
                    Operations op=new Operations(operationLine);
                    ops.add(op);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Recorremos todas las operaciones haciendo las mediciones pertinentes
            for(int i=0; i<ops.size();i++){
                Operations op=ops.get(i);
                long tInit;
                long tEnd;
                tInit=System.currentTimeMillis();
                if(op.getOperationType()==Operations.FREQUENCY)
                    for (int j = 0; j < REPETICIONES; j++) {
                        int freq=qd.getFreqQuery(op.getText());
                        System.out.println("Frecuencia de la consulta \""+op.getText()+"\": "+freq);
                    }
                else{
                    for (int j = 0; j < REPETICIONES; j++) {
                        System.out.println("Las sugerencias para el prefijo \""+op.getText()+"\"son:");
                    }
                }
                tEnd=System.currentTimeMillis();
                double duracion=((double)tEnd-(double)tInit)/REPETICIONES;
                System.out.println("COSTE DE OPERACION= "+duracion);
            }
        }


    }
}
