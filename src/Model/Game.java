package Model;

import Model.Board;

import java.util.Random;

public class Game {
    Random rd = new Random();
    private Board head;
    private Board tail;
    private ABB scoreboard;
    private int pipeCount = 0;

    public int getPipeCount() {
        return pipeCount;
    }

    public void setPipeCount(int pipeCount) {
        this.pipeCount = pipeCount;
    }

    private PipeLine headPipe;


    public Game() {
        addLast();

    }


    public void addLast() {
        Random rd = new Random();
        int row = rd.nextInt(7);
        int column = rd.nextInt(4);
        // Para la D
        int row1 = rd.nextInt(7);
        int column1 = rd.nextInt(5, 8);

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                Board node = new Board(null, (i) + "," + (j), "X");
                if (head == null) {
                    tail = node;
                    head = node;
                } else {
                    tail.setRight(node);
                    tail = node;

                }
            }
        }
        setRandomFont_Drain(head, row, column, row1, column1, 0);

    }

    public void search(String goal, PipeLine pipe) {

        search(head, goal, pipe);
    }

    //Metodo para buscar la posicion y colocar la  tuberia.
    private void search(Board current, String goal, PipeLine pipe) {
        if (current != null) {
            if (current.getPos().equals(goal)) {
                if (current.getImage().equals("F") || current.getImage().equals("D")) {
                    System.out.println("No se puede colocar una tubería en esta posición!");
                } else {
                    current.setPipe(pipe);
                    current.setImage(pipe.getPipe());
                    pipeCount++;
                }

            } else {
                search(current.getRight(), goal, pipe);
            }
        }

    }

    public void print() {
        print(head, 0, 0);

    }

    private void print(Board current, int i, int r) {
        //Caso Base


        if (current == null) {
            return;
        }

        if (current == tail) {
            System.out.println(current.getImage() + "  🤍");
            return;
        }

        if (i == 8) {
            if (r == 0) {
                System.out.print("🧡\n🤍 ");
                r = 1;
                i = 0;
            } else {
                System.out.print("🤍\n🧡 ");
                r = 0;
                i = 0;
            }


        }

        System.out.print(current.getImage() + "  ");
        print(current.getRight(), i + 1, r);
    }

    public Board resetRepeat(Board current){
        if (current == null){
            return null;
        }
        current.setRepeat(0);
        return resetRepeat(current.getRight());
    }

    public Board searchPipe(int fila, int columna, Board current, int i) {


        if (current == null) {
            return null;
        }
        if (((fila) + "," + (columna)).equals(current.getPos())) {
            i = 0;

            return current;
        }

        if (i == 1) {
            return searchPipe(fila, columna, current.getRight(), i);
        } else {
            return null;
        }


    }


    public boolean simulate() {
        simulate(head, head, 0, 0);
        boolean stop = true;
        if (verificatePipeLine(headPipe)) {
            System.out.println("🧠¡GANASTE!🧠");
            headPipe = null;
            return stop;
        } else {
            System.out.println("Perdiste🥱Zzzz");
            resetRepeat(head);
            printPipeline(headPipe);
            headPipe = null;
            return !stop;
        }
    }


    public void addLastPipe(PipeLine input) {
        if (this.headPipe == null) {
            this.headPipe = input;
            this.headPipe.setNext(input);
            this.headPipe.setPrevious(input);
        } else {
            PipeLine tail = this.headPipe.getPrevious();
            tail.setNext(input);
            input.setNext(this.headPipe);
            this.headPipe.setPrevious(input);
            input.setPrevious(tail);
        }

    }


    private Board simulate(Board current, Board temp, int fila, int column) {
        boolean stop = true;
        // TUBERIAS DOBLEMENTE ENLAZADA
        if (current.getImage().equals("F")) {
            fila = Character.getNumericValue(temp.getPos().charAt(0));
            column = Character.getNumericValue(temp.getPos().charAt(2));


            //VALIDACION PARA GANAR
            if (searchPipe(fila - 1, column, head, 1) != null) {
                if (searchPipe(fila - 1, column, head, 1).getImage().equals("D")) {
                    PipeLine pipe = new PipeLine(searchPipe(fila - 1, column, head, 1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return current;
                }
            }

            if (searchPipe(fila + 1, column, head, 1) != null) {
                if (searchPipe(fila + 1, column, head, 1).getImage().equals("D")) {
                    PipeLine pipe = new PipeLine(searchPipe(fila + 1, column, head, 1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return current;
                }
            }

            if (searchPipe(fila, column - 1, head, 1) != null) {
                if (searchPipe(fila, column - 1, head, 1).getImage().equals("D")) {
                    PipeLine pipe = new PipeLine(searchPipe(fila, column - 1, head, 1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return current;
                }
            }

            if (searchPipe(fila, column + 1, head, 1) != null) {
                if (searchPipe(fila, column + 1, head, 1).getImage().equals("D")) {
                    PipeLine pipe = new PipeLine(searchPipe(fila, column + 1, head, 1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return current;
                }
            }

            // Validacion arriba
            if (searchPipe(fila - 1, column, head, 1) != null) {
                if (searchPipe(fila - 1, column, head, 1).getImage().equals("||") && searchPipe(fila - 1, column, head, 1).getRepeat() == 0) { // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    temp = searchPipe(fila - 1, column, head, 1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            // Validacion abajo
            if (searchPipe(fila + 1, column, head, 1) != null) {
                if (searchPipe(fila + 1, column, head, 1).getImage().equals("||") && searchPipe(fila + 1, column, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila + 1, column, head, 1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            // Validacion derecha
            if (searchPipe(fila, column + 1, head, 1) != null) {
                if (searchPipe(fila, column + 1, head, 1).getImage().equals("=") && searchPipe(fila, column + 1, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila, column + 1, head, 1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            //Validacion izquierda
            if (searchPipe(fila, column - 1, head, 1) != null) {
                if (searchPipe(fila, column - 1, head, 1).getImage().equals("=") && searchPipe(fila, column - 1, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila, column - 1, head, 1);
                    System.out.println("CUCARACHA");
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }


            // BOLITAS
            if (searchPipe(fila - 1, column, head, 1) != null) {
                if (searchPipe(fila - 1, column, head, 1).getImage().equals("o") && searchPipe(fila - 1, column, head, 1).getRepeat() == 0) { // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    temp = searchPipe(fila - 1, column, head, 1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            // Validacion abajo
            if (searchPipe(fila + 1, column, head, 1) != null) {
                if (searchPipe(fila + 1, column, head, 1).getImage().equals("o") && searchPipe(fila + 1, column, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila + 1, column, head, 1);
                    System.out.println("CUCARACHA");
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            // Validacion derecha
            if (searchPipe(fila, column + 1, head, 1) != null) {
                if (searchPipe(fila, column + 1, head, 1).getImage().equals("o") && searchPipe(fila, column + 1, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila, column + 1, head, 1);
                    System.out.println("CUCARACHA");
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }

            //Validacion izquierda
            if (searchPipe(fila, column - 1, head, 1) != null) {
                if (searchPipe(fila, column - 1, head, 1).getImage().equals("o") && searchPipe(fila, column - 1, head, 1).getRepeat() == 0) {
                    temp = searchPipe(fila, column - 1, head, 1);
                    System.out.println("CUCARACHA");
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current, temp, fila, column);
                }
            }


            if (searchPipe(fila - 1, column, head, 1) != null) {
                if (searchPipe(fila - 1, column, head, 1).getImage().equals("X")) {
                    stop = false;
                }
            }

            // Validacion abajo
            if (searchPipe(fila + 1, column, head, 1) != null) {
                if (searchPipe(fila + 1, column, head, 1).getImage().equals("X")) {
                    stop = false;
                }
            }

            // Validacion derecha
            if (searchPipe(fila, column + 1, head, 1) != null) {
                if (searchPipe(fila, column + 1, head, 1).getImage().equals("X")) {
                    stop = false;
                }
            }

            //Validacion izquierda
            if (searchPipe(fila, column - 1, head, 1) != null) {
                if (searchPipe(fila, column - 1, head, 1).getImage().equals("X")) {
                    stop = false;
                }
            }


            //RECURSION

            if (!stop) {
                return null;
            }


        } else {
            simulate(current.getRight(), temp.getRight(), fila, column);
        }
        return null;
    }

    private boolean verificateD(PipeLine current) {
        if (current == null) {
            return false;
        }
        if (headPipe != null){
            if (current == headPipe.getPrevious()) {
                if (current.getPipe().equals("D")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return verificateD(current.getNext());
            }
        }

        return false;
    }


    private boolean verificatePipeLine(PipeLine current) {

        // validacion si gano

        if (verificateD(headPipe)) {

            if (current.getPipe().equals("=")) {
                if (current.getNext().getPipe().equals("D")) {
                    System.out.println(current.getNext().getPipe());
                    return true;
                }
            }
            if (current.getPipe().equals("||")) {
                if (current.getNext().getPipe().equals("D")) {
                    System.out.println(current.getNext().getPipe());
                    return true;
                }
            }

            // Para tuberias de arriba o abajo
            if (current.getPipe().equals("||")) {
                if (current.getNext().getPipe().equals("||")) {
                    return verificatePipeLine(current.getNext());
                }

                if (current.getNext().getPipe().equals("o")) {
                    return verificatePipeLine(current.getNext());
                }


            }

            // Para tuberias de izquierda o derecha
            if (current.getPipe().equals("=")) {

                if (current.getNext().getPipe().equals("=")) {
                    return verificatePipeLine(current.getNext());
                }

                if (current.getNext().getPipe().equals("o")) {
                    return verificatePipeLine(current.getNext());
                }

            }
            // Para tuberias de bolitas
            if (current.getPipe().equals("o")) {

                if (current.getNext().getPipe().equals("||")) {
                    return verificatePipeLine(current.getNext());
                }

                if (current.getNext().getPipe().equals("=")) {
                    return verificatePipeLine(current.getNext());
                }

            }

        } else {
            return false;
        }
        return false;
    }


    public void setRandomFont_Drain(Board current, int row, int column, int row1, int column1, int c) {

        if (c == 2) {
            return;
        } else {

            if (current != null) {

                if (current.getPos().equals((row) + "," + (column))) {
                    current.setImage("F");
                    current.setPipe(new PipeLine("F"));
                    c++;
                }

                if (current.getPos().equals((row1) + "," + (column1))) {
                    current.setImage("D");
                    current.setPipe(new PipeLine("D"));
                    c++;
                }
                setRandomFont_Drain(current.getRight(), row, column, row1, column1, c);
            }


        }

    }

    public void printScoreBoard() {
        scoreboard.print();
    }

    public void printHEADPIPE() {

    }

    public PipeLine printPipeline(PipeLine current) {
        if (headPipe != null){
            if (current == headPipe.getPrevious()) {
                System.out.println(current.getPipe());
                return null;
            }else{
                System.out.println(current.getPipe());
                return printPipeline(current.getNext());
            }
        }

       return null;
    }

}









