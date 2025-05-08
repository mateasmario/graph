package ro.upt.graph.generator;

import ro.upt.graph.entity.Edge;

import java.util.List;

public interface GraphGenerator {
    List<Edge> generate(int numberOfNodes);
}
