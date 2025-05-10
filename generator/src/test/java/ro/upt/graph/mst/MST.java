package ro.upt.graph.mst;

import ro.upt.graph.entity.WeightedGraph;
import ro.upt.graph.entity.impl.WeightedGraphEdge;

public interface MST {
    public Iterable<WeightedGraphEdge> mstEdgeList(WeightedGraph G) ;
}
