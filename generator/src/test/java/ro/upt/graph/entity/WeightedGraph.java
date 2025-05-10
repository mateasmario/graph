package ro.upt.graph.entity;



import ro.upt.graph.entity.impl.WeightedGraphEdge;

import java.io.IOException;


/**
 * Represents a simple undirected graph having a fixed number V of vertices (nodes).
 * Vertices are numbered either 0 other V-1
 * Edges can be added between existing vertices and have weights.
 */
public interface WeightedGraph {
    int getNrVertices();
    int getNrEdges();
    void addUndirectedEdge(int either, int other, int w);
    Iterable<Integer> nodesAdjacentTo(int node);
    Iterable<WeightedGraphEdge> edgesAdjacentTo(int node);
    Iterable<WeightedGraphEdge> allEdges();
    boolean hasVertex(int node);
    boolean hasEdge(int from, int to);
    void initFromFile(String file) throws IOException;
    void printGraph();
}