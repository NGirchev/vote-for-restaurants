package ru.girchev.restaurant.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "ROLE")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {}

    public Role(String roleName) { this.setName(roleName); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder
            extends AbstractEntity.Builder<Role, Role.Builder> {

        public Builder withName( String name ) {
            obj.setName(name);
            return thisObj;
        }
        protected Role createObj() { return new Role(); }
        protected Builder getThis() { return this; }
    }
}
