package ru.girchev.restaurant.domain;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

/**
 * @author Girchev N.A. <ngirchev@gmail.com>
 *         Created on 21.11.15.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * Field for optimistic lock strategy
     */
    @Version
    @Column(name = "LOCKING_KEY")
    private long lockingKey;

    /**
     * id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_generator")
    @TableGenerator(name = "entity_generator", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "IS_DELETED")
    @Type(type = "yes_no")
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        return Objects.equals(this.id, other.id);
    }

    public int defaultHashCode() {
        return super.hashCode();
    }

    protected abstract static class Builder<T extends AbstractEntity,B extends Builder<T, B>> {

        protected T obj;
        protected B thisObj;

        public Builder() {
            obj = createObj(); thisObj = getThis();
        }
        public B withId( Long id ) {
            obj.setId(id);
            return thisObj;
        }
        public B withDeleted( boolean isDeleted ) {
            obj.setDeleted(isDeleted); return thisObj;
        }
        public T build() { return obj; }
        protected abstract T createObj();
        protected abstract B getThis();
    }
}
