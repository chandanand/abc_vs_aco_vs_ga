package de.chandanand.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chand on 7/8/16.
 */
public class TestCase {

    private List<Integer> variableSet = new ArrayList<>();
    public int satisfyingNode;
    public int satisfyingPath;

    public void addValue(Integer testValue) {
        variableSet.add(testValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        return !(variableSet != null ? !variableSet.equals(testCase.variableSet) : testCase.variableSet != null);

    }

    @Override
    public int hashCode() {
        return variableSet != null ? variableSet.hashCode() : 0;
    }

    public int getVariable(int variableIndex) {
        return variableSet.get(variableIndex);
    }

    public List<Integer> getVariableSet() {
        return variableSet;
    }
}
