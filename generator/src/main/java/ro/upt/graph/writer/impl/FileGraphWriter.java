package ro.upt.graph.writer.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.writer.GraphWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGraphWriter implements GraphWriter {
    private String path;
    public FileGraphWriter(String path) {
        this.path = path;
    }
    @Override
    public void writeGraph(int noVertices, List<Edge> edgeList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(String.valueOf(noVertices));
            writer.newLine();

            for(Edge e : edgeList) {
                writer.write(e.toString());
                writer.newLine();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
