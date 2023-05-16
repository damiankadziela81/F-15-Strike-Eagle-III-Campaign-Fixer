package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nF-15 Strike Eagle 3 Campaign Fixer");
        System.out.println("Choose your option:");
        System.out.println("[1] Make backup (before mission)");
        System.out.println("[2] Fix campaign file (after mission)");
        System.out.println("[Q] Quit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "1" -> {
                String fileName = FileNameBuilder.buildFileName(scanner);
                FileHandler.makeBackup(fileName, "backup" + fileName);
            }
            case "2" -> {
                String fileName = FileNameBuilder.buildFileName(scanner);
                Byte[] targetsArray = FileHandler.fileToArray(fileName);
                if (targetsArray.length == 0) {
                    System.out.println("Restart the program and enter correct data.");
                    break;
                }
                Byte[] backupTargetsArray = FileHandler.fileToArray("backup" + fileName);
                if (backupTargetsArray.length == 0) {
                    System.out.println("There is no backup for " + fileName + " file! Restart app and make backup first.");
                    break;
                }
                if (backupTargetsArray.length == 176) {
                    System.out.println("Target data array has reached max limit of 29 records.");
                    break;
                }
                if (targetsArray.length == backupTargetsArray.length) {
                    System.out.println("No new targets added since last mission.");
                    break;
                }
                if (targetsArray.length < backupTargetsArray.length) {
                    System.out.println("Error: current file size < backup file size!");
                    break;
                }
                Byte[] arrayFixed = DataHandler.reconstructTargets(targetsArray, backupTargetsArray);
                //create new file from reconstructed data
                FileHandler.arrayToFile(arrayFixed, fileName);
                //backup the new file
                FileHandler.makeBackup(fileName, "backup" + fileName);
            }
            case "q" -> System.exit(0);
            default -> System.out.println("Invalid input, restart program and try again.");
        }
    }
}
