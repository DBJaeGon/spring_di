package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    
    private Map<String, User> userMap = new HashMap<>();

    public User findUserById(String id) {
        return userMap.get(id);
    }

    public void setUserMap(List<User> users) {
        for (User user : users) {
            userMap.put(user.getId(), user);
        }
    }
}
