package ro.upt.argument.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArgumentCollection {
    private final List<Argument> argumentList;

    public ArgumentCollection() {
        argumentList = new ArrayList<>();
    }

    public ArgumentCollection(List<Argument> argumentList) {
        this.argumentList = argumentList;
    }

    public Argument getByKey(String key) {
        for (Argument arg : argumentList) {
            if (arg.getKey().equals(key)) {
                return arg;
            }
        }

        return null;
    }
}
