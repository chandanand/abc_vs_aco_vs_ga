package de.chandanand.abc.algorithm;

import de.chandanand.abc.model.Node;
import de.chandanand.abc.model.Path;
import de.chandanand.abc.model.Paths;
import de.chandanand.abc.program.Program;

/**
 * Created by chand on 7/8/16.
 */
public class ABC {

    public Paths getControlFlowPaths(Program program) {
        int complexity = program.complexity;
        Node startNode = program.controlFlowGraphRoot;

        Paths paths = new Paths();

        for (int i = 1; i <= (2 * complexity); i++) {
            Path newPath = generateNewPath(startNode);

            if (!paths.contains(newPath))
                paths.addPath(newPath);
        }

        return paths;
    }

    private Path generateNewPath(Node startNode) {
        Path newPath = new Path();
        generatePathForNode(startNode, newPath);
        return newPath;
    }

    private void generatePathForNode(Node node, Path newPath) {
        newPath.loopOptimization(node);

        newPath.addNode(node);

        if (node.trueConditionNode == null)
            return;

        if (node.falseConditionNode == null) {
            node.trueConditionNode.incrementFitness();
            generatePathForNode(node.trueConditionNode, newPath);
        } else {
            if (node.trueConditionNode.getFitness() < node.falseConditionNode.getFitness()) {
                node.trueConditionNode.incrementFitness();
                generatePathForNode(node.trueConditionNode, newPath);
            } else {
                node.falseConditionNode.incrementFitness();
                generatePathForNode(node.falseConditionNode, newPath);
            }
        }
    }
}
