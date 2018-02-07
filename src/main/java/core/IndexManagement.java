package core;

import index.IBaseIndex;
import index.Impl.FirstAreaIndex;
import model.User;

public class IndexManagement {

    private static IBaseIndex index = new FirstAreaIndex("./");

    public User getUser(String id) {
        return index.get(id);
    }

    public User putUser(User user) {
        return index.put(user.getId(), user);
    }

}
