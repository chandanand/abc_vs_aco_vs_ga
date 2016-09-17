package de.chandanand.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class Path {

    private List<Node> nodes = new ArrayList<>();
    private boolean visited = false;
    private int setValue;
    private double tempFitness;
    private double fitnessValue;
    private double probability;
    public double strength;


    public void loopOptimization(Node startNode) {
        for (Node node : nodes) {
            if (startNode.lineNumber == node.lineNumber)
                node.incrementFitness();
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path = (Path) o;

        return !(nodes != null ? !nodes.equals(path.nodes) : path.nodes != null);

    }

    @Override
    public int hashCode() {
        return nodes != null ? nodes.hashCode() : 0;
    }

    public boolean contains(int nodeNumber) {
        return nodes.contains(new Node(nodeNumber));
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isNodePresent(Node node) {
        return nodes.contains(node);
    }

    public void setSetValue(int setValue) {
        this.setValue = setValue;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getSetValue() {
        return setValue;
    }

    public void setTempFitness(double tempFitness) {
        this.tempFitness = tempFitness;
    }

    public double getTempFitness() {
        return tempFitness;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }
}
