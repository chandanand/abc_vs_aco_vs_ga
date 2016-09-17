package de.chandanand.common.program;

import de.chandanand.common.model.Chromosome;
import de.chandanand.common.model.Graph;
import de.chandanand.common.model.Node;
import de.chandanand.common.model.Paths;

import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public abstract class Program {

    public int complexity;
    public int numberOfInputVariables;
    public int range;

    public Graph controlFlowGraph;
    public Node controlFlowGraphRoot;
    public Node controlFlowGraphEndNode;

    public Graph decisionTree;
    public Node decisionTreeRoot;
    public Paths decisionTreePaths;
    public List<Node> leaves;

    public abstract int program(Paths paths, List<Integer> variables);
    public abstract int getLeafByEvaluation(Chromosome chromosome);
}
