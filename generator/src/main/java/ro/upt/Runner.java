package ro.upt;

import ro.upt.graph.generator.impl.ConnectedGraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;
import ro.upt.graph.writer.GraphWriter;
import ro.upt.graph.writer.impl.FileGraphWriter;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: graph <noEdges> <outputFile>");
        }

        new FileGraphWriter(args[1])
                .writeGraph(Integer.parseInt(args[0]), new WeightedConnectedGraphGenerator().generate(Integer.parseInt(args[0])));
    }
}