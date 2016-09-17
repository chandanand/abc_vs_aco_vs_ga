package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 2/9/16.
 */
public class PointCircle extends Program {

    public PointCircle() {
        complexity = 3;
        numberOfInputVariables = 3;
        range = 20;
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

        for (int lineNumber = 1; lineNumber <= 8; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(4).addFalseConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(6).addTrueConditionNode(controlFlowGraph.getNode(7));
        controlFlowGraph.getNode(7).addTrueConditionNode(controlFlowGraph.getNode(8));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int x = variables.get(0);
        int y = variables.get(1);
        int r = variables.get(2);
        if ((x * x) + (y * y) > (r * r))
            return (paths.getPathNumberHavingNode(3));
        else if ((x * x) + (y * y) == (r * r))
            return (paths.getPathNumberHavingNode(5));
        else
            return (paths.getPathNumberHavingNode(7));
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int x = chromosome.realData.get(0).intValue();
        int y = chromosome.realData.get(1).intValue();
        int r = chromosome.realData.get(2).intValue();

        if ((x * x) + (y * y) > (r * r))
            return 3;
        else if ((x * x) + (y * y) == (r * r))
            return 5;
        else
            return 7;
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 8; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(8));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(4).addTrueConditionNode(decisionTree.getNode(5));
        decisionTree.getNode(4).addFalseConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(6).addTrueConditionNode(decisionTree.getNode(7));

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
        newPath.addNode(decisionTree.getNode(8));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(3));
        newPath.addNode(decisionTree.getNode(4));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(6));
        newPath.addNode(decisionTree.getNode(7));
        decisionTreePaths.addPath(newPath);

        return decisionTreePaths;
    }

    private List<Node> getLeaves() {
        List<Node> leaves = new ArrayList<>();
        leaves.add(decisionTree.getNode(3));
        leaves.add(decisionTree.getNode(5));
        leaves.add(decisionTree.getNode(7));
        leaves.add(decisionTree.getNode(8));
        return leaves;
    }
}
