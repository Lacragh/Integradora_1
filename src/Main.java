import java.util.Scanner;


public class Main {

    public static Game controller;
    public static Scanner reader;
    public static void main(String[] args) {
        controller = new Game();
        reader = new Scanner(System.in);
        int exit = 0;
        System.out.println("Bienvenido al mejor juego del mundo!");
        while (exit == 0) {

            System.out.println("\n**********🤯PIPELINE🤯**********");
            System.out.println("* 1. Nueva partida🥶             *");
            System.out.println("* 2. Ver puntaje🕹️               *");
            System.out.println("* 3. Salir🥱                     *");
            System.out.println("**********************************\n");
            int choice = reader.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Ingresa tu nombre de usuario!🥰🧐");
                    String nickname = reader.nextLine();
                    nickname = reader.nextLine();

                    //metodo para impresion del tablero
                    board();
                    //Empieza juego
                    startedGame();
                    break;
                case 2:
                case 3:
                    System.out.println("Gracias por jugar el mejor juego del mundo");
                    System.out.println("Inviertan para parte 2!🦕💲");
                    exit++;
                    break;
            }

        }


    }

    public static void board(){
        System.out.println("🤍🧡🤍🧡🤍🧡🤍🧡🤍");
        System.out.print("🧡");controller.print();
        System.out.println("🧡🤍🧡🤍🧡🤍🧡🤍🧡");

    }

    public static void startedGame() {

        int exit2 = 0;

        while (exit2 == 0) {
            System.out.println("\n**********🤯PIPELINE🤯************");
            System.out.println("* 1. Poner tubería🤩             *");
            System.out.println("* 2. Simular🧐                   *");
            System.out.println("* 3. Salir🥱                     *");
            System.out.println("**********************************\n");
            int choice2 = reader.nextInt();

            switch (choice2) {
                case 1:
                    board();
                    System.out.println("Ingrese la posicion en la que desea colocar la tubería\n");
                    System.out.println("Coloque fila y columna, así -> (0,7)\n");
                    String row_column = reader.next();

                    System.out.println("Ingrese el tipo de tubería que desea colocar");
                    System.out.println("Existen 3 tipos de tuberia:\n1. = (tubería horizontal)\n2. || (Tubería vertical)\n3. o " +
                            "(Cambio de 90 grados\nDigite la tubería que desee (=,||,o)");
                    String pipeLine = reader.next();

                    while (!pipeLine.equals("o") && !pipeLine.equals("=") && !pipeLine.equals("||")) {
                        System.out.println("ERROR, por favor ingrese una tubería valida!\n");
                        pipeLine = reader.next();
                    }

                    //metodo para asignar la tuberia a la posicion elegida
                    controller.search(row_column, new PipeLine(pipeLine));
                    controller.print();
                    break;
                case 2:

                    controller.simulate();
                    break;
                case 3:
                    System.out.println("Gracias por jugar el mejor juego del mundo");
                    System.out.println("Inviertan para parte 2!🦕💲");
                    exit2++;
                    break;
            }
        }
    }


}
