package com.kameleoon.trialtask.repository.audit;

import com.kameleoon.trialtask.entity.AbstractEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

/**
 * Auditor for entities.
 *
 * @author Yauheni Tstiov
 */
public class AuditListener {
    /**
     * Audits the entity when added
     *
     * @param entity which is added to the database
     */
    @PrePersist
    public void onPrePersist(AbstractEntity entity) {
        LocalDateTime date = LocalDateTime.now();
        entity.setCreationDate(date);
        entity.setUpdateDate(date);
    }

    /**
     * Audits the entity on update.
     *
     * @param entity which is update to the database.
     */
    @PreUpdate
    public void onPreUpdate(AbstractEntity entity) {
        entity.setUpdateDate(LocalDateTime.now());
    }
}
