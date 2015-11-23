package ru.girchev.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.domain.User;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByBlockedFalse();

    User findByEmail(String email);
}
