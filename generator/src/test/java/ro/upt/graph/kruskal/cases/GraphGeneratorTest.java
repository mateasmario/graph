package ro.upt.graph.kruskal.cases;

import org.junit.jupiter.api.*;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;
import ro.upt.graph.kruskal.entity.*;
import ro.upt.graph.writer.GraphWriter;
import ro.upt.graph.writer.impl.FileGraphWriter;

import java.io.File;
import java.io.IOException;

public class GraphGeneratorTest {
    private static final String OUTPUT_FILE_PATH = "src/main/resources/data.txt";
    private static final int NO_VERTICES = 500;
    private GraphGenerator graphGenerator;

    private static void deleteFileIfExists() {
        File file = new File(OUTPUT_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @BeforeAll
    public static void init() {
        deleteFileIfExists();
    }

    @BeforeEach
    public void setup() {
        graphGenerator = new WeightedConnectedGraphGenerator();
        deleteFileIfExists();
    }

    @Test
    public void testCreateFile() throws IOException {
        GraphWriter graphWriter = new FileGraphWriter(OUTPUT_FILE_PATH);
        graphWriter.writeGraph(NO_VERTICES, graphGenerator.generate(NO_VERTICES));

        File file = new File(OUTPUT_FILE_PATH);
        Assertions.assertTrue(file.exists());
    }

    @Test
    public void testKruskal() throws IOException {
        GraphWriter graphWriter = new FileGraphWriter(OUTPUT_FILE_PATH);
        graphWriter.writeGraph(NO_VERTICES, graphGenerator.generate(NO_VERTICES));

        WeightedGraph weightedGraph = new WeightedGraphAdjList(OUTPUT_FILE_PATH);
        MST mst = new KruskalMST();
        Iterable<WeightedGraphEdge> edge = mst.mstEdgeList(weightedGraph);
        System.out.println(edge);
    }

    @AfterEach
    public void tearDown() {
        deleteFileIfExists();
    }
}
