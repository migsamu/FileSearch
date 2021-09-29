package org.iesfm.filesearch;

public class Occurrences {

    private int occurrences;

    public Occurrences() {
        this.occurrences = 0;
    }

    public void add(int add) {
        occurrences += add;
    }

    public int getOccurrences() {
        return occurrences;
    }
}
