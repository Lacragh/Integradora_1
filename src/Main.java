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



}
