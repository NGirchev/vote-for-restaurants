package ru.girchev.restaurant.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "VOTE")
public class Vote extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "RESTAURANT_ID")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @Column(name = "LAST_UPDATED")
    @Temporal(TemporalType.DATE)
    private Date lastUpdated;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static class Builder
            extends AbstractEntity.Builder<Vote, Vote.Builder> {

        public Builder withUser( User user ) {
            obj.setUser(user); return thisObj;
        }
        public Builder withRestaurant( Restaurant restaurant ) {
            obj.setRestaurant(restaurant); return thisObj;
        }
        public Builder withLastUpdated( Date lastUpdated ) {
            obj.setLastUpdated(lastUpdated); return thisObj;
        }
        protected Vote createObj() { return new Vote(); }
        protected Builder getThis() { return this; }
    }
}
