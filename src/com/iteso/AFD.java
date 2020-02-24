package com.iteso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AFD {
    private String[] inputChain;
    private String[] alphabet;
    private char initialState;
    private String[] finalStates;
    private String[][] pattern;

    public AFD(String fileName) {
        setFileData(fileName);
    }

    /**
     * Read file and set values
     * @param fileName File name
     */
    public void setFileData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            int counter = 0;

            while (line != null) {
                if (counter == 0) setInputChain(splitLine(line));
                if (counter == 1) setAlphabet(splitLine(line));
                counter++;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String[] splitLine(String textLine) {
        return textLine.split(";");
    }

    public String[] getInputChain() {
        return inputChain;
    }

    public void setInputChain(String[] inputChain) {
        this.inputChain = inputChain;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }

    public char getInitialState() {
        return initialState;
    }

    public void setInitialState(char initialState) {
        this.initialState = initialState;
    }

    public String[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(String[] finalStates) {
        this.finalStates = finalStates;
    }

    public String[][] getPattern() {
        return pattern;
    }

    public void setPattern(String[][] pattern) {
        this.pattern = pattern;
    }
}
