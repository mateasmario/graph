package ro.upt.graph.generator.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.entity.Node;
import ro.upt.graph.generator.AbstractGraphGenerator;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class ConnectedGraphGenerator extends AbstractGraphGenerator {
    private static final Logger logger = Logger.getLogger(ConnectedGraphGenerator.class.getName());

    @Override
    public List<Edge> generateForMultipleNodes(int numberOfNodes) {
        logger.info("Generating graph with " + numberOfNodes + " nodes...");
        List<Edge> spanningTree = generateSpanningTree(numberOfNodes);
        fillWithRemainingEdges(spanningTree, numberOfNodes);

        return spanningTree;
    }

    private List<Edge> generateSpanningTree(int numberOfNodes) {
        logger.info("Generating spanning tree...");
        List<Edge> spanningTree = new ArrayList<>();

        List<Node> unvisitedNodes = Node.generateListOfNodes(numberOfNodes);
        List<Node> visitedNodes = new ArrayList<>();

        visitedNodes.add(new Node(0));
        unvisitedNodes.remove(new Node(0));

        while (!isEmpty(unvisitedNodes)) {
            Node leftNode = getRandomNodeFrom(unvisitedNodes);
            Node rightNode = getRandomNodeFrom(visitedNodes);

            if (!leftNode.equals(rightNode)) {
                addEdgeToSpanningTree(spanningTree, leftNode, rightNode);
                visitedNodes.add(leftNode);
                unvisitedNodes.remove(leftNode);
            }
        }
        logger.info("Spanning tree generated.");

        return spanningTree;
    }

    private boolean isEmpty(List<Node> nodes) {
        return nodes.size() == 0;
    }

    private Node getRandomNodeFrom(List<Node> nodes) {
        return nodes.get(Randomizer.getRandomBetween(0, nodes.size()));
    }

    private void addEdgeToSpanningTree(List<Edge> spanningTree, Node leftNode, Node rightNode) {
        Edge edgeToAdd = createEdge(leftNode, rightNode);
        spanningTree.add(edgeToAdd);
    }

    private void fillWithRemainingEdges(List<Edge> spanningTree, int numberOfNodes) {
        int maxNumberOfEdges = generateMaxNumberOfEdges(numberOfNodes);

        logger.info("Adding edges until graph reaches " + maxNumberOfEdges + " edges...");

        List<Edge> totalEdgesWithoutSpanningTree = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = i + 1; j < numberOfNodes; j++) {
                Edge edgeToAdd = createEdge(new Node(i), new Node(j));
                if (edgeIsValid(edgeToAdd, totalEdgesWithoutSpanningTree)
                        && !spanningTree.contains(edgeToAdd)) {
                    totalEdgesWithoutSpanningTree.add(edgeToAdd);
                }
            }
        }

        while (spanningTree.size() < maxNumberOfEdges) {
            int edgeIndex = Randomizer.getRandomBetween(0, totalEdgesWithoutSpanningTree.size());

            spanningTree.add(totalEdgesWithoutSpanningTree.get(edgeIndex));
            totalEdgesWithoutSpanningTree.remove(edgeIndex);
        }

        logger.info("Edges generated.");
    }

    private int generateMaxNumberOfEdges(int numberOfNodes) {
        if (numberOfNodes <= 2) {
            return 1;
        }

        int edgeMultiplier = getRandomEdgeMultiplier(numberOfNodes);
        logger.info("Edge multiplier has been set to " + edgeMultiplier + ".");
        return edgeMultiplier * numberOfNodes;
    }

    private int getRandomEdgeMultiplier(int numberOfNodes) {
        return numberOfNodes >= 5 ? Randomizer.getRandomBetween(1, (numberOfNodes - 1) / 2) : 1;
    }

    private boolean edgeIsValid(Edge edge, List<Edge> spanningTree) {
        if (edge.getLeft() != edge.getRight()) {
            return !spanningTree.contains(edge);
        }

        return false;
    }

    protected abstract Edge createEdge(Node left, Node right);
}
