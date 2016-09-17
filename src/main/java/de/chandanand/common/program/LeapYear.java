package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 2/9/16.
 */
public class LeapYear extends Program {

    public LeapYear() {
        complexity = 4;
        numberOfInputVariables = 1;
        range = 100;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
        controlFlowGraphEndNode = controlFlowGraph.getNode(8);

        decisionTree = createDecisionTree();
        decisionTreeRoot = decisionTree.getNode(1);
        decisionTreePaths = decisionTreePathGeneration();
        leaves = getLeaves();
    }

    private Graph createControlFlowGraph() {
        Graph controlFlowGraph = new Graph();

        for (int lineNumber = 1; lineNumber <= 14; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addFalseConditionNode(controlFlowGraph.getNode(9));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(4).addFalseConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(6).addTrueConditionNode(controlFlowGraph.getNode(7));
        controlFlowGraph.getNode(7).addTrueConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(8).addTrueConditionNode(controlFlowGraph.getNode(11));
        controlFlowGraph.getNode(9).addTrueConditionNode(controlFlowGraph.getNode(10));
        controlFlowGraph.getNode(10).addTrueConditionNode(controlFlowGraph.getNode(11));
        controlFlowGraph.getNode(11).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(12).addTrueConditionNode(controlFlowGraph.getNode(13));
        controlFlowGraph.getNode(13).addTrueConditionNode(controlFlowGraph.getNode(14));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int a = variables.get(0);
        if (a % 4 == 0) {
            if (a % 100 == 0) {
                if (a % 400 == 0)
                    return (paths.getPathNumberHavingNode(5));
                else
                    return (paths.getPathNumberHavingNode(7));
            } else
                return (paths.getPathNumberHavingNode(10));
        } else
            return (paths.getPathNumberHavingNode(13));
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int a = chromosome.realData.get(0).intValue();

        if (a % 4 == 0) {
            if (a % 100 == 0) {
                if (a % 400 == 0) {
                    return 5;
                } else
                    return 7;
            } else
                return 10;
        } else
            return 13;
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 14; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(14));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(12));
        decisionTree.getNode(3).addTrueConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(3).addNeutralConditionNode(decisionTree.getNode(11));
        decisionTree.getNode(3).addFalseConditionNode(decisionTree.getNode(9));
        decisionTree.getNode(4).addTrueConditionNode(decisionTree.getNode(5));
        decisionTree.getNode(4).addNeutralConditionNode(decisionTree.getNode(8));
        decisionTree.getNode(4).addFalseConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(6).addTrueConditionNode(decisionTree.getNode(7));
        decisionTree.getNode(9).addTrueConditionNode(decisionTree.getNode(10));
        decisionTree.getNode(12).addTrueConditionNode(decisionTree.getNode(13));

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
        leaves.add(decisionTree.getNode(10));
        leaves.add(decisionTree.getNode(13));
        leaves.add(decisionTree.getNode(7));
        leaves.add(decisionTree.getNode(5));
        leaves.add(decisionTree.getNode(8));
        leaves.add(decisionTree.getNode(14));
        leaves.add(decisionTree.getNode(11));
        return leaves;
    }

}