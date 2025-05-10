package ro.upt.graph.mst.impl;

import ro.upt.graph.entity.WeightedGraph;
import ro.upt.graph.entity.impl.WeightedGraphEdge;
import ro.upt.graph.entity.DisjointSet;
import ro.upt.graph.entity.impl.DisjointSetUpForest;
import ro.upt.graph.mst.MST;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST implements MST {

    @Override
    public Iterable<WeightedGraphEdge> mstEdgeList(WeightedGraph G) {

        int V = G.getNrVertices();
        int E = G.getNrEdges();

        Queue<WeightedGraphEdge> mst = new LinkedList<>();  // edges in MST

        // create array of edges and sort by weight
        WeightedGraphEdge[] edges = new WeightedGraphEdge[E];
        int t = 0;
        for (WeightedGraphEdge e : G.allEdges()) {
            edges[t++] = e;
        }

        Arrays.sort(edges);

        // start Kruskal algorithm
        DisjointSet uf = new DisjointSetUpForest(V); // init Union-Find structure
        for (int i = 0; i < E && mst.size() < V - 1; i++) {
            WeightedGraphEdge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            // v-w does not create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // merge v and w components
                mst.add(e);     // add edge e to mst
            }
        }
        return mst;
    }

}
