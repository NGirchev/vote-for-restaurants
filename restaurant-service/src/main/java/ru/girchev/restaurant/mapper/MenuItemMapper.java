package ru.girchev.restaurant.mapper;

import ru.girchev.restaurant.domain.MenuItem;
import ru.girchev.restaurant.dto.MenuItemDTO;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class MenuItemMapper extends AbstractMapper<MenuItem, MenuItemDTO>{

    @Override
    public MenuItemDTO map(MenuItem menuItem) {
        MenuItemDTO dto = super.map(menuItem);
        dto.setName(menuItem.getName());
        dto.setCost(menuItem.getPrice().toString());
        return dto;
    }
}
