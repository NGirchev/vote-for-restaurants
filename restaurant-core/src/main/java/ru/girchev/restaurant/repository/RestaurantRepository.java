package ru.girchev.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.girchev.restaurant.domain.Restaurant;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findByIdAndDeletedFalse(Long id);

    List<Restaurant> findByDeletedFalse();
}
