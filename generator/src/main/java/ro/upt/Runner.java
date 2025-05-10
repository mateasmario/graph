package ro.upt;

import ro.upt.argument.ArgumentParser;
import ro.upt.argument.entity.ArgumentCollection;
import ro.upt.argument.exception.ArgumentParseException;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.generator.impl.UnweightedConnectedGraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;
import ro.upt.graph.writer.impl.FileGraphWriter;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException, ArgumentParseException {
        ArgumentParser argumentParser = new ArgumentParser();

        ArgumentCollection argumentCollection = argumentParser.parseArguments(args);

        int noVertices = (Integer)argumentCollection.getByKey("n").getValue();
        boolean weighted = (Boolean)argumentCollection.getByKey("w").getValue();
        String file = (String)argumentCollection.getByKey("f").getValue();

        System.out.println(noVertices);
        System.out.println(weighted);
        System.out.println(file);

        GraphGenerator graphGenerator;

        if (weighted) {
            graphGenerator = new WeightedConnectedGraphGenerator();
        }
        else {
            graphGenerator = new UnweightedConnectedGraphGenerator();
        }

        new FileGraphWriter(file).writeGraph(noVertices, graphGenerator.generate(noVertices));
    }
}