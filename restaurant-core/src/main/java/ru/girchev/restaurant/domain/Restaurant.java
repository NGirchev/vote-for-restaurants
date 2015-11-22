package ru.girchev.restaurant.domain;

import javax.persistence.*;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "RESTAURANT", uniqueConstraints = {
@UniqueConstraint(columnNames = "NAME")})
public class Restaurant extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    private String name;

    public Restaurant() {}

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder
            extends AbstractEntity.Builder<Restaurant, Restaurant.Builder> {

        public Builder withName( String name ) {
            obj.setName(name); return thisObj;
        }
        protected Restaurant createObj() { return new Restaurant(); }
        protected Builder getThis() { return this; }
    }
}
