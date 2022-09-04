public class Board {


    private Board left;
    private Board right;
    private Board up;
    private Board down;
    private PipeLine pipe;

    private String pos;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Board getLeft() {
        return left;
    }

    public void setLeft(Board left) {
        this.left = left;
    }

    public Board getRight() {
        return right;
    }

    public void setRight(Board right) {
        this.right = right;
    }

    public Board getUp() {
        return up;
    }

    public void setUp(Board up) {
        this.up = up;
    }

    public Board getDown() {
        return down;
    }

    public void setDown(Board down) {
        this.down = down;
    }

    public PipeLine getPipe() {
        return pipe;
    }

    public void setPipe(PipeLine pipe) {
        this.pipe = pipe;
    }
}
