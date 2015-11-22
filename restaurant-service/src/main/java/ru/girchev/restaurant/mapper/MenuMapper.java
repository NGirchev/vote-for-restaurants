package ru.girchev.restaurant.mapper;

import ru.girchev.restaurant.domain.Menu;
import ru.girchev.restaurant.dto.MenuDTO;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class MenuMapper extends AbstractMapper<Menu, MenuDTO>{

    @Override
    public MenuDTO map(Menu menu) {
        MenuDTO dto = super.map(menu);
        dto.setItems(AbstractMapper.map(new MenuItemMapper(), menu.getMenuItems()));
        return dto;
    }
}
