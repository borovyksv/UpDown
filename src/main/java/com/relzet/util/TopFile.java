package com.relzet.util;

/**
 * Created by user-pc on 06.09.2016.
 */
public class TopFile {
    private String name;
    private int size;
    private int perc;

    public TopFile(String name, int size, int perc) {

        this.name = name;
        this.size = size;
        this.perc = perc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPerc() {
        return perc;
    }

    public void setPerc(int perc) {
        this.perc = perc;
    }
}
