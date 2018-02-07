package index.Impl;

import index.IBaseIndex;
import io.FileSync;

import java.util.TreeMap;

public class BaseIndexImpl<K, V> extends FileSync implements IBaseIndex<K, V> {

    private TreeMap<K, V> index = new TreeMap<K, V>();


    public BaseIndexImpl(String directory, String filename) {
        super(directory, filename);
    }

    public void createIndex() {

    }

    public void recoveryIndex() {

    }

    public V get(K k) {
        return null;
    }

    public V put(K k, V v) {
        return null;
    }

    public V remove(K k) {
        return null;
    }

    private synchronized void sync() {
        return;
    }
}
