package com.iteso;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        AFD afd = new AFD("sim1.txt");
        afd.simulate();

//        System.out.println(Arrays.toString(afd.getAlphabet()));
    }

}
