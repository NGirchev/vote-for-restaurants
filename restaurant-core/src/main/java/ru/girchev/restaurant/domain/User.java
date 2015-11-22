package ru.girchev.restaurant.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "MIDDLENAME")
    private String middlename;

    @Column(name = "EMAIL")
    private String email;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
//    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Role> roles;

    /**
     *
     */
    @Column(name = "UID")
    private String uid;

    @Column(name = "LAST_LOGIN_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastLogin;

    @Column(name = "IS_BLOCKED")
    @Type(type = "yes_no")
    private boolean blocked;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public static class Builder
            extends AbstractEntity.Builder<User, User.Builder> {

        public Builder withBlocked( boolean blocked ) {
            obj.setBlocked(blocked); return thisObj;
        }
        public Builder withEmail( String email ) {
            obj.setEmail(email); return thisObj;
        }
        public Builder withFirstname( String firstname ) {
            obj.setFirstname(firstname); return thisObj;
        }
        public Builder withLastLogin( Date lastLogin ) {
            obj.setLastLogin(lastLogin); return thisObj;
        }
        public Builder withLastname( String lastname ) {
            obj.setLastname(lastname); return thisObj;
        }
        public Builder withMiddlename( String middlename ) {
            obj.setMiddlename(middlename); return thisObj;
        }
        public Builder withPassword( String password ) {
            obj.setPassword(password); return thisObj;
        }
        public Builder withRoles( List<Role> roles ) {
            obj.setRoles(roles); return thisObj;
        }
        public Builder withUid( String uid ) {
            obj.setUid(uid); return thisObj;
        }
        public Builder withUsername( String username ) {
            obj.setUsername(username);
            return thisObj;
        }

        protected User createObj() { return new User(); }
        protected Builder getThis() { return this; }
    }
}
