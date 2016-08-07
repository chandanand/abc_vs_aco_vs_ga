package de.chandanand.abc.model;

import java.util.Random;

/**
 * Created by chand on 7/8/16.
 */
public class Node {

    public int lineNumber;
    public Node trueConditionNode;
    public Node falseConditionNode;
    private int fitness;

    public Node(int lineNumber) {
        generateFitness();
        this.lineNumber = lineNumber;
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
}
