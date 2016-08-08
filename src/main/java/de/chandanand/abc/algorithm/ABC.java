package de.chandanand.abc.algorithm;

import de.chandanand.abc.ABCRunner;
import de.chandanand.abc.model.*;
import de.chandanand.abc.program.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chand on 7/8/16.
 */
public class ABC {

    private int iteration;

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

    public TestSuite testSuiteGeneration(Paths paths, Program program) {

        TestSuite testSuite = new TestSuite();

        initialiseTestCases(testSuite, paths, program);

        evaluateTestCases(testSuite, paths, program);

        int numberOfSets = (int) Math.sqrt(paths.getSize());

        divideIntoSets(paths, program);

        List<Integer> testCaseData = new ArrayList<>();
        iteration = 0;

        while ((!checkPathSatisfaction(paths)) && iteration < 50) {
            testCaseData.add(testSuite.getSize());

            for (int setValue = 1; setValue <= numberOfSets; setValue++) {
                double totalFitness = fitnessCalculation(setValue, paths);
                double averageProbability = probabilityCalculation(setValue, totalFitness, paths);
                int noOfNewTestCases = noOfNewTestCases(setValue, averageProbability, paths);
                newTestDataGeneration(testSuite, noOfNewTestCases, program);
                neighbourTestDataGeneration(testSuite, program, paths);
            }
            evaluateTestCases(testSuite, paths, program);
            iteration++;
        }

        return testSuite;
    }

    private void initialiseTestCases(TestSuite testSuite, Paths paths, Program program) {
        for (int i = 0; i < paths.getSize(); i++) {
            TestCase testCase = new TestCase();
            for (int j = 0; j < program.numberOfInputVariables; j++) {
                Random random = new Random();
                Integer testValue = 1 + random.nextInt(program.range);
                testCase.addValue(testValue);
            }

            if (!testSuite.isTestCasePresent(testCase)) {
                testSuite.addTestCase(testCase);
            }
        }
    }

    private void evaluateTestCases(TestSuite testSuite, Paths paths, Program program) {
        for (TestCase testCase : testSuite.getTestCases()) {
            int pathNumber = program.program(paths, testCase.getVariable(0),
                    testCase.getVariable(1), testCase.getVariable(2));
            testCase.satisfyingPath = pathNumber + 1;

            if (pathNumber != -1)
                paths.setPathVisited(pathNumber);
        }
    }

    private void divideIntoSets(Paths paths, Program program) {
        Node root = program.controlFlowGraphRoot;

        while (root.falseConditionNode == null)
            root = root.trueConditionNode;

        // for set 1
        for (Path path : paths.getPaths()) {
            if (path.isNodePresent(root.trueConditionNode))
                path.setSetValue(1);
        }

        // for set 2
        for (Path path : paths.getPaths()) {
            if (path.isNodePresent(root.falseConditionNode))
                path.setSetValue(2);
        }
    }

    private boolean checkPathSatisfaction(Paths paths) {
        for (Path path : paths.getPaths())
            if (!path.isVisited())
                return false;
        return true;
    }

    private double fitnessCalculation(int setNo, Paths paths) {
        double totalFitness = 0;
        double maxFitness = 0;
        for (Path firstPath: paths.getPaths()) {
            int tempFitness;
            if (firstPath.isVisited() && firstPath.getSetValue() == setNo) {
                for (Path secondPath: paths.getPaths()) {
                    if (!secondPath.isVisited() && secondPath.getSetValue() == setNo) {
                        tempFitness = paths.fitnessOfPath(firstPath, secondPath);
                        secondPath.setTempFitness(tempFitness);
                        maxFitness = (maxFitness < tempFitness) ? tempFitness : maxFitness;
                    }
                }
            }
        }

        for (Path firstPath: paths.getPaths())
            if (firstPath.isVisited() && firstPath.getSetValue() == setNo)
                for (Path secondPath: paths.getPaths())
                    if (!secondPath.isVisited() && secondPath.getSetValue() == setNo)
                        secondPath.setFitnessValue((secondPath.getTempFitness()) / maxFitness);

        for (Path path: paths.getPaths())
            totalFitness += path.getFitnessValue();

        return totalFitness;
    }

    private double probabilityCalculation(int setValue, double totalFitness, Paths paths) {
        int count = 1;
        double totalProbability = 0;

        for (Path path: paths.getPaths()) {
            if (!path.isVisited() && path.getSetValue() == setValue) {
                double probability = (path.getFitnessValue()) / totalFitness;
                path.setProbability(probability);
                totalProbability += probability;
                count++;
            }
        }
        return totalProbability / count;
    }

    private int noOfNewTestCases(int setValue, double averageProbability, Paths paths) {
        int newTestCases = 0;
        for (Path path: paths.getPaths())
            if (!path.isVisited() && path.getSetValue() == setValue
                    && path.getProbability() <= averageProbability)
                newTestCases++;
        return newTestCases;
    }

    private void newTestDataGeneration(TestSuite testSuite, int newTestCases, Program program) {
        for (int i = 0; i < newTestCases; i++) {
            TestCase testCase = new TestCase();
            for (int j = 0; j < program.numberOfInputVariables; j++) {
                Random random = new Random();
                Integer testValue = 1 + random.nextInt(program.range);
                testCase.addValue(testValue);
            }
            if (!testSuite.isTestCasePresent(testCase)) {
                testSuite.addTestCase(testCase);
            }
        }
    }

    private void neighbourTestDataGeneration(TestSuite testSuite, Program program, Paths paths) {
        Random random = new Random();
        int numberOfUnsolvedPaths = getNoOfUnsolvedPaths(paths);

        for (int i = 0; i < numberOfUnsolvedPaths; i++) {
            TestCase testCase = new TestCase();

            for (int j = 0; j < program.numberOfInputVariables; j++) {
                int number = random.nextInt(2) - 1;
                int index = random.nextInt(testSuite.getSize());
                testCase.addValue(testSuite.getTestCase(i).getVariable(j) +
                        number * (testSuite.getTestCase(i).getVariable(j) -
                                testSuite.getTestCase(index).getVariable(j)));
            }
            if (!testSuite.isTestCasePresent(testCase)) {
                testSuite.addTestCase(testCase);
            }
        }
    }

    private int getNoOfUnsolvedPaths(Paths paths) {
        int numberOfUnsolvedPaths = 0;
        for (Path path: paths.getPaths())
            if (!path.isVisited())
                numberOfUnsolvedPaths++;
        return numberOfUnsolvedPaths;
    }

    private int getPathCoverage(Paths paths) {
        int coveredPathCount = 0;

        for (Path path: paths.getPaths())
            if (path.isVisited())
                coveredPathCount++;

        return coveredPathCount;
    }

    public int getIterations() {
        return iteration;
    }
}
