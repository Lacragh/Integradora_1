public class Board {


    private Board left;
    private Board right;

    private PipeLine pipe;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String pos;

    public Board(PipeLine pipe, String pos,String image) {
        this.pipe = pipe;
        this.pos = pos;
        this.image = image;
    }



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


    public PipeLine getPipe() {
        return pipe;
    }

    public void setPipe(PipeLine pipe) {
        this.pipe = pipe;
    }
}
