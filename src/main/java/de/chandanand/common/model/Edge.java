package de.chandanand.common.model;

/**
 * Created by chand on 8/8/16.
 */
public class Edge {

    private Node head, tail;

    public double pheromoneLevel;
    public double heuristicValue;
    public double probability;

    public Edge(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
        this.pheromoneLevel = 1;
        this.heuristicValue = 2;
        this.probability = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (head != null ? !head.equals(edge.head) : edge.head != null)
            return false;
        return !(tail != null ? !tail.equals(edge.tail) : edge.tail != null);

    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        return result;
    }

    public void updatePheromoneLevel(int alpha, int beta) {
        pheromoneLevel = Math.pow(pheromoneLevel, alpha) + Math.pow(heuristicValue, -beta);
    }

    public void updateHeuristicValue() {
        heuristicValue *= 2;
    }
}
