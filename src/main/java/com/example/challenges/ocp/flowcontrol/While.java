package com.example.challenges.ocp.flowcontrol;

public class While {
    public static void main(String[] args) {
        int i = 0, j = 0;
        OUTER_LOOP: while(true) {
            i++;
            while (true) {
                j++;
                System.out.println("(" + i + ", " + j + ")");
                if (j == 4) {
                    break OUTER_LOOP;
                }
            }
        }
    }
}
