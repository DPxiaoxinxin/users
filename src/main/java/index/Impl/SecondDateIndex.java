package index.Impl;

import model.User;

public class SecondDateIndex extends BaseIndexImpl<Integer, String >{


    public SecondDateIndex(String directory) {
        super(directory, "date");
        this.keyStartIdx = 7;
        this.keyEndIdx = 15;
    }

    protected String getCurIndex(Integer curKey) {
        String value = this.index.get(curKey);
        ThirdOtherIndex thirdOtherIndex = new ThirdOtherIndex(curKey.toString());
        this.setNextIndex(thirdOtherIndex);
        return value;
    }

    protected String putCurIndex(Integer curKey, User user) {
        String value = this.index.get(curKey);
        ThirdOtherIndex thirdOtherIndex = new ThirdOtherIndex(curKey.toString());
        this.setNextIndex(thirdOtherIndex);
        if (value == null) {
            this.index.put(curKey, thirdOtherIndex.getPath());
        }
        return value;
    }

    protected String removeCurIndex(Integer integer) {
        return null;
    }
}
