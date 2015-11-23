package ru.girchev.restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.girchev.restaurant.domain.Role;
import ru.girchev.restaurant.domain.User;
import ru.girchev.restaurant.dto.UserDTO;
import ru.girchev.restaurant.repository.UserRepository;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String email, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public User registerUser(String email, String password, String firstName, String lastname, String middlename) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean hasRole(String role) {
        for (Role r : getCurrentUser().getRoles()) {
            if (r.getName().equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsAdapter) {
            UserDetailsAdapter adapter = (UserDetailsAdapter)authentication.getPrincipal();
            return adapter.getUser();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email is not found in the database");
        }
        return new UserDetailsAdapter(user);

    }
}
