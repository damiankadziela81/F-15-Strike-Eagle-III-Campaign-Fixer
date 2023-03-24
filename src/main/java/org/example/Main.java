package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true){
            System.out.println("\nF-15 Strike Eagle 3 Campaign Fixer");
            System.out.println("Choose your option:");
            System.out.println("[1] Make backup (before mission)");
            System.out.println("[2] Fix campaign file (after mission)");
            System.out.println("[Q] Quit");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().toLowerCase();
            switch(userInput) {
                case "1" -> {
                    String fileName = FileNameBuilder.buildFileName();
                    FileHandler.makeBackup(fileName,"backup" + fileName);
                }
                case "2" -> {
                    System.out.println("fixing file...");
                }
                case "q" -> System.exit(0);
                default -> System.out.println("Invalid input, please try again.");
            }
        }
    }
}