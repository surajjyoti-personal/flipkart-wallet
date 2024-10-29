package surajjyoti.nath.managers;

import surajjyoti.nath.exceptions.EntityAlreadyExistsException;
import surajjyoti.nath.exceptions.EntityDoesNotExistException;
import surajjyoti.nath.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private final Map<String, User> users;
    private UserManager() {
        users = new HashMap<>();
    }
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User creteUser(User user) throws EntityAlreadyExistsException {
        if (users.containsKey(user.getName())) {
            throw new EntityAlreadyExistsException("userName: " + user.getName() + " already exists");
        }
        users.put(user.getName(), user);
        return user;
    }

    public User getUserByName(String name) throws EntityDoesNotExistException {
        if (!users.containsKey(name)) {
            throw new EntityDoesNotExistException("userName: " + name + " does not exist");
        }
        return users.get(name);
    }
}
