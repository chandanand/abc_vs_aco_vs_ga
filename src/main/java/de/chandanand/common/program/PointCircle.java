package de.chandanand.common.program;

import de.chandanand.common.model.Graph;
import de.chandanand.common.model.Paths;

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
}
