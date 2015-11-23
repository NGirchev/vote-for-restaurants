package ru.girchev.restaurant.service;

import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.dto.MenuItemDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;
import ru.girchev.restaurant.service.exception.MenuException;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface MenuService extends CrudService<MenuDTO>{

    Menu findLastMenu(Restaurant restaurant);

    List<MenuDTO> getAllForRestaurant(Long Id);

}
