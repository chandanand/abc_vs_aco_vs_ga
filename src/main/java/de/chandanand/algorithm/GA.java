package de.chandanand.algorithm;

import de.chandanand.common.model.*;
import de.chandanand.common.program.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chand on 9/8/16.
 */
public class GA {

    private final int MAX_GENERATIONS = 100;
    private final double PXOVER = 0.8, PXMUTATION = 0.15;

    private int populationSize = 20;
    private Program program;
    private TestSuite testSuite;
    private int m = 0;
    private Random rand = new Random();

    public TestSuite testSuiteGeneration(Program program) {
        this.program = program;

        testSuite = new TestSuite();

        for (Node leaf : program.leaves) {
            if (!leaf.visited) {
                int numberOfGenerations = 0;
                List<Chromosome> initialPopulation = createInitialPopulation();
                List<Chromosome> currentPopulation = evaluatePopulation(initialPopulation, leaf);
                while (!leaf.visited && numberOfGenerations <= MAX_GENERATIONS) {
                    selectParents(currentPopulation);
                    createNewPopulation(currentPopulation);
                    evaluatePopulation(initialPopulation, leaf);
                    numberOfGenerations++;
                    System.out.println("No of generations" + numberOfGenerations);
                }
                if (numberOfGenerations > MAX_GENERATIONS) {
                    System.exit(1);
                }
            }
        }

        return testSuite;
    }

    private List<Chromosome> createInitialPopulation() {
        for (int i = 0; i < program.numberOfInputVariables; i++)
            m += 5;

        List<Chromosome> initialPopulation = new ArrayList<>();

        for (int j = 0; j < populationSize; j++) {
            Chromosome population = new Chromosome();
            population.initializeBinData(m);
            initialPopulation.add(population);
        }

        return initialPopulation;
    }

    private List<Chromosome> evaluatePopulation(List<Chromosome> initialPopulation, Node leaf) {

        List<Chromosome> currentPopulation = new ArrayList<>();

        for (int i = 0, j = 0; j < populationSize; i++, j++) {
            currentPopulation.add(initialPopulation.get(i));
            currentPopulation.get(j).binaryToDecimal(program.numberOfInputVariables);
            currentPopulation.get(j).decimalToReal(program.numberOfInputVariables, 5);
        }

        for (Chromosome chromosome : currentPopulation) {
            int nodeNo = program.getLeafByEvaluation(chromosome);

            int pathNo = program.decisionTreePaths.getPathNumberHavingNode(nodeNo);
            Path exePath = program.decisionTreePaths.getPathHavingPathNumber(pathNo);

            pathNo = program.decisionTreePaths.getPathNumberHavingNode(leaf.lineNumber);
            Path domPath = program.decisionTreePaths.getPathHavingPathNumber(pathNo);

            chromosome.fitness = fitnessOfTestCase(exePath, domPath);

            if (leaf.lineNumber == nodeNo) {
                leaf.visited = true;
                addTestCaseToSuite(chromosome, nodeNo);
                chromosome.nodeCovered = nodeNo;
            }
        }
        return currentPopulation;
    }

    private void addTestCaseToSuite(Chromosome currentPopulation, int nodeNo) {
        boolean flag = false;
        for (TestCase testCase : testSuite.getTestCases())
            if (testCase.getVariable(0) == currentPopulation.realData.get(0)
                    && testCase.getVariable(1) == currentPopulation.realData.get(1)
                    && testCase.getVariable(2) == currentPopulation.realData.get(2))
                flag = true;

        if (!flag) {
            TestCase testCase = new TestCase();
            for (int t = 0; t < program.numberOfInputVariables; t++)
                testCase.addValue(currentPopulation.realData.get(t).intValue());
            testCase.satisfyingNode = nodeNo;
            testSuite.addTestCase(testCase);
        }
    }

    private double fitnessOfTestCase(Path exePath, Path domPath) {
        int noOfCoveredNodes = 0, noOfDomNodes = 0;
        Double fitness;

        for (int i = 0; exePath.getNode(i) != null && domPath.getNode(i) != null
                && exePath.getNode(i) == domPath.getNode(i); i++)
            noOfCoveredNodes++;

        for (int i = 0; domPath.getNode(i) != null; i++)
            noOfDomNodes++;

        fitness = (double) noOfCoveredNodes / (double) noOfDomNodes;
        return fitness;
    }

    private void selectParents(List<Chromosome> currentPopulation) {
        List<Chromosome> parent = new ArrayList<>();
        float totalFitness = calculateTotalFitness(currentPopulation);

        for (int i = 0; i < currentPopulation.size(); i++) {
            currentPopulation.get(i).rft = totalFitness;

            if (i == 0)
                currentPopulation.get(i).cft = 0;
            else
                currentPopulation.get(i).cft = currentPopulation.get(i - 1).cft;
        }


        for (int i = 0, j = 0; i < populationSize; i++, j++) {
            float r = rand.nextFloat();
            if (r < currentPopulation.get(0).cft)
                parent.add(currentPopulation.get(0));
            else {
                for (int k = 0; k < currentPopulation.size(); k++)
                    if ((currentPopulation.get(k).cft < r
                            && currentPopulation.get(k + 1).cft > r))
                        parent.add(currentPopulation.get(k));
            }
        }
    }

    private float calculateTotalFitness(List<Chromosome> currentPopulation) {
        float totalFitness = 0;
        for (Chromosome current : currentPopulation)
            totalFitness += current.fitness;
        return totalFitness;
    }


    private void createNewPopulation(List<Chromosome> currentPopulation) {
        int XOLength = 0;

        List<Chromosome> crossOver = selectForCrossover(currentPopulation, XOLength);
        List<Chromosome> newPopulation = crossOver(currentPopulation, crossOver, XOLength);
        mutation(newPopulation);
    }

    private List<Chromosome> selectForCrossover(List<Chromosome> currentPopulation, int XOLength) {
        List<Chromosome> crossOver = new ArrayList<>();
        for (Chromosome chromosome : currentPopulation) {
            float r = rand.nextFloat();

            if (r < PXOVER) {
                crossOver.add(chromosome);
                XOLength++;
            }
        }
        if (crossOver.get(0) == null)
            System.exit(99);

        return crossOver;
    }

    private List<Chromosome> crossOver(List<Chromosome> currentPopulation, List<Chromosome> crossOver, int XOLength) {
        int pos, temp;
        for (int i = 0; i < XOLength; i++) {
            for (int j = 0; j < XOLength; j++) {
                if (i == j) continue;
                pos = rand.nextInt(m - 2) + 1;
                for (int k = pos; k < m; k++) {
                    temp = crossOver.get(i).binaryData.get(k);
                    crossOver.get(i).binaryData.set(k, crossOver.get(j).binaryData.get(k));
                    crossOver.get(j).binaryData.set(k, temp);
                }
            }
        }

        List<Chromosome> newPopulation = new ArrayList<>();
        newPopulation.addAll(currentPopulation);

        for (int i = 0; i < crossOver.size(); i++)
            if (crossOver.get(i) != null)
                newPopulation.set(i, crossOver.get(i));

        return newPopulation;
    }

    private void mutation(List<Chromosome> newPopulation) {
        if (newPopulation.get(0) == null)
            System.exit(15);
        for (Chromosome chromosome : newPopulation)
            if (chromosome != null)
                for (int i = 0; i < m; i++) {
                    float r = rand.nextFloat();
                    if (r < PXMUTATION)
                        chromosome.binaryData.set(i, (chromosome.binaryData.get(i) + 1) % 2);
                }
    }
}
