package org.iesfm.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

        Occurrences occurrences = new Occurrences();

        List<Thread> threads = new ArrayList<>();
        List<CountWordTask> tasks = new ArrayList<>();
        // Por cada archivo se crea un hilo que busca el texto en eee archivo
        for (File file : folder.listFiles()) {
            // se crea la tarea que busca en el archivo file el texto word
            CountWordTask countWordTask = new CountWordTask(word, file, occurrences);

            // Creamos el thread asignandole la tarea
            Thread countWordThread = new Thread(countWordTask);
            // arrancamos el thread
            countWordThread.start();
            threads.add(countWordThread);
            tasks.add(countWordTask);
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("La palabra " + word + " aparece " + occurrences.getOccurrences() + " veces");

    }

}
