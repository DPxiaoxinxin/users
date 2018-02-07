package index.Impl;

import model.User;

public class SecondDateIndex extends BaseIndexImpl<String >{


    public SecondDateIndex(String directory) {
        super(directory, "date");
        this.keyStartIdx = 6;
        this.keyEndIdx = 14;
    }

    protected String getCurIndex(Integer curKey) {
        String value = this.index.get(curKey);
        ThirdOtherIndex thirdOtherIndex = new ThirdOtherIndex(this.getFileDirectory() + curKey.toString());
        this.setNextIndex(thirdOtherIndex);
        return value;
    }

    protected String putCurIndex(Integer curKey, User user) {
        String value = this.index.get(curKey);
        ThirdOtherIndex thirdOtherIndex = new ThirdOtherIndex(this.getFileDirectory() + curKey.toString());
        this.setNextIndex(thirdOtherIndex);
        if (value == null) {
            this.index.put(curKey, thirdOtherIndex.getFilePath());
        }
        return value;
    }

    protected String removeCurIndex(Integer integer) {
        return null;
    }
}
