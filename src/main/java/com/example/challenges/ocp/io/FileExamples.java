package com.example.challenges.ocp.io;

import java.io.*;

public class FileExamples {
    public static void main(String[] args) {
        File inputFile = new File("/Users/edgarrincon/Documents/input.txt");
        File outputFile = new File("/Users/edgarrincon/Documents/output.txt");

        try (var input = new FileReader(inputFile);
             var output = new FileWriter(outputFile)) {
            int c;
            while ((c = input.read()) != -1) {
                output.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var bufferedInput = new BufferedReader(new FileReader(inputFile));
             var bufferedOutput = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = bufferedInput.readLine()) != null) {
                bufferedOutput.write(line);
                bufferedOutput.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // new FileInputStream(file) -- input binary file without buffering
        // new FileOutputStream(file) -- output binary file without buffering
        // new BufferedInputStream(fileOutputStream) -- input binary file with buffering
        // new BufferedOutputStream(fileOutputStream) -- output binary file with buffering

    }
}
