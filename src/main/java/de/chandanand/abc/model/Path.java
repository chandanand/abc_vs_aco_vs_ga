package de.chandanand.abc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class Path {

    private List<Node> nodes = new ArrayList<>();


    public void loopOptimization(Node startNode) {
        for (Node node : nodes) {
            if (startNode.lineNumber == node.lineNumber)
                node.incrementFitness();
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }
}
