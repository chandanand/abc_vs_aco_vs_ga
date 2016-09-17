package de.chandanand.common.program;

import de.chandanand.common.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class TriangleClassifier extends Program {

    public TriangleClassifier() {
        complexity = 8;
        numberOfInputVariables = 3;
        range = 20;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
        controlFlowGraphEndNode = controlFlowGraph.getNode(24);
        
        decisionTree = createDecisionTree();
        decisionTreeRoot = decisionTree.getNode(1);
        decisionTreePaths = decisionTreePathGeneration();
        leaves = getLeaves();
    }

    private Graph createControlFlowGraph() {
        Graph controlFlowGraph = new Graph();

        for (int lineNumber = 1; lineNumber <= 24; lineNumber++)
            controlFlowGraph.newNode(lineNumber);

        controlFlowGraph.getNode(1).addTrueConditionNode(controlFlowGraph.getNode(2));
        controlFlowGraph.getNode(2).addTrueConditionNode(controlFlowGraph.getNode(3));
        controlFlowGraph.getNode(2).addFalseConditionNode(controlFlowGraph.getNode(16));
        controlFlowGraph.getNode(3).addTrueConditionNode(controlFlowGraph.getNode(4));
        controlFlowGraph.getNode(3).addFalseConditionNode(controlFlowGraph.getNode(5));
        controlFlowGraph.getNode(4).addTrueConditionNode(controlFlowGraph.getNode(15));
        controlFlowGraph.getNode(5).addTrueConditionNode(controlFlowGraph.getNode(6));
        controlFlowGraph.getNode(5).addFalseConditionNode(controlFlowGraph.getNode(7));
        controlFlowGraph.getNode(6).addTrueConditionNode(controlFlowGraph.getNode(15));
        controlFlowGraph.getNode(7).addTrueConditionNode(controlFlowGraph.getNode(8));
        controlFlowGraph.getNode(8).addTrueConditionNode(controlFlowGraph.getNode(9));
        controlFlowGraph.getNode(8).addFalseConditionNode(controlFlowGraph.getNode(10));
        controlFlowGraph.getNode(9).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(10).addTrueConditionNode(controlFlowGraph.getNode(11));
        controlFlowGraph.getNode(10).addFalseConditionNode(controlFlowGraph.getNode(12));
        controlFlowGraph.getNode(11).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(12).addTrueConditionNode(controlFlowGraph.getNode(13));
        controlFlowGraph.getNode(13).addTrueConditionNode(controlFlowGraph.getNode(14));
        controlFlowGraph.getNode(14).addTrueConditionNode(controlFlowGraph.getNode(15));
        controlFlowGraph.getNode(15).addTrueConditionNode(controlFlowGraph.getNode(24));
        controlFlowGraph.getNode(16).addTrueConditionNode(controlFlowGraph.getNode(17));
        controlFlowGraph.getNode(17).addTrueConditionNode(controlFlowGraph.getNode(18));
        controlFlowGraph.getNode(17).addFalseConditionNode(controlFlowGraph.getNode(19));
        controlFlowGraph.getNode(18).addTrueConditionNode(controlFlowGraph.getNode(23));
        controlFlowGraph.getNode(19).addTrueConditionNode(controlFlowGraph.getNode(20));
        controlFlowGraph.getNode(19).addFalseConditionNode(controlFlowGraph.getNode(21));
        controlFlowGraph.getNode(20).addTrueConditionNode(controlFlowGraph.getNode(23));
        controlFlowGraph.getNode(21).addTrueConditionNode(controlFlowGraph.getNode(22));
        controlFlowGraph.getNode(22).addTrueConditionNode(controlFlowGraph.getNode(23));
        controlFlowGraph.getNode(23).addTrueConditionNode(controlFlowGraph.getNode(24));

        controlFlowGraph.addEdge(controlFlowGraph.getNode(1), controlFlowGraph.getNode(2));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(2), controlFlowGraph.getNode(3));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(2), controlFlowGraph.getNode(16));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(3), controlFlowGraph.getNode(4));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(3), controlFlowGraph.getNode(5));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(4), controlFlowGraph.getNode(15));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(5), controlFlowGraph.getNode(6));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(5), controlFlowGraph.getNode(7));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(6), controlFlowGraph.getNode(15));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(7), controlFlowGraph.getNode(8));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(8), controlFlowGraph.getNode(9));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(8), controlFlowGraph.getNode(10));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(9), controlFlowGraph.getNode(14));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(10), controlFlowGraph.getNode(11));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(10), controlFlowGraph.getNode(12));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(11), controlFlowGraph.getNode(14));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(12), controlFlowGraph.getNode(13));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(13), controlFlowGraph.getNode(14));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(14), controlFlowGraph.getNode(15));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(15), controlFlowGraph.getNode(24));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(16), controlFlowGraph.getNode(17));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(17), controlFlowGraph.getNode(18));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(17), controlFlowGraph.getNode(19));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(18), controlFlowGraph.getNode(23));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(19), controlFlowGraph.getNode(20));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(19), controlFlowGraph.getNode(21));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(20), controlFlowGraph.getNode(23));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(21), controlFlowGraph.getNode(22));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(22), controlFlowGraph.getNode(23));
        controlFlowGraph.addEdge(controlFlowGraph.getNode(23), controlFlowGraph.getNode(24));

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, List<Integer> variables) {
        int a = variables.get(0);
        int b = variables.get(1);
        int c = variables.get(2);

        if ((a < (b + c)) && (b < (c + a)) && (c < (a + b))) {
            if ((a == b) && (b == c))
                return (paths.getPathNumberHavingNode(4));
            else if ((a != b) && (b != c) && (c != a))
                return (paths.getPathNumberHavingNode(6));
            else {
                if ((a == b))
                    return (paths.getPathNumberHavingNode(9));
                else if (b == c)
                    return (paths.getPathNumberHavingNode(11));
                else if (c == a)
                    return (paths.getPathNumberHavingNode(13));
            }
        } else {
            if (a >= (b + c))
                return (paths.getPathNumberHavingNode(18));
            else if (b >= (c + a))
                return (paths.getPathNumberHavingNode(20));
            else
                return (paths.getPathNumberHavingNode(22));
        }

        return 0;
    }

    @Override
    public int getLeafByEvaluation(Chromosome chromosome) {
        int a = chromosome.realData.get(0).intValue();
        int b = chromosome.realData.get(1).intValue();
        int c = chromosome.realData.get(2).intValue();

        if ((a < (b + c)) && (b < (c + a)) && (c < (a + b))) {
            if ((a == b) && (b == c))
                return 4;
            else if ((a != b) && (b != c) && (c != a))
                return 6;
            else {
                if ((a == b))
                    return 9;
                else if (b == c)
                    return 11;
                else if (c == a)
                    return 13;
            }
        } else {
            if (a >= (b + c))
                return 18;
            else if (b >= (c + a))
                return 20;
            else
                return 22;
        }

        return 0;
    }

    private Graph createDecisionTree() {
        Graph decisionTree = new Graph();

        for (int i = 1; i <= 24; i++)
            decisionTree.newNode(i);

        decisionTree.getNode(1).addTrueConditionNode(decisionTree.getNode(2));
        decisionTree.getNode(2).addTrueConditionNode(decisionTree.getNode(3));
        decisionTree.getNode(2).addNeutralConditionNode(decisionTree.getNode(24));
        decisionTree.getNode(2).addFalseConditionNode(decisionTree.getNode(16));
        decisionTree.getNode(3).addTrueConditionNode(decisionTree.getNode(4));
        decisionTree.getNode(3).addNeutralConditionNode(decisionTree.getNode(15));
        decisionTree.getNode(3).addFalseConditionNode(decisionTree.getNode(5));
        decisionTree.getNode(5).addTrueConditionNode(decisionTree.getNode(6));
        decisionTree.getNode(5).addFalseConditionNode(decisionTree.getNode(7));
        decisionTree.getNode(7).addTrueConditionNode(decisionTree.getNode(8));
        decisionTree.getNode(8).addTrueConditionNode(decisionTree.getNode(9));
        decisionTree.getNode(8).addNeutralConditionNode(decisionTree.getNode(14));
        decisionTree.getNode(8).addFalseConditionNode(decisionTree.getNode(10));
        decisionTree.getNode(10).addTrueConditionNode(decisionTree.getNode(11));
        decisionTree.getNode(10).addFalseConditionNode(decisionTree.getNode(12));
        decisionTree.getNode(12).addTrueConditionNode(decisionTree.getNode(13));
        decisionTree.getNode(16).addTrueConditionNode(decisionTree.getNode(17));
        decisionTree.getNode(17).addTrueConditionNode(decisionTree.getNode(18));
        decisionTree.getNode(17).addNeutralConditionNode(decisionTree.getNode(23));
        decisionTree.getNode(17).addFalseConditionNode(decisionTree.getNode(19));
        decisionTree.getNode(19).addTrueConditionNode(decisionTree.getNode(20));
        decisionTree.getNode(19).addFalseConditionNode(decisionTree.getNode(22));

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
        leaves.add(decisionTree.getNode(6));
        leaves.add(decisionTree.getNode(20));
        leaves.add(decisionTree.getNode(18));
        leaves.add(decisionTree.getNode(22));
        leaves.add(decisionTree.getNode(9));
        leaves.add(decisionTree.getNode(13));
        leaves.add(decisionTree.getNode(11));
        leaves.add(decisionTree.getNode(14));
        leaves.add(decisionTree.getNode(23));
        leaves.add(decisionTree.getNode(24));
        leaves.add(decisionTree.getNode(15));
        leaves.add(decisionTree.getNode(4));
        return leaves;
    }


}
