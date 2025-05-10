package ro.upt;

import ro.upt.argument.ArgumentParser;
import ro.upt.argument.entity.ArgumentCollection;
import ro.upt.argument.exception.ArgumentParseException;
import ro.upt.graph.generator.GraphGenerator;
import ro.upt.graph.generator.impl.UnweightedConnectedGraphGenerator;
import ro.upt.graph.generator.impl.WeightedConnectedGraphGenerator;
import ro.upt.graph.writer.impl.FileGraphWriter;
import ro.upt.printer.Printer;
import ro.upt.printer.impl.SysOutPrinter;

import java.io.IOException;

public class Runner {
    private static final Printer printer = new SysOutPrinter();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            printer.printMessage("No arguments specified.");
            printer.printMessage("Refer to github.com/mateasmario/graph for documentation on how to use graph.");
            return;
        }

        ArgumentParser argumentParser = new ArgumentParser();

        try {
            ArgumentCollection argumentCollection = argumentParser.parseArguments(args);

            int noVertices = (Integer) argumentCollection.getByKey("n").getValue();
            boolean weighted = (Boolean) argumentCollection.getByKey("w").getValue();
            String file = (String) argumentCollection.getByKey("f").getValue();

            System.out.println(noVertices);
            System.out.println(weighted);
            System.out.println(file);

            GraphGenerator graphGenerator;

            if (weighted) {
                graphGenerator = new WeightedConnectedGraphGenerator();
            } else {
                graphGenerator = new UnweightedConnectedGraphGenerator();
            }

            new FileGraphWriter(file).writeGraph(noVertices, graphGenerator.generate(noVertices));
        } catch (ArgumentParseException ex) {
            printer.printMessage(ex.getMessage());
        }
    }
}