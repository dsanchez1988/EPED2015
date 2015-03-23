public class Main {

    public static void main(String[] args) {
        if (args.length !=3 ){
            System.out.println("Número de parametros erroneos\n" +
                    "El programa debe ejecutarse usando el comando \"java -jar eped2015.jar <estructura> <fichero_consultas> <fichero_operaciones>\"\n\n" +
                    "Estructuras\n" +
                    "===========\n" +
                    "L  Usa una implementación mediante Lista\n" +
                    "T  Usa una implementación mediante árbol");
        }
    }
}
