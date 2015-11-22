package ru.girchev.restaurant.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "MENU")
public class Menu extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "RESTAURANT_ID")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;

    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public static class Builder
            extends AbstractEntity.Builder<Menu, Menu.Builder> {

        public Builder withCreatedOn( Date createdOn ) {
            obj.setCreatedOn(createdOn); return thisObj;
        }
        public Builder withRestaurant( Restaurant restaurant ) {
            obj.setRestaurant(restaurant); return thisObj;
        }
        public Builder withMenuItems( List<MenuItem> menuItems ) {
            obj.setMenuItems(menuItems); return thisObj;
        }
        protected Menu createObj() { return new Menu(); }
        protected Builder getThis() { return this; }
    }
}
