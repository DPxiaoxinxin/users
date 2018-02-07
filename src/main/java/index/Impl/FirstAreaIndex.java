package index.Impl;

import model.User;

public class FirstAreaIndex extends BaseIndexImpl<Integer, String>{

    public FirstAreaIndex(String directory) {
        super(directory, "area");
        this.keyStartIdx = 0;
        this.keyEndIdx = 7;
    }

    protected String getCurIndex(Integer curKey) {
        String value = this.index.get(curKey);
        SecondDateIndex secondDateIndex = new SecondDateIndex(curKey.toString());
        this.setNextIndex(secondDateIndex);
        return value;
    }

    protected String putCurIndex(Integer curKey, User user) {
        String value = this.index.get(curKey);
        SecondDateIndex secondDateIndex = new SecondDateIndex(curKey.toString());
        this.setNextIndex(secondDateIndex);
        if (value == null) {
            this.index.put(curKey, secondDateIndex.getPath());
        }
        return value;
    }

    protected String removeCurIndex(Integer integer) {
        return null;
    }
}
