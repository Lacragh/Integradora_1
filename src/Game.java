import java.util.Random;

public class Game {

    private Board head;
    private Board tail;
    private int h = 0;

    public Game() {

        addLast();
    }


    public void addLast() {
        Random rd = new Random();
        int row = rd.nextInt(7);
        int column = rd.nextInt(4);
        // Para la D
        int row1 = rd.nextInt(7);
        int column1 = rd.nextInt(5,8);

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
        setRandomFont_Drain(head,row,column,row1,column1,0);
    }

    public void search(String goal, PipeLine pipe) {

        search(head, goal, pipe);
    }

    private void search(Board current, String goal, PipeLine pipe) {

        if (current.getPos().equals(goal)) {
            current.setPipe(pipe);
            current.setImage(pipe.getPipe());
        } else {
            search(current.getRight(), goal, pipe);
        }
    }

    public void print() {

        print(head, 0,0);

    }

    private void print(Board current, int i,int r) {
        //Caso Base


        if (current == null) {
            return;
        }

        if (current == tail) {
            System.out.println(current.getImage()+" 🤍");
            return;
        }

        if (i == 8 ) {
            if (r == 0){
                System.out.print("🧡\n🤍");
                r = 1;
                i = 0;
            }else{
                System.out.print("🤍\n🧡");
                r = 0;
                i = 0;
            }


        }

        System.out.print(current.getImage() + " ");
        print(current.getRight(), i + 1,r);
    }







    public Board searchPipe(int fila, int columna,Board current,int i){


        if (current == null){
            return null;
        }
                if (((fila)+","+(columna)).equals(current.getPos())){
                    i = 0;
                    System.out.println("BIEN");
                    return current;
                }

                if (i == 1){
                   return searchPipe(fila,columna,current.getRight(),i);
                }else{
                    return null;
                }


            }



    public void simulate(){

        simulate(head,head,0,0);
    }

    public String comprobar(Board temp){
        int fila = Character.getNumericValue(temp.getPos().charAt(0));
        int columna = Character.getNumericValue(temp.getPos().charAt(2));
        // Ganar
        if(searchPipe(fila-1,columna,head,1).getImage().equals("D")){
            System.out.println("Ganaste");
        }else
        if(searchPipe(fila+1,columna,head,1).getImage().equals("D")){
            System.out.println("Ganaste");
        }else
        if(searchPipe(fila,columna-1,head,1).getImage().equals("D")){
            System.out.println("Ganaste");
        }else
        if(searchPipe(fila,columna+1,head,1).getImage().equals("D")){
            System.out.println("Ganaste");
        }else{
           return "Perdiste";
        }

        return null;


    }


    private void simulate(Board current,Board temp,int fila,int column){

        // TUBERIAS DOBLEMENTE ENLAZADA
        if(current.getImage().equals("F")){
             fila =  Character.getNumericValue(temp.getPos().charAt(0));
             column = Character.getNumericValue(temp.getPos().charAt(2));
             comprobar(temp);
             // Validacion arriba
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("||") && searchPipe(fila-1,column,head,1).getRepeat() == 0){ // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    System.out.println(searchPipe(fila-1,column,head,1).getImage());
                    temp = searchPipe(fila-1,column,head,1);
                    temp.setRepeat(1);

                }
            }

            // Validacion abajo
            if(searchPipe(fila+1,column,head,1) != null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("||") && searchPipe(fila+1,column,head,1).getRepeat() == 0){
                    temp = searchPipe(fila+1,column,head,1);
                    temp.setRepeat(1);
                }
            }

            // Validacion derecha
            if(searchPipe(fila,column+1,head,1) != null){
                if(searchPipe(fila,column+1,head,1).getImage().equals("=") && searchPipe(fila,column+1,head,1).getRepeat() == 0){
                    temp = searchPipe(fila,column+1,head,1);
                    temp.setRepeat(1);
                }
            }

            //Validacion izquierda
            if(searchPipe(fila,column-1,head,1) != null){
                if(searchPipe(fila,column-1,head,1).getImage().equals("=") && searchPipe(fila,column-1,head,1).getRepeat() == 0){
                    temp = searchPipe(fila,column-1,head,1);
                    temp.setRepeat(1);
                }
            }


            // BOLITAS
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("o") && searchPipe(fila-1,column,head,1).getRepeat() == 0){ // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    temp = searchPipe(fila-1,column,head,1);
                    temp.setRepeat(1);
                }
            }

            // Validacion abajo
            if (searchPipe(fila+1,column,head,1) != null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("o") && searchPipe(fila+1,column,head,1).getRepeat() == 0){
                    temp = searchPipe(fila+1,column,head,1);
                    temp.setRepeat(1);
                }
            }

            // Validacion derecha
            if(searchPipe(fila,column+1,head,1).getImage().equals("o") && searchPipe(fila,column+1,head,1).getRepeat() == 0){
                temp = searchPipe(fila,column+1,head,1);
                temp.setRepeat(1);
            }
            //Validacion izquierda
            if(searchPipe(fila,column-1,head,1).getImage().equals("o") && searchPipe(fila,column-1,head,1).getRepeat() == 0){
                temp = searchPipe(fila,column-1,head,1);
                temp.setRepeat(1);
            }
            //////////////////////////////////// AHORA BIEN NECESITAMOS UNA FORMA DE PARAR EL ALGORITMO PARA CUANDO NO HAY INGUNA TUBERIA ALREDEDOR EN ESE MOMENTO PIERDE




            ///////////////////////////RECURSION

            System.out.println(temp.getImage());
            simulate(current,temp,fila,column);


        }else{
            simulate(current.getRight(),temp.getRight(),fila,column);
        }



    }



    public void setRandomFont_Drain(Board current,int row,int column,int row1,int column1,int c) {

        if (c == 2){
            return;
        }else{
            if (current.getPos().equals((row) + "," +(column))) {
                current.setPos("F");
                c++;
            }

            if (current.getPos().equals((row) + "," +(column))) {
                current.setPos("D");
                c++;
            }
            setRandomFont_Drain(current.getRight(),row,column,row1,column1,c);
        }

    }

}









