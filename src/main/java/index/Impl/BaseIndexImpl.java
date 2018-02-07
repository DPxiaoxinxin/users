package index.Impl;

import index.IBaseIndex;
import io.FileSync;
import model.User;

import java.io.IOException;
import java.util.TreeMap;

public abstract class BaseIndexImpl<V> extends FileSync implements IBaseIndex {

    protected TreeMap<Integer, V> index;
    protected int keyStartIdx;
    protected int keyEndIdx;
    private BaseIndexImpl nextIndex;


    public BaseIndexImpl(String directory, String filename) {
        super(directory, filename);
        this.initIndex();
    }

    public void setNextIndex(BaseIndexImpl nextIndex) {
        this.nextIndex = nextIndex;
    }


    public void initIndex() {
        this.index = (TreeMap<Integer, V>) this.read();
        if (this.index == null) {
            this.index = new TreeMap<Integer, V>();
        }
    }

    public User get(String key) {
        Integer curKey = this.getCurKey(key);
        V v = this.getCurIndex(curKey);
        if (this.nextIndex != null) {
            return this.nextIndex.get(key);
        }
        return (User) v;
    }

    public User put(String key, User user) {
        Integer curKey = this.getCurKey(key);
        V v = this.putCurIndex(curKey, user);
        this.sync();
        if (this.nextIndex != null) {
            return this.nextIndex.put(key, user);
        }
        return (User) this.getCurIndex(curKey);
    }

    public User remove(String key) {
        return null;
    }

    abstract protected V getCurIndex(Integer curKey);

    abstract protected V putCurIndex(Integer curKey, User user);

    abstract protected V removeCurIndex(Integer curKey);

    private synchronized void sync() {
        try {
            this.write(this.index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Integer getCurKey(String key) {
        return Integer.valueOf(key.substring(this.keyStartIdx, this.keyEndIdx));
    }

}
