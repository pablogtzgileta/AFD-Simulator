package com.iteso;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AFD {
    private String inputChain;
    private ArrayList<String> substrings = new ArrayList<>();
    private String[] alphabet;
    private char initialState;
    private String[] finalStates;
    private HashMap<Integer, String[]> pattern;
    private int currentState;
    private int resetState;
    private String sequence = "";

    // AFD constructor
    public AFD(String fileName) {
        pattern = new HashMap<>();
        setFileData(fileName);
    }

    // Simulation of substrings
    public void simulateSubstrings() {
        String validSubstrings = "";
        // Get all substrings from inputChain
        getSubstrings();
        //
        for (int i = 0; i < substrings.size(); i++) {
            if (simulateParameter(substrings.get(i))){
                validSubstrings += substrings.get(i) + ", ";
            }
        }

        System.out.println("Subcadenas");
        if (validSubstrings.length() > 1) {
            System.out.println(validSubstrings.substring(0, validSubstrings.length() - 2));
        } else {
            System.out.println(validSubstrings);
        }
    }

    // Simulate AFD with data from file
    public void simulate() {
        sequence += currentState + "/";
        for (int i = 0; i < inputChain.length(); i++) {
            // Get value from chain to check movement
            char movement = inputChain.charAt(i);

            // Find alphabet position to check in pattern
            int charPos = findAlphabetIndex(movement);

            // Move state
            currentState = Integer.parseInt(pattern.get(currentState)[charPos]);
            sequence += currentState + "/";

            if (i == inputChain.length() - 1) {
                if (Arrays.asList(finalStates).contains(String.valueOf(currentState))) {
                    System.out.println("Aceptada");
                    System.out.println("Secuencia de estados: " + sequence);
                } else {
                    System.out.println("Rechazada");
                    System.out.println("Secuencia de estados: " + sequence);
                }
            }
        }
    }

    // Simulate AFD with data passed as parameter
    private boolean simulateParameter(String chain) {
//        System.out.println(initialState);
        for (int i = 0; i < chain.length(); i++) {
            // Get value from chain to check movement
            char movement = chain.charAt(i);

            // Find alphabet position to check in pattern
            int charPos = findAlphabetIndex(movement);

            // Move state
            currentState = Integer.parseInt(pattern.get(currentState)[charPos]);

            if (i == chain.length() - 1) {
                if (Arrays.asList(finalStates).contains(String.valueOf(currentState))) {
                    currentState = resetState;
                    return true;
                } else {
                    currentState = resetState;
                    return false;
                }
            }
        }

        return false;
    }


    private void getSubstrings() {
        for (int i = 0; i < inputChain.length(); i++) {
            for (int j = i + 1; j <= inputChain.length(); j++) {
                if (!substrings.contains(inputChain.substring(i, j))) {
                    substrings.add(inputChain.substring(i, j));
                }
            }
        }
    }

    // Linear-search function to find the index of an element
    public int findAlphabetIndex(char t) {
        // if array is Null
        if (alphabet == null) {
            return -1;
        }

        int len = alphabet.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (alphabet[i].equals(String.valueOf(t))) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }


    /**
     * Read file and set values
     *
     * @param fileName File name
     */
    public void setFileData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            int lineCounter = 0;
            int patternCounter = 0;

            while (line != null) {
                if (lineCounter == 0) setInputChain(line);
                else if (lineCounter == 1) setAlphabet(splitLine(line));
                else if (lineCounter == 2) setInitialState(line.charAt(0));
                else if (lineCounter == 3) setFinalStates(splitLine(line));
                else {
                    addPattern(patternCounter, line);
                    patternCounter++;
                }
                lineCounter++;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Split line by ';'
     *
     * @param textLine line to split
     * @return Array generated by line being divided by ';'
     */
    private static String[] splitLine(String textLine) {
        return textLine.split(";");
    }

    /**
     * Add  pattern to hashmap of patterns
     *
     * @param position
     * @param textLine
     */
    private void addPattern(int position, String textLine) {
        String[] divided = textLine.split(";");

        this.pattern.put(position, divided);
    }

    public String getInputChain() {
        return inputChain;
    }

    public void setInputChain(String inputChain) {
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
        this.currentState = initialState - '0';
        this.resetState = initialState - '0';
    }

    public String[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(String[] finalStates) {
        this.finalStates = finalStates;
    }

    public HashMap<Integer, String[]> getPattern() {
        return pattern;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
