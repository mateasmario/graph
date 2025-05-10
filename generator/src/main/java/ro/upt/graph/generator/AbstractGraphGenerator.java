package ro.upt.graph.generator;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.generator.GraphGenerator;

import java.util.List;

public abstract class AbstractGraphGenerator implements GraphGenerator {
    @Override
    public List<Edge> generate(int numberOfNodes) {
        if (numberOfNodes == 1) {
            return List.of();
        }

        return generateForMultipleNodes(numberOfNodes);
    }

    public abstract List<Edge> generateForMultipleNodes(int numberOfNodes);
}
