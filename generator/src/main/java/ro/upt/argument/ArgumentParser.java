package ro.upt.argument;

import ro.upt.argument.entity.Argument;
import ro.upt.argument.entity.ArgumentCollection;
import ro.upt.argument.exception.ArgumentParseException;
import ro.upt.argument.type.ArgumentType;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    public ArgumentCollection parseArguments(String[] args) throws ArgumentParseException {
        List<Argument> arguments = fillWithDefaultArguments();

        String currentKey = null;

        for (int i = 0; i < args.length; i++) {
            if (isKey(i)) {
                currentKey = convertToValidKeyFormat(args[i]);
            } else {
                String currentValue = args[i];
                updateArgument(arguments, currentKey, currentValue);
            }
        }

        checkForMandatoryArguments(arguments);

        return new ArgumentCollection(arguments);
    }

    private List<Argument> fillWithDefaultArguments() {
        return new ArrayList<>(Argument.ALLOWED_ARGUMENTS);
    }

    private boolean isKey(int key) {
        return key % 2 == 0;
    }

    private String convertToValidKeyFormat(String key) throws ArgumentParseException {
        if (!key.startsWith("-")) {
            throw new ArgumentParseException("Argument keys should start with a minus.");
        }

        if (key.length() == 1) {
            throw new ArgumentParseException("Key cannot be empty.");
        }

        return key.substring(1);
    }

    private void updateArgument(List<Argument> arguments, String key, String value) throws ArgumentParseException {
        Argument arg = getArgumentFromList(arguments, key);

        if (arg == null) {
            throw new ArgumentParseException("Unexpected argument with given key " + key + ".");
        }

        arg.setValue(
                mapArgumentValue(arg.getType(), value)
        );
    }

    private Argument getArgumentFromList(List<Argument> arguments, String key) {
        for(Argument arg : arguments) {
            if (arg.getKey().equals(key)) {
                return arg;
            }
        }

        return null;
    }

    private Object mapArgumentValue(ArgumentType type, String value) {
        return switch (type) {
            case INTEGER -> Integer.parseInt(value);
            case BOOLEAN -> Boolean.valueOf(value);
            case STRING -> value;
        };
    }

    private void checkForMandatoryArguments(List<Argument> arguments) throws ArgumentParseException {
        for(Argument arg : arguments) {
            if (arg.getValue() == null) {
                throw new ArgumentParseException("Argument " + arg.getKey() + " should have an explicit value.");
            }
        }
    }
}
