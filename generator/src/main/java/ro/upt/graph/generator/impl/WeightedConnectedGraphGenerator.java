package ro.upt.graph.generator.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.entity.Node;
import ro.upt.graph.entity.WeightedEdge;
import ro.upt.graph.util.Randomizer;

public class WeightedConnectedGraphGenerator extends ConnectedGraphGenerator {
    private static final int MIN_WEIGHT = 1;
    private static final int MAX_WEIGHT = 1000;


    @Override
    protected Edge createEdge(Node left, Node right) {
        return new WeightedEdge(left.getValue(), right.getValue(), Randomizer.getRandomBetween(MIN_WEIGHT, MAX_WEIGHT));
    }
}
