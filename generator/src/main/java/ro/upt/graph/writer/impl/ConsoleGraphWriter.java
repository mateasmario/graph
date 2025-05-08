package ro.upt.graph.writer.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.writer.GraphWriter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ConsoleGraphWriter implements GraphWriter {
    private static final Logger logger = Logger.getLogger(ConsoleGraphWriter.class.getName());
    @Override
    public void writeGraph(int noVertices, List<Edge> edgeList) throws IOException {
        logger.info(String.valueOf(noVertices));
        for(Edge e : edgeList) {
            logger.info(e.toString());
        }
    }
}
