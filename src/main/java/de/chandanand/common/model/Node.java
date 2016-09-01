package de.chandanand.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chand on 7/8/16.
 */
public class Node {

    public int lineNumber;
    public Node trueConditionNode;
    public Node falseConditionNode;
    private int fitness;
    public boolean visited;
    public List<Node> feasibleSet = new ArrayList<>();
    private boolean even;

    public Node(int lineNumber) {
        generateFitness();
        this.lineNumber = lineNumber;
        this.even = true;
        this.visited = false;
    }

    private void generateFitness() {
        Random number = new Random();
        fitness = number.nextInt(2);
    }

    public void incrementFitness() {
        fitness++;
    }

    public int getFitness() {
        return fitness;
    }

    public void addTrueConditionNode(Node node) {
        trueConditionNode = node;
    }

    public void addFalseConditionNode(Node node) {
        falseConditionNode = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return lineNumber == node.lineNumber;

    }

    @Override
    public int hashCode() {
        return lineNumber;
    }

    public void addInFeasibleSet(Node node) {
        if (even) {
            feasibleSet.add(0, node);
            even = false;
        } else {
            feasibleSet.add(1, node);
            even = true;
        }
    }
}
