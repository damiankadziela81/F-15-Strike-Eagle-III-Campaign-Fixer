package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class FileHandler {

    static Byte[] fileToArray(String fileName){
        ArrayList<Byte> contentList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            int content;
            while ((content = fileInputStream.read()) != -1) {
                contentList.add((byte) content);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentList.toArray(new Byte[0]);
    }

    static void arrayToFile (Byte[] contentArray, String newFileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFileName);
            for (Byte content : contentArray) {
                fileOutputStream.write(content);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void makeBackup(String sourceFileName, String destinationFileName) {
        try {
            FileInputStream sourceFileInputStream = new FileInputStream(sourceFileName);
            FileOutputStream destinationFileOutputStream = new FileOutputStream(destinationFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = sourceFileInputStream.read(buffer)) > 0) {
                destinationFileOutputStream.write(buffer, 0, length);
            }
            sourceFileInputStream.close();
            destinationFileOutputStream.close();
            System.out.println("File " + sourceFileName + " has been backed up as " + destinationFileName + ".");
            System.out.println("You can fly your mission now.");
        } catch (IOException e) {
            System.out.println("No valid file present!");
        }
    }
}
