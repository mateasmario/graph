package ro.upt.graph.testcase.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.upt.argument.ArgumentParser;
import ro.upt.argument.entity.ArgumentCollection;
import ro.upt.argument.exception.ArgumentParseException;
import ro.upt.argument.type.ArgumentType;

public class ArgumentParserTest {
    private static final String ARG_INPUT_CORRECT = "-n 1 -w true -f test.txt";
    private static final String ARG_INPUT_ONE_MISSING_CORRECT = "-n 1 -f test.txt";
    private static final String ARG_INPUT_ONE_MISSING_INCORRECT = "-w true -f test.txt";
    private static final String ARG_INPUT_INVALID_FORMAT = "-n 1 -w true f test.txt";
    private static final String ARG_INPUT_EMPTY = "-n 1 -w true - test.txt";
    private static final String ARG_INPUT_UNKNOWN = "-n 1 -wd true -f test.txt";

    private ArgumentParser argumentParser;

    @BeforeEach
    public void setup() {
        argumentParser = new ArgumentParser();
    }

    @Test
    public void testAllArgumentsAllowed() throws ArgumentParseException {
        ArgumentCollection arguments = argumentParser.parseArguments(ARG_INPUT_CORRECT.split(" "));

        Assertions.assertNotNull(arguments);

        Assertions.assertNotNull(arguments.getByKey("n"));
        Assertions.assertEquals(1, arguments.getByKey("n").getValue());
        Assertions.assertEquals(ArgumentType.INTEGER, arguments.getByKey("n").getType());
        Assertions.assertNotNull(arguments.getByKey("w"));
        Assertions.assertEquals(true, arguments.getByKey("w").getValue());
        Assertions.assertEquals(ArgumentType.BOOLEAN, arguments.getByKey("w").getType());
        Assertions.assertNotNull(arguments.getByKey("f"));
        Assertions.assertEquals("test.txt", arguments.getByKey("f").getValue());
        Assertions.assertEquals(ArgumentType.STRING, arguments.getByKey("f").getType());
    }

    @Test
    public void testOneMissingArgumentCorrect() throws ArgumentParseException {
        ArgumentCollection arguments = argumentParser.parseArguments(ARG_INPUT_ONE_MISSING_CORRECT.split(" "));

        Assertions.assertNotNull(arguments);

        Assertions.assertNotNull(arguments.getByKey("n"));
        Assertions.assertEquals(1, arguments.getByKey("n").getValue());
        Assertions.assertEquals(ArgumentType.INTEGER, arguments.getByKey("n").getType());
        Assertions.assertNotNull(arguments.getByKey("w"));
        Assertions.assertEquals(true, arguments.getByKey("w").getValue());
        Assertions.assertEquals(ArgumentType.BOOLEAN, arguments.getByKey("w").getType());
        Assertions.assertNotNull(arguments.getByKey("f"));
        Assertions.assertEquals("test.txt", arguments.getByKey("f").getValue());
        Assertions.assertEquals(ArgumentType.STRING, arguments.getByKey("f").getType());
    }

    @Test
    public void testOneMissingArgumentIncorrect() throws ArgumentParseException {
        ArgumentParseException ex = Assertions.assertThrows(ArgumentParseException.class,
                () -> argumentParser.parseArguments(ARG_INPUT_ONE_MISSING_INCORRECT.split(" ")));

        Assertions.assertEquals("Argument n should have an explicit value.", ex.getMessage());
    }

    @Test
    public void testArgumentInvalidFormat() {
        ArgumentParseException ex = Assertions.assertThrows(ArgumentParseException.class,
                () -> argumentParser.parseArguments(ARG_INPUT_INVALID_FORMAT.split(" ")));
        Assertions.assertEquals("Argument keys should start with a minus.", ex.getMessage());
    }

    @Test
    public void testArgumentEmpty() {
        ArgumentParseException ex = Assertions.assertThrows(ArgumentParseException.class,
                () -> argumentParser.parseArguments(ARG_INPUT_EMPTY.split(" ")));
        Assertions.assertEquals("Key cannot be empty.", ex.getMessage());
    }

    @Test
    public void testArgumentNotAllowed() {
        ArgumentParseException ex = Assertions.assertThrows(ArgumentParseException.class,
                () -> argumentParser.parseArguments(ARG_INPUT_UNKNOWN.split(" ")));
        Assertions.assertEquals("Unexpected argument with given key wd.", ex.getMessage());
    }
}
