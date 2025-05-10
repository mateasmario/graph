package ro.upt.graph.generator.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.entity.Node;

public class UnweightedConnectedGraphGenerator extends ConnectedGraphGenerator {
    @Override
    protected Edge createEdge(Node left, Node right) {
        return new Edge(left.getValue(), right.getValue());
    }
}
