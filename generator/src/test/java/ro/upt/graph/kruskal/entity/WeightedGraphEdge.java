package ro.upt.graph.kruskal.entity;

/**
 * WeightedEdge represents an edge in a weighted undirected graph
 */
public class WeightedGraphEdge implements Comparable<WeightedGraphEdge>{
    private int either;
    private int other;
    private int w;


    public WeightedGraphEdge(int either, int other, int w) {
        this.either = either;
        this.other = other;
        this.w = w;
    }

    public String toString() {
        return String.format("%d-%d %d", either, other, w);
    }

    public int either() {
        return either;
    }

    public int other(int v) {
        if (v == either)
            return other;
        else
            return either;
    }

    public int weight(){
        return w;
    }


    @Override
    public int compareTo(WeightedGraphEdge that) {
        return Integer.compare(this.w, that.w);
    }

}