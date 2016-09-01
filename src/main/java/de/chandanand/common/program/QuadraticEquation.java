package de.chandanand.common.program;

import de.chandanand.common.model.Graph;
import de.chandanand.common.model.Paths;

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
}
