import java.nio.channels.Pipe;
import java.util.Random;

public class Game {

    private Board head;
    private Board tail;

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
        verificatePipeLine(headPipe);
    }




    public void addLastPipe(PipeLine input) {
        if (this.headPipe == null) {
            this.headPipe = input;
            this.headPipe .setNext(input);
            this.headPipe .setPrevious(input);
        } else {
            PipeLine tail = this.headPipe.getPrevious();
            tail.setNext(input);
            input.setNext(this.headPipe );
            this.headPipe.setPrevious(input);
            input.setPrevious(tail);
        }

    }


    private Board simulate(Board current,Board temp,int fila,int column){
        boolean stop = true;
        // TUBERIAS DOBLEMENTE ENLAZADA
        if(current.getImage().equals("F")){
             fila =  Character.getNumericValue(temp.getPos().charAt(0));
             column = Character.getNumericValue(temp.getPos().charAt(2));


             //VALIDACION PARA GANAR
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("D")){
                    System.out.println("Me agrega la D");
                    PipeLine pipe = new PipeLine(searchPipe(fila-1,column,head,1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return null;
                }
            }

            if(searchPipe(fila+1,column,head,1) !=null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("D")){
                    System.out.println("Me agrega la D");
                    PipeLine pipe = new PipeLine(searchPipe(fila+1,column,head,1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return null;
                }
            }

            if(searchPipe(fila,column-1,head,1) != null){
                if(searchPipe(fila,column-1,head,1).getImage().equals("D")){
                    System.out.println("Me agrega la D");
                    PipeLine pipe = new PipeLine(searchPipe(fila,column-1,head,1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return null;
                }
            }

            if(searchPipe(fila,column+1,head,1) != null){
                if(searchPipe(fila,column+1,head,1).getImage().equals("D")){
                    System.out.println("ME AGREGA LA D");
                    PipeLine pipe = new PipeLine(searchPipe(fila,column+1,head,1).getPipe().getPipe());
                    addLastPipe(pipe);
                    return null;
                }
            }

             // Validacion arriba
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("||") && searchPipe(fila-1,column,head,1).getRepeat() == 0){ // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    System.out.println("ME METE || HACIA ARRIBA A LA LISTA");
                    temp = searchPipe(fila-1,column,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            // Validacion abajo
            if(searchPipe(fila+1,column,head,1) != null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("||") && searchPipe(fila+1,column,head,1).getRepeat() == 0){
                    System.out.println("ME METE || HACIA ABAJO A LA LISTA");
                    temp = searchPipe(fila+1,column,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            // Validacion derecha
            if(searchPipe(fila,column+1,head,1) != null){
                if(searchPipe(fila,column+1,head,1).getImage().equals("=") && searchPipe(fila,column+1,head,1).getRepeat() == 0){
                    System.out.println("ME METE = HACIA DERECHA A LA LISTA");
                    temp = searchPipe(fila,column+1,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                   return  simulate(current,temp,fila,column);
                }
            }

            //Validacion izquierda
            if(searchPipe(fila,column-1,head,1) != null){
                if(searchPipe(fila,column-1,head,1).getImage().equals("=") && searchPipe(fila,column-1,head,1).getRepeat() == 0){
                    System.out.println("ME METE = HACIA IZQUIEDA A LA LISTA");
                    temp = searchPipe(fila,column-1,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }


            // BOLITAS
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("o") && searchPipe(fila-1,column,head,1).getRepeat() == 0){ // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    temp = searchPipe(fila-1,column,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            // Validacion abajo
            if (searchPipe(fila+1,column,head,1) != null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("o") && searchPipe(fila+1,column,head,1).getRepeat() == 0){
                    System.out.println("ME METE BOLITA HACIA ARRIBA A LA LISTA");
                    temp = searchPipe(fila+1,column,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            // Validacion derecha
            if(searchPipe(fila,column+1,head,1) !=null){
                if(searchPipe(fila,column+1,head,1).getImage().equals("o") && searchPipe(fila,column+1,head,1).getRepeat() == 0){
                    System.out.println("ME METE BOLITA HACIA derecha A LA LISTA");
                    temp = searchPipe(fila,column+1,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            //Validacion izquierda
            if (searchPipe(fila,column-1,head,1) != null){
                if(searchPipe(fila,column-1,head,1).getImage().equals("o") && searchPipe(fila,column-1,head,1).getRepeat() == 0){
                    System.out.println("ME METE BOLITA HACIA IZQUIERDA A LA LISTA");
                    temp = searchPipe(fila,column-1,head,1);
                    PipeLine pipe = new PipeLine(temp.getPipe().getPipe());
                    addLastPipe(pipe);
                    temp.setRepeat(1);
                    return simulate(current,temp,fila,column);
                }
            }

            //////////////////////////////////// AHORA BIEN NECESITAMOS UNA FORMA DE PARAR EL ALGORITMO PARA CUANDO NO HAY INGUNA TUBERIA ALREDEDOR EN ESE MOMENTO PIERDE
            if(searchPipe(fila-1,column,head,1) != null){
                if(searchPipe(fila-1,column,head,1).getImage().equals("X") ){ // Aqui seria agregar una Y YA SE REPITIO PARA EVITAR QUE SE REPITA LA MISMA TUBERIA Y EL TEMP PASE A LA OTRA
                    stop = false;
                }
            }

            // Validacion abajo
            if (searchPipe(fila+1,column,head,1) != null){
                if(searchPipe(fila+1,column,head,1).getImage().equals("X")){
                    stop = false;
                }
            }

            // Validacion derecha
            if(searchPipe(fila,column+1,head,1) !=null){
                if(searchPipe(fila,column+1,head,1).getImage().equals("X")){
                   stop = false;
                }
            }

            //Validacion izquierda
            if (searchPipe(fila,column-1,head,1) != null){
                if(searchPipe(fila,column-1,head,1).getImage().equals("X")){
                    stop = false;
                }
            }




            ///////////////////////////RECURSION

            if (stop == false){
                System.out.println("PERDISTE");
            }



        }else{
            simulate(current.getRight(),temp.getRight(),fila,column);
        }



        return null;
    }

    public PipeLine printPIPELINES(PipeLine current){
        if (current == headPipe.getPrevious()){
            System.out.println("LO ANTERIOR A LA CABEZA");
            System.out.println(current.getPipe());
            System.out.println("CABEZA");
            System.out.println(current.getNext().getPipe());
            return null;
        }
        System.out.println(current.getPipe());
        return printPIPELINES(current.getNext());
    }


    public PipeLine verificatePipeLine(PipeLine current){
        System.out.println("ENTRO AL METODO DE VERICACION");

        // validacion si gano
        printPIPELINES(headPipe);
        if(current.getPipe().equals("=") | current.getPipe().equals("o") | current.getPipe().equals("||")){
            if (current.getNext().getPipe().equals("D")){
                System.out.println("Ganaste");

            }
        }

        // Para tuberias de arriba o abajo
        if(current.getPipe().equals("||")){
            if(current.getNext().equals("||")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
                 return verificatePipeLine(current.getNext());
            }

            if(current.getNext().getPipe().equals("o")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
               return verificatePipeLine(current.getNext());
            }


        }

        // Para tuberias de izqueirda o derecha
        if(current.getPipe().equals("=")){

            if(current.getNext().getPipe().equals("=")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
                 return verificatePipeLine(current.getNext());
            }

            if(current.getNext().getPipe().equals("o")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
                 return verificatePipeLine(current.getNext());
            }

        }
        // Para tuberias de bolitas
        if(current.getPipe().equals("o")){

            if(current.getNext().getPipe().equals("||")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
                 return verificatePipeLine(current.getNext());
            }

            if(current.getNext().getPipe().equals("=")){
                System.out.println("ENTRO EN LA VERIFICAION BUCLE");
               return verificatePipeLine(current.getNext());
            }

        }

        if(current.getNext().getPipe().equals("D")){
            System.out.println("Ganaste");
        }else{
            System.out.println(current.getPipe());
            System.out.println(current.getNext().getPipe());
            System.out.println("Perdiste mero malito");
        }

        return null;
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









