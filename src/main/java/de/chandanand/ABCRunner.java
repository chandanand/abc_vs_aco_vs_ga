package de.chandanand;

import de.chandanand.algorithm.ABC;
import de.chandanand.common.model.*;
import de.chandanand.common.program.Program;
import de.chandanand.common.program.ProgramStrategy;

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


}
