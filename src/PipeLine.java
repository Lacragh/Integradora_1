public class PipeLine {

    private String pipe;
    private PipeLine next;
    private PipeLine previous;

    public PipeLine getNext() {
        return next;
    }

    public void setNext(PipeLine next) {
        this.next = next;
    }

    public PipeLine getPrevious() {
        return previous;
    }

    public void setPrevious(PipeLine previous) {
        this.previous = previous;
    }

    public PipeLine(String pipe) {
        this.pipe = pipe;

    }

    public String getPipe() {

        return pipe;
    }

    public void setPipe(String pipe) {

        this.pipe = pipe;
    }


}
