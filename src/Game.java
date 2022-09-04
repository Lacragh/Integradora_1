public class Game {

    private Board head;
    private Board tail;


    public Game() {
        addLast();
    }


    public void addLast() {
        Random rd = new Random();
        int row = rd.nextInt(7);
        int column = rd.nextInt(4);
        int row1 = rd.nextInt(7);
        int column1 = rd.nextInt(8)+5;
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

        print(head, 0);

    }

    private void print(Board current, int i) {
        //Caso Base

        if (current == null) {
            return;
        }

        if (current == tail) {
            System.out.println(current.getImage());
            return;
        }

        if (i == 8) {
            i = 0;
            System.out.print("\n");
        }
        System.out.print(current.getImage() + " ");
        print(current.getRight(), i + 1);
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









