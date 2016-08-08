package de.chandanand.program;

import de.chandanand.abc.model.Node;
import de.chandanand.abc.model.Paths;

/**
 * Created by chand on 7/8/16.
 */
public abstract class Program {

    public int complexity;
    public int numberOfInputVariables;
    public int range;
    public Node controlFlowGraphRoot;

    public abstract int program(Paths paths, int a, int b, int c);
}
