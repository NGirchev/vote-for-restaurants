package ru.girchev.restaurant.dto;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 22.11.15.
 */
public class AbstractDTO {

    private Long id;

    public AbstractDTO() {
    }

    public AbstractDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
