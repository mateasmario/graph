package ro.upt.graph.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<Node> generateListOfNodes(int numberOfNodes) {
        List<Node> list = new ArrayList<>();

        for (int i = 0; i<numberOfNodes; i++) {
            list.add(new Node(i));
        }

        return list;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value;
    }
}
