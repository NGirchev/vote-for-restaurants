package ru.girchev.restaurant.dto;

import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
public class MenuDTO extends AbstractDTO {

    private List<MenuItemDTO>items;

    private Long restaurantId;

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
