package ro.upt.argument.entity;

import ro.upt.argument.type.ArgumentType;

import java.util.List;
import java.util.Objects;

public class Argument{
    public static final List<Argument> ALLOWED_ARGUMENTS = List.of(
            new Argument("n", null, ArgumentType.INTEGER),
                    new Argument("w", false, ArgumentType.BOOLEAN),
                    new Argument("f", null,ArgumentType.STRING)
            );
    protected String key;
    protected Object value;
    private ArgumentType type;

    public Argument(String key, Object value, ArgumentType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public ArgumentType getType() {
        return type;
    }

    public void setType(ArgumentType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(key, argument.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
