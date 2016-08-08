package de.chandanand.abc;

import de.chandanand.abc.algorithm.ABC;
import de.chandanand.abc.model.*;
import de.chandanand.program.Program;
import de.chandanand.program.ProgramStrategy;

/**
 * Created by chand on 7/8/16.
 */
public class ABCRunner {

    public static void main(String[] args) {
        ABC abc = new ABC();

        if (args.length == 0) {
            System.out.println("Invalid command line input (Provide program name)");
            return;
        }

        Program program = ProgramStrategy.getProgram(args[0]);

        if (program == null) {
            System.out.println("Invalid command line input (Provide valid program name)");
            return;
        }

        Long startTime = System.nanoTime();

        Paths controlFlowPaths = abc.getControlFlowPaths(program);
        TestSuite testSuite = abc.testSuiteGeneration(controlFlowPaths, program);

        Long endTime = System.nanoTime();

        System.out.println("Time: " + ((endTime - startTime) * Math.pow(10, -9)) + " sec");
    }

    public static void displayTestSuite(TestSuite testSuite) {
        for (TestCase testCase: testSuite.getTestCases()) {
            System.out.print("(");
            for (Integer value: testCase.getVariableSet())
                System.out.print(value + ",");
            System.out.print(");");
        }
    }

    public static void displayPaths(Paths paths) {
        for (Path path: paths.getPaths())
            for (Node node: path.getNodes())
                System.out.print(node.lineNumber + "->");
        System.out.println();
    }


}
