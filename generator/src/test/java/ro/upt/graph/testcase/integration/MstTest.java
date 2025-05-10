package ro.upt.graph.testcase.integration;

import org.junit.jupiter.api.*;
import ro.upt.graph.entity.WeightedGraph;
import ro.upt.graph.entity.impl.WeightedGraphAdjList;
import ro.upt.graph.entity.impl.WeightedGraphEdge;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;
import ro.upt.graph.mst.MST;
import ro.upt.graph.mst.impl.KruskalMST;
import ro.upt.graph.writer.GraphWriter;
import ro.upt.graph.writer.impl.FileGraphWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MstTest {
    private static final String OUTPUT_FILE_PATH = "src/main/resources/data.txt";
    private static final int NO_VERTICES = 100;

    private GraphGenerator graphGenerator;
    private GraphWriter graphWriter;
    private WeightedGraph weightedGraph;
    private MST mst;

    private void deleteFileIfExists() {
        File file = new File(OUTPUT_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @BeforeEach
    public void setup() {
        deleteFileIfExists();

        graphGenerator = new WeightedConnectedGraphGenerator();
        graphWriter = new FileGraphWriter(OUTPUT_FILE_PATH);
        weightedGraph = new WeightedGraphAdjList(0);
        mst = new KruskalMST();
    }

    @AfterEach
    public void teardown() {
        deleteFileIfExists();
    }

    @Test
    public void testKruskalFindsMST() throws IOException {
        graphWriter.writeGraph(NO_VERTICES, graphGenerator.generate(NO_VERTICES));

        weightedGraph.initFromFile(OUTPUT_FILE_PATH);

        Iterable<WeightedGraphEdge> builtSpanningTree = mst.mstEdgeList(weightedGraph);

        Assertions.assertNotNull(builtSpanningTree);
        Assertions.assertInstanceOf(List.class, builtSpanningTree);
        Assertions.assertEquals(NO_VERTICES-1, ((List<WeightedGraphEdge>)builtSpanningTree).size());
    }
}
