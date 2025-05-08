package ro.upt.graph.writer;

import ro.upt.graph.entity.Edge;

import java.io.IOException;
import java.util.List;

public interface GraphWriter {
    void writeGraph(int noVertices, List<Edge> edgeList) throws IOException;
}
