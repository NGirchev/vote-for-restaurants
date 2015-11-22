package ru.girchev.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.Role;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
