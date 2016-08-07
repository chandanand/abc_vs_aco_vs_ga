package de.chandanand.abc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class Graph {

    private List<Node> nodes = new ArrayList<>();

    public void newNode(int lineNumber) {
        nodes.add(new Node(lineNumber));
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }
}
