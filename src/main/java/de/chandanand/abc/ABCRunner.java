package de.chandanand.abc;

import de.chandanand.abc.algorithm.ABC;
import de.chandanand.abc.model.Paths;
import de.chandanand.abc.program.Program;
import de.chandanand.abc.program.ProgramStrategy;

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

        Paths controlFlowPaths = abc.getControlFlowPaths(program);
    }
}
