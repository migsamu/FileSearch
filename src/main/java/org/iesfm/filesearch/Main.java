package org.iesfm.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        log.info("Introduce un directorio");
        String folderPath = scanner.nextLine();

        File folder = new File(folderPath);

        log.info("Introduce el texto a buscar");
        String word = scanner.nextLine();

        FileUtils fileUtils = new FileUtils();

        int ocurrences = 0;

        for (File file : folder.listFiles()) {
            CountWordTask countWordTask = new CountWordTask(word, file);
            Thread countWordThread = new Thread(countWordTask);
            countWordThread.start();
            try {
                countWordThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ocurrences += countWordTask.getOcurrences();

        }

        log.info("La palabra " + word + "aparece " + ocurrences + " veces");

    }

}
