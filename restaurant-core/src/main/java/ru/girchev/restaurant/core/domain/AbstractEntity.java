package ru.girchev.restaurant.core.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by Girchev N.A. on 18.11.15.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_generator")
    @TableGenerator(name = "entity_generator", initialValue = 1, allocationSize = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
