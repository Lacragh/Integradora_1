package Model;

public class ABB {

    private Player root;


    public void print() {
        print(root, 1);
    }

    private void print(Player current, int n) {

        if (current == null) {
            return;
        }

        print(current.getRight(), n);
        System.out.println("|----------------------------------------------|");
        System.out.println("|Jugador         Puntos                        |");
        System.out.println("|----------------------------------------------|");
        System.out.println("|" + current.getName() + ": " + current.getScore() + "                       |");
        print(current.getLeft(), n);
    }


    public void insert(Player n) {
        insert(n, root);
    }

    private void insert(Player n, Player current) {

        if (root == null) {
            this.root = n;
        } else {

            if (n.getScore() > current.getScore()) {
                if (current.getRight() != null) {
                    insert(n, current.getRight());
                } else {
                    current.setRight(n);
                }
            }

            if (n.getScore() < current.getScore()) {
                if (current.getLeft() != null) {
                    insert(n, current.getLeft());
                } else {
                    current.setLeft(n);
                }

            }
        }


    }


}
