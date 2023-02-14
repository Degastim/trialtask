package com.kameleoon.trialtask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Super class for entity
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    protected AbstractEntity() {
    }

    protected AbstractEntity(long id) {
        this.id = id;
    }

    public AbstractEntity(long id, LocalDateTime creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public AbstractEntity(long id, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
