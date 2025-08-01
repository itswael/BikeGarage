package com.waelsworld.backend.utils;

import com.waelsworld.backend.dtos.UserRequestDTO;

public class userUtils {
    public static boolean validateUserData(UserRequestDTO userRequest) {
        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            return false;
        }
        if (userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
            return false;
        }
        if (userRequest.getPhone() == null || userRequest.getPhone().isEmpty()) {
            return false;
        }
        return userRequest.getPassword() != null && !userRequest.getPassword().isEmpty();
    }

    public static boolean validatePassword(String password) {
        return password != null && password.length() >= 8;
    }
}
