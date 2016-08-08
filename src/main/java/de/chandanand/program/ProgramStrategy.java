package de.chandanand.program;

/**
 * Created by chand on 7/8/16.
 */
public class ProgramStrategy {

    public static Program getProgram(String programName) {
        Program program = null;
        if ("TriangleClassifier".equalsIgnoreCase(programName))
            program = new TriangleClassifier();

        return program;
    }
}
