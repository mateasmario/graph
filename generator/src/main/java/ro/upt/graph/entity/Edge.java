package ro.upt.graph.entity;

public class Edge {
    protected int left;
    protected int right;

    public Edge(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;

        // We are discussing undirected graphs
        return (left == edge.left && right == edge.right)
                || (left == edge.right && right == edge.left);
    }

    @Override
    public String toString() {
        return left + ", " + right;
    }
}
