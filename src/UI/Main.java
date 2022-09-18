package UI;

import Model.*;

import java.util.Scanner;

/*
Luis Fernando Pinillos Sanchez | A00381323
Victor Manuel Garzon Meneses | A00381962
 */
public class Main {
    public static Long start, end;

    public static String nickname;
    public static Game controller;
    public static Scanner reader;

    public static ABB scoreboard;

    public static void main(String[] args) {
        scoreboard = new ABB();
        reader = new Scanner(System.in);
        int exit = 0;
        System.out.println("Bienvenido al mejor juego del mundo!");
        while (exit == 0) {
            System.out.println("\n**********🤯PIPELINE🤯************");
            System.out.println("* 1. Nueva partida🥶             *");
            System.out.println("* 2. Ver puntaje🕹️               *");
            System.out.println("* 3. Salir🥱                     *");
            System.out.println("**********************************\n");
            int choice = reader.nextInt();

            switch (choice) {
                case 1:
                    controller = new Game();
                    start = System.nanoTime();
                    System.out.println("Ingresa tu nombre de usuario!🥰🧐");
                    nickname = reader.nextLine();
                    nickname = reader.nextLine();

                    //metodo para impresion del tablero
                    board();
                    //Empieza juego
                    startedGame();
                    break;
                case 2:
                    scoreboard.print();
                    break;
                case 3:
                    System.out.println("Gracias por jugar el mejor juego del mundo");
                    System.out.println("Inviertan para parte 2!🦕💲");
                    exit++;
                    break;
                default:
                    System.out.println("Por favor ingresar una opción valida!");
            }

        }


    }

    public static void board() {
        System.out.println("🤍🧡🤍🧡🤍🧡🤍🧡🤍🧡🤍🧡🤍");
        System.out.print("🧡 ");
        controller.print();
        System.out.println("🧡🤍🧡🤍🧡🤍🧡🤍🧡🤍🧡🤍🧡");

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
                    int row = Character.getNumericValue(row_column.charAt(0));
                    int column = Character.getNumericValue(row_column.charAt(2));
                    if (row <= 7 && row >= 0 &&
                            column <= 7 && column >= 0) {
                        System.out.println("Ingrese el tipo de tubería que desea colocar");
                        System.out.println("Existen 3 tipos de tuberia:\n = (tubería horizontal)\n || (Tubería vertical)\n o " +
                                "(Cambio de 90 grados\nDigite la tubería que desee (=,||,o)");
                        String pipeLine = reader.next();

                        while (!pipeLine.equals("o") && !pipeLine.equals("=") && !pipeLine.equals("||")) {
                            System.out.println("ERROR, por favor ingrese una tubería valida!\n");
                            pipeLine = reader.next();
                        }

                        //metodo para asignar la tuberia a la posicion elegida
                        controller.search(row_column, new PipeLine(pipeLine));
                        board();
                    } else {
                        System.out.println("\nPor favor ingrese una coordenada valida!");
                    }
                    break;
                case 2:
                    if (controller.simulate()) {
                        exit2++;
                        end = System.nanoTime();

                        long totalNanoTime = end - start;

                        double seconds = (double) totalNanoTime / 1000000000;

                        int points = (int) ((controller.getPipeCount() * 100) - ((60 - seconds) * 10));
                        scoreboard.insert(new Player(nickname, points));
                    } else {
                        System.out.println("Mala colocación de tuberias, sigue intentando!");
                    }
                    break;
                case 3:
                    System.out.println("\nGracias por jugar el mejor juego del mundo!");
                    System.out.println("Inviertan para parte 2!🦕💲");
                    exit2++;
                    end = System.nanoTime();

                    long totalNanoTime = end - start;
                    System.out.println(totalNanoTime);
                    double seconds = (double) totalNanoTime / 1000000000;
                    System.out.println(seconds);
                    System.out.println(controller.getPipeCount());
                    int points = (int) ((controller.getPipeCount() * 100) - ((60 - seconds) * 10));
                    scoreboard.insert(new Player(nickname, points));
                    break;
                default:
                    System.out.println("Por favor ingrese una opción valida!");

            }
        }
    }


}
