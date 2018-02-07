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
        String baseFilePath = this.directory + "/" +filename;
        File baseDirectory = new File(this.directory);
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
        }
        this.curFile = new File(baseFilePath + ".data");
        this.newFile = new File(baseFilePath + ".repo");
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

    public Object read(){

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.curFile));
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
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

    public String getFilePath() {
        try {
            return this.curFile.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.curFile.getAbsolutePath();
    }

    public String getFileDirectory() {
        return this.getFilePath().replace(this.curFile.getName(), "");
    }



}
