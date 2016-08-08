package de.chandanand.program;

import de.chandanand.abc.model.Graph;
import de.chandanand.abc.model.Paths;

/**
 * Created by chand on 7/8/16.
 */
public class TriangleClassifier extends Program {

    private Graph controlFlowGraph;

    public TriangleClassifier() {
        complexity = 8;
        numberOfInputVariables = 3;
        range = 20;
        controlFlowGraph = createControlFlowGraph();
        controlFlowGraphRoot = controlFlowGraph.getNode(1);
    }

    private Graph createControlFlowGraph() {
        controlFlowGraph = new Graph();

        for (int lineNumber = 0; lineNumber <= 24; lineNumber++)
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

        return controlFlowGraph;
    }

    @Override
    public int program(Paths paths, int a, int b, int c) {
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
}