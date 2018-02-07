package index.Impl;

import model.User;

public class ThirdOtherIndex extends BaseIndexImpl<Integer, User>{

    public ThirdOtherIndex(String directory) {
        super(directory, "other");
        this.keyStartIdx = 15;
        this.keyEndIdx = 19;
    }



    protected User getCurIndex(Integer curKey) {
        return this.index.get(curKey);
    }

    protected User putCurIndex(Integer curKey, User user) {
        return this.index.put(curKey, user);
    }

    protected User removeCurIndex(Integer integer) {
        return null;
    }

    @Override
    protected Integer getCurKey(String key) {
        key = key.toLowerCase().replace("x", "10");
        return super.getCurKey(key);
    }
}
