package index;

public interface IBaseIndex<K, V> {

    void createIndex();

    void recoveryIndex();

    V get(K k);

    V put(K k, V v);

    V remove(K k);


}
