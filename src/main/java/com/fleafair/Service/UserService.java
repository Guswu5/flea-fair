package com.fleafair.Service;

import com.fleafair.Entity.User;

public interface UserService {
    User findByUsername(String username);
    boolean checkPassword(User user, String rawPassword);
}