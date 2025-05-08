package ro.upt.graph.entity;

public class WeightedEdge extends Edge {
    protected int weight;

    public WeightedEdge(int left, int right, int weight) {
        super(left, right);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return super.toString() + " " + weight;
    }
}
