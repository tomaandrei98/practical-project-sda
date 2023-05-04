package org.example.validator.impl;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.validator.Validator;
import org.example.validator.exception.ValidatorException;
import org.hibernate.Session;

public class UserRoleValidator implements Validator<Integer> {
    private final Session currentSession;
    private Role role;

    public UserRoleValidator(Session currentSession, Role role) {
        this.currentSession = currentSession;
        this.role = role;
    }

    @Override
    public void validate(Integer userId) throws ValidatorException {
        User user = currentSession.createQuery(String.format("""
                from User
                where id = %s
                """, userId), User.class).getSingleResult();

        if (user.getRole() != role) {
            throw new ValidatorException(String.format("you are not allowed (you're not a/an %s)", role.getRole()));
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}