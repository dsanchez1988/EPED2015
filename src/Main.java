import Ifaces.ListIF;
import Ifaces.QueryDepot;
import structs.Query;
import structs.QueryDepotList;
import structs.QueryDepotTree;
import structs.list.ListIterator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final double REPETICIONES=1000;

    public static void main(String[] args) {
        if (args.length !=3 || (!args[0].equals("L") && !args[0].equals("T")) ) {
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

            System.out.println("Consultas almacenadas: " + qd.numQueries() + ".");

            //Recorremos todas las operaciones haciendo las mediciones pertinentes
            for(int i=0; i<ops.size();i++){
                Operations op=ops.get(i);
                long tInit;
                long tEnd;
                double duracion=0;
                int freq=0;

                if(op.getOperationType()==Operations.FREQUENCY){
                    tInit=System.currentTimeMillis();
                    for (int j = 0; j < REPETICIONES; j++) {
                        freq=qd.getFreqQuery(op.getText());
                    }
                    tEnd=System.currentTimeMillis();
                    duracion=((double)tEnd-(double)tInit)/REPETICIONES;
                    System.out.println("La frecuencia de  \""+op.getText()+"\" es: "+freq+".");
                }
                else{
                    ListIF<Query> lista=null;
                    tInit=System.currentTimeMillis();
                    for (int j = 0; j < REPETICIONES; j++) {
                        lista = qd.listOfQueries(op.getText());
                    }
                    tEnd=System.currentTimeMillis();
                    duracion=((double)tEnd-(double)tInit)/REPETICIONES;
                    System.out.println("Las sugerencias para el prefijo \"" + op.getText() + "\" son:");
                    ListIterator<Query> lIt= (ListIterator<Query>)lista.getIterator();
                    while(lIt.hasNext()) {
                        Query q = lIt.getNext();
                        System.out.println("\t\"" + q.getText() + "\" con frecuencia " + q.getFreq());
                    }
                }

                System.out.println("-Tiempo: "+duracion);
            }
        }


    }
}
