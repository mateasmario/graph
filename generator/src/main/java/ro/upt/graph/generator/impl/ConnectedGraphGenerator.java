package ro.upt.graph.generator.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public abstract class ConnectedGraphGenerator implements GraphGenerator {
    private static final Logger logger = Logger.getLogger(ConnectedGraphGenerator.class.getName());

    @Override
    public List<Edge> generate(int numberOfNodes) {
        logger.info("Generating graph with " + numberOfNodes + " nodes...");
        List<Edge> spanningTree = generateSpanningTree(numberOfNodes);
        fillWithRemainingEdges(spanningTree, numberOfNodes);

        return spanningTree;
    }

    private List<Edge> generateSpanningTree(int numberOfNodes) {
        logger.info("Generating spanning tree...");
        List<Edge> spanningTree = new ArrayList<>();

        List<Integer> unvisitedNodes = fillNodes(numberOfNodes);
        List<Integer> visitedNodes = new ArrayList<>();

        visitedNodes.add(0);
        unvisitedNodes.remove(0);

        while (unvisitedNodes.size() > 0) {
            int leftNodeIndex = Randomizer.getRandomBetween(0, unvisitedNodes.size());
            int rightNodeIndex = Randomizer.getRandomBetween(0, visitedNodes.size());

            if (!unvisitedNodes.get(leftNodeIndex).equals(visitedNodes.get(rightNodeIndex))) {
                spanningTree.add(createEdge(unvisitedNodes.get(leftNodeIndex), visitedNodes.get(rightNodeIndex)));
                visitedNodes.add(unvisitedNodes.get(leftNodeIndex));
                unvisitedNodes.remove(leftNodeIndex);
            }
        }
        logger.info("Spanning tree generated.");

        return spanningTree;
    }

    private List<Integer> fillNodes(int numberOfNodes) {
        List<Integer> nodes = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(i);
        }

        return nodes;
    }

    private void fillWithRemainingEdges(List<Edge> spanningTree, int numberOfNodes) {
        int edgeMultiplier = numberOfNodes >= 5 ? Randomizer.getRandomBetween(1, (numberOfNodes - 1) / 2) : 1;
        int totalNoEdges = edgeMultiplier * numberOfNodes;

        logger.info("Edge multiplier has been set to " + edgeMultiplier + ".");
        logger.info("Adding edges until graph reaches " + totalNoEdges + " edges...");

        List<Edge> totalEdgesWithoutSpanningTree = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = i + 1; j < numberOfNodes; j++) {
                Edge edgeToAdd = createEdge(i, j);
                if (edgeIsValid(edgeToAdd, totalEdgesWithoutSpanningTree)
                        && !spanningTree.contains(edgeToAdd)) {
                    totalEdgesWithoutSpanningTree.add(edgeToAdd);
                }
            }
        }

        while (spanningTree.size() < totalNoEdges) {
            int edgeIndex = Randomizer.getRandomBetween(0, totalEdgesWithoutSpanningTree.size());

            spanningTree.add(totalEdgesWithoutSpanningTree.get(edgeIndex));
            totalEdgesWithoutSpanningTree.remove(edgeIndex);
        }

        logger.info("Edges generated.");
    }

    private boolean edgeIsValid(Edge edge, List<Edge> spanningTree) {
        if (edge.getLeft() != edge.getRight()) {
            return !spanningTree.contains(edge);
        }

        return false;
    }

    protected abstract Edge createEdge(int left, int right);
}
