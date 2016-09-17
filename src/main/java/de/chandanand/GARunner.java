package de.chandanand;

import de.chandanand.algorithm.ABC;
import de.chandanand.algorithm.GA;
import de.chandanand.common.model.Paths;
import de.chandanand.common.model.TestSuite;
import de.chandanand.common.program.Program;
import de.chandanand.common.program.ProgramStrategy;

/**
 * Created by chand on 9/8/16.
 */
public class GARunner {

    public static void main(String[] args) {
        GA ga = new GA();

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

        TestSuite testSuite = ga.testSuiteGeneration(program);

        Long endTime = System.nanoTime();

        System.out.println("Time: " + ((endTime - startTime) * Math.pow(10, -9)) + " sec");
    }
}
