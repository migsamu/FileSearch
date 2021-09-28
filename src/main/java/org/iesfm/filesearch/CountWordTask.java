package org.iesfm.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CountWordTask implements Runnable {

    private static Logger log = LoggerFactory.getLogger(CountWordTask.class);

    private String word;
    private File file;
    private FileUtils fileUtils = new FileUtils();

    private int ocurrences = 0;


    public CountWordTask(String word, File file) {
        this.word = word;
        this.file = file;
    }

    @Override
    public void run() {
        ocurrences = fileUtils.countTextOccurrences(file, word);
    }

    public int getOcurrences() {
        return ocurrences;
    }
}
