package ru.girchev.restaurant.dto;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class RestaurantDTO extends AbstractDTO {

    private String name;

    private MenuDTO menu;

    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public RestaurantDTO() {
        super();
    }

    public RestaurantDTO(Long id) {
        super(id);
    }
}
