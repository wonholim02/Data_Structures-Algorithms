import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {

    private Map<T, DisjointSetNode<T>> disjointSet;

    public DisjointSet() {
        disjointSet = new HashMap<>();
    }

    public T find(Vertex<T> data) {
        return find(data.getData());
    }

    public T find(T data) {
        if (disjointSet.containsKey(data)) {
            return find(disjointSet.get(data)).getData();
        } else {
            disjointSet.put(data, new DisjointSetNode<>(data));
            return find(disjointSet.get(data)).getData();
        }
    }

    private DisjointSetNode<T> find(DisjointSetNode<T> curr) {
        DisjointSetNode<T> parent = curr.getParent();
        if (parent == curr) {
            return curr;
        } else {
            parent = find(curr.getParent());
            curr.setParent(parent);
            return parent;
        }
    }

    public void union(T first, T second) {
        union(disjointSet.get(first), disjointSet.get(second));
    }

    private void union(DisjointSetNode<T> first, DisjointSetNode<T> second) {
        DisjointSetNode<T> firstParent = find(first);
        DisjointSetNode<T> secondParent = find(second);
        if (firstParent != secondParent) {
            if (firstParent.getRank() < secondParent.getRank()) {
                firstParent.setParent(secondParent);
            } else {
                secondParent.setParent(firstParent);
                if (firstParent.getRank() == secondParent.getRank()) {
                    firstParent.setRank(firstParent.getRank() + 1);
                }
            }
        }
    }
}