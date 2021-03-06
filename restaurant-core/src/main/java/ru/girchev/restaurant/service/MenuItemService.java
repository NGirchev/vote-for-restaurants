package ru.girchev.restaurant.service;

import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.domain.MenuItem;
import ru.girchev.restaurant.domain.Restaurant;
import ru.girchev.restaurant.dto.MenuDTO;
import ru.girchev.restaurant.dto.MenuItemDTO;
import ru.girchev.restaurant.dto.RestaurantDTO;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public interface MenuItemService extends CrudService<MenuItemDTO>{

    List<MenuItemDTO> getAllForMenu(Long menuId);
}
