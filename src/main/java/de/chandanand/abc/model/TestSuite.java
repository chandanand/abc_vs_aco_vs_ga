package de.chandanand.abc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class TestSuite {

    private List<TestCase> testCases = new ArrayList<>();

    public boolean isTestCasePresent(TestCase testCase) {
        return testCases.contains(testCase);
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public TestCase getTestCase(int index) {
        return testCases.get(index);
    }

    public Integer getSize() {
        return testCases.size();
    }
}
