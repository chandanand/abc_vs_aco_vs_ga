package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 2/9/16.
 */
public class Remainder extends Program {

    public Remainder() {
        complexity = 2;
        numberOfInputVariables = 2;
        range = 20;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
        controlFlowGraphEndNode = controlFlowGraph.getNode(6);

        decisionTree = createDecisionTree();
        decisionTreeRoot = decisionTree.getNode(1);
        decisionTreePaths = decisionTreePathGeneration();
        leaves = getLeaves();
    }

    private Graph createControlFlowGraph() {
        Graph controlFlowGraph = new Graph();

        for (int lineNumber = 1; lineNumber <= 6; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(6));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int a = variables.get(0);
        int b = variables.get(1);
        if (b != 0) {
            int r = a % b;
            if (r == 0)
                return (paths.getPathNumberHavingNode(3));
            else
                return (paths.getPathNumberHavingNode(4));
        }
        return 0;
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int a = chromosome.realData.get(0).intValue();
        int b = chromosome.realData.get(1).intValue();
        int r = a % b;
        if (r == 0)
            return 3;
        else
            return 5;
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 6; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(4).addTrueConditionNode(decisionTree.getNode(5));

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
        newPath.addNode(decisionTree.getNode(6));
        decisionTreePaths.addPath(newPath);

        newPath = new Path();
        newPath.addNode(decisionTree.getNode(1));
        newPath.addNode(decisionTree.getNode(2));
        newPath.addNode(decisionTree.getNode(4));
        newPath.addNode(decisionTree.getNode(5));
        decisionTreePaths.addPath(newPath);

        return decisionTreePaths;
    }

    private List<Node> getLeaves() {
        List<Node> leaves = new ArrayList<>();
        leaves.add(decisionTree.getNode(3));
        leaves.add(decisionTree.getNode(5));
        leaves.add(decisionTree.getNode(6));
        return leaves;
    }
}
