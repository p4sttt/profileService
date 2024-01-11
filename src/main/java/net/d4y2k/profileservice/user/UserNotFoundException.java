package net.d4y2k.profileservice.user;

import net.d4y2k.profileservice.exception.EntityNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(UUID id) {
        super("Could not found User with id: " + id);
    }
}
