package ru.girchev.restaurant.service;

import ru.girchev.restaurant.domain.Role;
import ru.girchev.restaurant.domain.User;
import ru.girchev.restaurant.dto.UserDTO;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface UserService extends CrudService<UserDTO>{

    User registerUser(String email, String password);

    User registerUser(String email, String password, String firstName, String lastname, String middlename);

    User authUser(String email, String password);

    boolean hasRole(String role);

    boolean hasRole(Role role);
}
