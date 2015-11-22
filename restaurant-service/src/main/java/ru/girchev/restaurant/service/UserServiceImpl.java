package ru.girchev.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.girchev.restaurant.domain.Role;
import ru.girchev.restaurant.domain.User;
import ru.girchev.restaurant.dto.UserDTO;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User registerUser(String email, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public User registerUser(String email, String password, String firstName, String lastname, String middlename) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public User authUser(String email, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean hasRole(String role) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean hasRole(Role role) {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public UserDTO create(UserDTO dto) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UserDTO findOne(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<UserDTO> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
