package index;

import model.User;

public interface IBaseIndex {

    void initIndex();

    User get(String key);

    User put(String key, User user);

    User remove(String key);


}
