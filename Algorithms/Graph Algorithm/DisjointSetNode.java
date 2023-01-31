public class DisjointSetNode<T> {

    private DisjointSetNode<T> parent;
    private T data;
    private int rank;

    public DisjointSetNode(T data) {
        this.parent = this;
        this.data = data;
        this.rank = 0;
    }

    public DisjointSetNode<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public int getRank() {
        return rank;
    }

    public void setParent(DisjointSetNode<T> parent) {
        this.parent = parent;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Rank: " + rank + ", Parent: "
            + parent.getData();
    }
}