package ru.girchev.restaurant.mapper;

import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.RestaurantDTO;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class RestaurantMapper extends AbstractMapper<Restaurant, RestaurantDTO>{

    @Override
    public RestaurantDTO map(Restaurant restaurant) {
        RestaurantDTO dto = super.map(restaurant);
        dto.setName(restaurant.getName());
        return dto;
    }
}
