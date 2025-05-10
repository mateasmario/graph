package ro.upt.graph.writer.impl;

import ro.upt.graph.entity.Edge;
import ro.upt.graph.writer.GraphWriter;
import ro.upt.printer.Printer;
import ro.upt.printer.impl.SysOutPrinter;

import java.io.IOException;
import java.util.List;

public class ConsoleGraphWriter implements GraphWriter {
    private final Printer printer = new SysOutPrinter();
    @Override
    public void writeGraph(int noVertices, List<Edge> edgeList) throws IOException {
        printer.printMessage(String.valueOf(noVertices));
        for(Edge e : edgeList) {
            printer.printMessage(e.toString());
        }
    }
}
