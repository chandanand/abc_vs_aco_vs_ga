package de.chandanand.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chand on 11/9/16.
 */
public class Chromosome {

    public List<Integer> binaryData, decimalData;
    public List<Double> realData;

    public Double fitness;
    public int nodeCovered;
    public double rft, cft;

    public Chromosome() {
        binaryData = new ArrayList<>();
        decimalData = new ArrayList<>();
        realData = new ArrayList<>();
    }

    public void initializeBinData(int m) {
        for (int i = 0; i < m; i++) {
            Random randomNumber = new Random();
            binaryData.add(randomNumber.nextInt(2));
        }
    }

    public void binaryToDecimal(int inputVariables) {
        for (int j = 0; j < inputVariables; j++)
            for (int i = (5 * j); i < (5 * (j + 1)); i++)
                if (binaryData.get(i) == 1)
                    decimalData.add(j, decimalData.get(j) + ((Double) Math.pow(2, 5 - 1 - (i % 5))).intValue());
    }

    public void decimalToReal(int inputVars, int m) {
        double intermediate = 19.0 / (Math.pow(2, m) - 1);
        for (int i = 0; i < inputVars; i++)
            realData.add(i, 1 + (decimalData.get(i) * (Double) intermediate));
    }
}
