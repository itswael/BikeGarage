package com.waelsworld.backend.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum userErrors {
    USER_NOT_FOUND("User not found"),
    INVALID_USER_DATA("Invalid user data provided"),
    REQUIRED_FIELDS_MISSING("Required fields are missing"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    PHONE_ALREADY_EXISTS("Phone number already exists"),
    PASSWORD_TOO_SHORT("Password must be at least 8 characters long"),
    USER_CREATION_FAILED("Failed to create user"),
    USER_UPDATE_FAILED("Failed to update user"),
    USER_DELETION_FAILED("Failed to delete user"),
    INVALID_LOGIN_CREDENTIALS("Email or password is incorrect");

    private final String message;
}
