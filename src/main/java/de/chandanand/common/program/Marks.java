package de.chandanand.common.program;

import de.chandanand.common.model.Graph;
import de.chandanand.common.model.Paths;

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

}