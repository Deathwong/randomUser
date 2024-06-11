package com.jefrido.randomuser.service;

import com.jefrido.randomuser.domain.User;

import java.util.List;

public interface UserService {

    /**
     * Fetches users from randomuser.me API.
     *
     * @param results Number of users to fetch.
     * @return List of {@link User}.
     */
    List<User> getUsers(int results, String sortBy);
}
