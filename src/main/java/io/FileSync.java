package io;

import java.io.*;

public class FileSync {

    private final String directory;
    private final String filename;
    private File file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public FileSync(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        this.open();
    }


    public void open() throws IOException {
        File file = new File(this.directory + this.filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.objectInputStream = new ObjectInputStream(new FileInputStream(file));
        this.objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
    }

    public void read() {
        return;
    }

    public void write() {
        return;
    }

    public void close() {
        return;
    }

}
