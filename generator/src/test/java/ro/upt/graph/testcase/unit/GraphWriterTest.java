package ro.upt.graph.testcase.unit;

import org.junit.jupiter.api.*;
import ro.upt.graph.entity.Edge;
import ro.upt.graph.writer.GraphWriter;
import ro.upt.graph.writer.impl.FileGraphWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GraphWriterTest {
    private static final String OUTPUT_FILE_PATH = "src/main/resources/data.txt";
    private static final List<Edge> VALID_MST = List.of(
            new Edge(3, 4),
            new Edge(4, 5),
            new Edge(1, 2),
            new Edge(0, 2)
    );

    private GraphWriter graphWriter;

    private void deleteFileIfExists() {
        File file = new File(OUTPUT_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @BeforeEach
    public void setup() {
        deleteFileIfExists();
    }

    @AfterEach
    public void teardown() {
        deleteFileIfExists();
    }

    @Test
    public void testFileCreated() throws IOException {
        GraphWriter graphWriter = new FileGraphWriter(OUTPUT_FILE_PATH);
        graphWriter.writeGraph(5,VALID_MST);

        File file = new File(OUTPUT_FILE_PATH);
        Assertions.assertTrue(file.exists());
    }
}
