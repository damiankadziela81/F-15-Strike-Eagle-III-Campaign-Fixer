package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class FileHandler {

    static Byte[] fileToArray(String fileName){
        ArrayList<Byte> contentList = new ArrayList<Byte>();

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);

            int content;
            while ((content = fileInputStream.read()) != -1) {
                contentList.add((byte) content);
            }

            fileInputStream.close();
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

    static void compareAndFix(Byte[] array1, Byte[] array2) {


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
        } catch (IOException e) {
            System.out.println("No valid file present!");
        }
    }
}
