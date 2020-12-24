package com.company.console;

import com.company.common.GeneralLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        int testNumber = readNumberFile();
        File file = new File("src/com/company/Tests/input" + testNumber + ".txt");
        List<Integer> list = readFromFile(file);
        System.out.println(list);
        List <Integer> finalList = GeneralLogic.process(list);
        printResultToFile(finalList, testNumber);
    }

    public static int readNumberFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер теста: ");
        return scanner.nextInt();
    }

    public static List<Integer> readFromFile(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    public static void printResultToFile(List<Integer> finalList, int testNumber) throws IOException {
        FileWriter fileWriter= new FileWriter(new File("src/com/company/Tests/output" + testNumber + ".txt"));
        fileWriter.append((String.valueOf(finalList)));
        fileWriter.close();
    }
}
