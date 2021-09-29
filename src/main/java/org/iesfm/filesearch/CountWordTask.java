package org.iesfm.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CountWordTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(CountWordTask.class);

    private String word;
    private File file;
    private Occurrences totalOccurrences;


    public CountWordTask(String word, File file, Occurrences occurrences) {
        this.word = word;
        this.file = file;
        this.totalOccurrences = occurrences;
    }

    @Override
    public void run() {
        FileUtils fileUtils = new FileUtils();
        int fileOcurrences = fileUtils.countTextOccurrences(file, word);
        totalOccurrences.add(fileOcurrences);
        log.info("la palabra aparecen en el archivo " + file.getName() + " " + fileOcurrences + " veces");
    }

    public Occurrences getTotalOccurrences() {
        return totalOccurrences;
    }
}
