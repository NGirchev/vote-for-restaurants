package ru.girchev.restaurant.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "MENU_ITEM")
public class MenuItem extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @JoinColumn(name = "MENU_ID")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Menu menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public static class Builder
            extends AbstractEntity.Builder<MenuItem, MenuItem.Builder> {

        public Builder withName( String name ) {
            obj.setName(name); return thisObj;
        }
        public Builder withPrice( BigDecimal price ) {
            obj.setPrice(price); return thisObj;
        }
        public Builder withMenu( Menu menu ) {
            obj.setMenu(menu); return thisObj;
        }
        protected MenuItem createObj() { return new MenuItem(); }
        protected Builder getThis() { return this; }
    }
}
