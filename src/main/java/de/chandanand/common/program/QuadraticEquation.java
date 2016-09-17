package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 1/9/16.
 */
public class QuadraticEquation extends Program {

    public QuadraticEquation() {
        complexity = 4;
        numberOfInputVariables = 3;
        range = 20;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
        controlFlowGraphEndNode = controlFlowGraph.getNode(15);

        decisionTree = createDecisionTree();
        decisionTreeRoot = decisionTree.getNode(1);
        decisionTreePaths = decisionTreePathGeneration();
        leaves = getLeaves();
    }

    private Graph createControlFlowGraph() {
        Graph controlFlowGraph = new Graph();

        for (int lineNumber = 1; lineNumber <= 15; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(15));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(6).addTrueConditionNode(controlFlowGraph.getNode(7));
        controlFlowGraph.getNode(6).addFalseConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(7).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(8).addTrueConditionNode(controlFlowGraph.getNode(9));
        controlFlowGraph.getNode(9).addTrueConditionNode(controlFlowGraph.getNode(10));
        controlFlowGraph.getNode(9).addFalseConditionNode(controlFlowGraph.getNode(11));
        controlFlowGraph.getNode(10).addTrueConditionNode(controlFlowGraph.getNode(13));
        controlFlowGraph.getNode(11).addTrueConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(12).addTrueConditionNode(controlFlowGraph.getNode(13));
        controlFlowGraph.getNode(13).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(14).addTrueConditionNode(controlFlowGraph.getNode(15));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int a = variables.get(0);
        int b = variables.get(1);
        int c = variables.get(2);
        float d;
        if (a == 0)
            return (paths.getPathNumberHavingNode(3));
        else {
            d = (b * b) - (4 * a * c);
            if (d == 0)
                return (paths.getPathNumberHavingNode(7));
            else {
                if (d > 0)
                    return (paths.getPathNumberHavingNode(10));
                else
                    return (paths.getPathNumberHavingNode(12));
            }
        }
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int a = chromosome.realData.get(0).intValue();
        int b = chromosome.realData.get(1).intValue();
        int c = chromosome.realData.get(2).intValue();

        float d;
        if (a == 0)
            return 3;
        else {
            d = (b * b) - (4 * a * c);
            if (d == 0)
                return 7;
            else {
                if (d > 0)
                    return 10;
                else
                    return 12;
            }
        }
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 15; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(15));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(4).addTrueConditionNode(decisionTree.getNode(5));
        decisionTree.getNode(5).addTrueConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(6).addTrueConditionNode(decisionTree.getNode(7));
        decisionTree.getNode(6).addNeutralConditionNode(decisionTree.getNode(14));
        decisionTree.getNode(6).addFalseConditionNode(decisionTree.getNode(8));
        decisionTree.getNode(8).addTrueConditionNode(decisionTree.getNode(9));
        decisionTree.getNode(9).addTrueConditionNode(decisionTree.getNode(10));
        decisionTree.getNode(9).addNeutralConditionNode(decisionTree.getNode(13));
        decisionTree.getNode(9).addFalseConditionNode(decisionTree.getNode(11));
        decisionTree.getNode(11).addTrueConditionNode(decisionTree.getNode(12));

        return decisionTree;
    }

    private Paths decisionTreePathGeneration() {
        Paths decisionTreePaths = new Paths();

        Path newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(15));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(7));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(14));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(9));
        newPath.addNode(decisionTree.getNode(10));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(9));
        newPath.addNode(decisionTree.getNode(13));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(8));
        newPath.addNode(decisionTree.getNode(9));
        newPath.addNode(decisionTree.getNode(11));
        newPath.addNode(decisionTree.getNode(12));
        decisionTreePaths.addPath(newPath);

        return decisionTreePaths;
    }

    private List<Node> getLeaves() {
        List<Node> leaves = new ArrayList<>();
        leaves.add(decisionTree.getNode(3));
        leaves.add(decisionTree.getNode(7));
        leaves.add(decisionTree.getNode(10));
        leaves.add(decisionTree.getNode(12));
        leaves.add(decisionTree.getNode(13));
        leaves.add(decisionTree.getNode(14));
        leaves.add(decisionTree.getNode(15));
        return leaves;
    }
}
