package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

class FileNameBuilder {
    private static StringBuilder sb = new StringBuilder(2);

    static String buildFileName(){
        Scanner scanner = new Scanner(System.in);
        int slot=-1;
        while (sb.isEmpty()) {
            System.out.println("Which campaign is underway? [P]anama / [D]esert Storm / [K]orea.");
            String campaign = scanner.nextLine();
            switch(campaign.toLowerCase()) {
                case "p" -> sb.append("PA");
                case "d" -> sb.append("DS");
                case "k" -> sb.append("KO");
                default -> System.out.println("Enter valid choice.");
                }
            }
        while(sb.capacity()<3){
            System.out.println("Input your pilot slot: [0-7].");
            try{
                slot = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Enter valid choice.");
                continue;
            }
            if(slot>=0 & slot <=7) {
                sb.append(slot);
                sb.append(".exp");
            } else {
                System.out.println("Enter valid choice.");
            }
        }
        System.out.println("Current file being modified: " + sb.toString());
        return sb.toString();
    }
}
