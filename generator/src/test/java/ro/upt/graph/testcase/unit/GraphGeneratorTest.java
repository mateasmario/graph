package ro.upt.graph.testcase.unit;

import org.junit.jupiter.api.*;
import ro.upt.graph.entity.Edge;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;

import java.util.List;

public class GraphGeneratorTest {
    private static final int NO_VERTICES_LARGE = 100;
    private static final int NO_VERTICES_SMALL = 2;
    private static final int NO_VERTICES_SINGLE = 1;
    private GraphGenerator graphGenerator;

    @BeforeEach
    public void setup() {
        graphGenerator = new WeightedConnectedGraphGenerator();
    }

    @Test
    public void testGraphLargeCorrectlyGenerated() {
        List<Edge> graph = graphGenerator.generate(NO_VERTICES_LARGE);
        Assertions.assertTrue(graph.size() >= NO_VERTICES_LARGE);
    }

    @Test
    public void testGraphSmallCorrectlyGenerated() {
        List<Edge> graph = graphGenerator.generate(NO_VERTICES_SMALL);
        Assertions.assertTrue(graph.size() >= NO_VERTICES_SMALL - 1);
    }

    @Test
    public void testSingleGraphCorrectlyGenerated() {
        List<Edge> graph = graphGenerator.generate(NO_VERTICES_SINGLE);
        Assertions.assertTrue(graph.isEmpty());
    }
}
