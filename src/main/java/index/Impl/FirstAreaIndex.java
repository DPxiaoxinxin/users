package index.Impl;

import model.User;

public class FirstAreaIndex extends BaseIndexImpl<String>{

    public FirstAreaIndex(String directory) {
        super(directory, "area");
        this.keyStartIdx = 0;
        this.keyEndIdx = 6;
    }

    protected String getCurIndex(Integer curKey) {
        String value = this.index.get(curKey);
        SecondDateIndex secondDateIndex = new SecondDateIndex(this.getFileDirectory() + curKey.toString());
        this.setNextIndex(secondDateIndex);
        return value;
    }

    protected String putCurIndex(Integer curKey, User user) {
        String value = this.index.get(curKey);
        SecondDateIndex secondDateIndex = new SecondDateIndex(this.getFileDirectory() + curKey.toString());
        this.setNextIndex(secondDateIndex);
        if (value == null) {
            this.index.put(curKey, secondDateIndex.getFilePath());
        }
        return value;
    }

    protected String removeCurIndex(Integer integer) {
        return null;
    }
}
