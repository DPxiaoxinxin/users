package index.Impl;

import index.IBaseIndex;
import io.FileSync;
import model.User;

import java.io.IOException;
import java.util.TreeMap;

public abstract class BaseIndexImpl<K, V> extends FileSync implements IBaseIndex {

    protected TreeMap<K, V> index;
    protected int keyStartIdx;
    protected int keyEndIdx;
    private BaseIndexImpl<K, V> nextIndex;


    public BaseIndexImpl(String directory, String filename) {
        super(directory, filename);
        this.initIndex();
    }

    public void setNextIndex(BaseIndexImpl nextIndex) {
        this.nextIndex = nextIndex;
    }


    public void initIndex() {
        try {
            this.index = (TreeMap<K, V>) this.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (this.index == null) {
            this.index = new TreeMap<K, V>();
        }
    }

    public User get(String key) {
        K curKey = this.getCurKey(key);
        V v = this.getCurIndex(curKey);
        if (this.nextIndex != null) {
            return this.nextIndex.get(key);
        }
        return (User) v;
    }

    public User put(String key, User user) {
        K curKey = this.getCurKey(key);
        V v = this.putCurIndex(curKey, user);
        if (this.nextIndex != null) {
            return this.nextIndex.get(key);
        }
        return (User) v;
    }

    public User remove(String key) {
        return null;
    }

    abstract protected V getCurIndex(K k);

    abstract protected V putCurIndex(K k, User user);

    abstract protected V removeCurIndex(K k);

    private synchronized void sync() {
        try {
            this.write(this.index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected K getCurKey(String key) {
        return (K) key.substring(this.keyStartIdx, this.keyEndIdx);
    }

}
