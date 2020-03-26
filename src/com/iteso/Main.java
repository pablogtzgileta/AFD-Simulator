package com.iteso;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        AFD afd = new AFD("./simulations/examen/sim2_4.txt");
        afd.simulateSubstrings();
    }

}
