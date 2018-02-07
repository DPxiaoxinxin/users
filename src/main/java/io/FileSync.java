package io;

import java.io.*;

public class FileSync {

    private final String directory;
    private final String filename;
    private File curFile;
    private File newFile;

    public FileSync(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        this.open();
    }


    public void open() {
        String basepath = this.directory + filename;
        this.curFile = new File(basepath + ".data");
        this.newFile = new File(basepath + ".repo");
        if (!curFile.exists()) {
            if (newFile.exists()) {
                newFile.renameTo(curFile);
            } else {
                try {
                    curFile.createNewFile();
                    newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object read() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.curFile));
        return objectInputStream.readObject();
    }

    public void write(Object v) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.newFile));
        objectOutputStream.writeObject(v);
        objectOutputStream.close();
        synchronized (this.curFile) {
            this.curFile.renameTo(new File(this.curFile.getPath() + ".old"));
            this.newFile.renameTo(this.curFile);
        }
    }

    public String getPath() {
        return this.curFile.getPath();
    }



}
