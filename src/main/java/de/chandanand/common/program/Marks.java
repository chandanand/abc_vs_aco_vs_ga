package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 1/9/16.
 */
public class Marks extends Program {

    public Marks() {
        complexity = 5;
        numberOfInputVariables = 3;
        range = 100;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
        controlFlowGraphEndNode = controlFlowGraph.getNode(12);

        decisionTree = createDecisionTree();
        decisionTreeRoot = decisionTree.getNode(1);
        decisionTreePaths = decisionTreePathGeneration();
        leaves = getLeaves();
    }

    private Graph createControlFlowGraph() {
        Graph controlFlowGraph = new Graph();

        for (int lineNumber = 1; lineNumber <= 12; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(4).addFalseConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(6).addTrueConditionNode(controlFlowGraph.getNode(7));
        controlFlowGraph.getNode(6).addFalseConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(7).addTrueConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(8).addTrueConditionNode(controlFlowGraph.getNode(9));
        controlFlowGraph.getNode(8).addFalseConditionNode(controlFlowGraph.getNode(10));
        controlFlowGraph.getNode(9).addTrueConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(10).addTrueConditionNode(controlFlowGraph.getNode(11));
        controlFlowGraph.getNode(11).addTrueConditionNode(controlFlowGraph.getNode(12));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int a = variables.get(0);
        int b = variables.get(1);
        int c = variables.get(2);

        float avg;
        avg = (a + b + c) / 3;
        if (avg < 40)
            return (paths.getPathNumberHavingNode(3));
        else if (avg >= 40 && avg < 50)
            return (paths.getPathNumberHavingNode(5));
        else if (avg >= 50 && avg < 60)
            return (paths.getPathNumberHavingNode(7));
        else if (avg >= 60 && avg < 75)
            return (paths.getPathNumberHavingNode(9));
        else
            return (paths.getPathNumberHavingNode(11));
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int a = chromosome.realData.get(0).intValue();
        int b = chromosome.realData.get(1).intValue();
        int c = chromosome.realData.get(2).intValue();

        double avg = (double) (a + b + c) / 3.0;
        if (avg < 40) {
            return 3;
        } else if (avg >= 40 && avg < 50)
            return 5;
        else if (avg >= 50 && avg < 60)
            return 7;
        else if (avg >= 60 && avg < 75)
            return 9;
        else
            return 11;
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 12; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(12));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(4).addTrueConditionNode(decisionTree.getNode(5));
        decisionTree.getNode(4).addFalseConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(6).addTrueConditionNode(decisionTree.getNode(7));
        decisionTree.getNode(6).addFalseConditionNode(decisionTree.getNode(8));
        decisionTree.getNode(8).addTrueConditionNode(decisionTree.getNode(9));
        decisionTree.getNode(8).addFalseConditionNode(decisionTree.getNode(10));
        decisionTree.getNode(10).addTrueConditionNode(decisionTree.getNode(11));

        return decisionTree;
    }

    private Paths decisionTreePathGeneration() {
        Paths decisionTreePaths = new Paths();

        Path newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(15));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(7));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(9));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(7));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(10));
        newPath.addNode(decisionTree.getNode(11));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(7));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(10));
        newPath.addNode(decisionTree.getNode(14));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(7));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(10));
        newPath.addNode(decisionTree.getNode(12));
        newPath.addNode(decisionTree.getNode(13));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(16));
        newPath.addNode(decisionTree.getNode(17));
        newPath.addNode(decisionTree.getNode(18));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(24));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(16));
        newPath.addNode(decisionTree.getNode(17));
        newPath.addNode(decisionTree.getNode(23));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(16));
        newPath.addNode(decisionTree.getNode(17));
        newPath.addNode(decisionTree.getNode(23));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(16));
        newPath.addNode(decisionTree.getNode(17));
        newPath.addNode(decisionTree.getNode(19));
        newPath.addNode(decisionTree.getNode(21));
        newPath.addNode(decisionTree.getNode(22));
        decisionTreePaths.addPath(newPath);

        return decisionTreePaths;
    }

    private List<Node> getLeaves() {
        List<Node> leaves = new ArrayList<>();
        leaves.add(decisionTree.getNode(3));
        leaves.add(decisionTree.getNode(11));
        leaves.add(decisionTree.getNode(5));
        leaves.add(decisionTree.getNode(7));
        leaves.add(decisionTree.getNode(9));
        leaves.add(decisionTree.getNode(12));
        return leaves;
    }

}