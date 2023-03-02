package com.iliareshetov.accounthub.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@MappedSuperclass
public abstract class AbstractTimestampEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", nullable = false)
    private Timestamp createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    @PrePersist
    protected void onCreate() {
        updateTime = createTime = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = Timestamp.from(Instant.now());
    }
}
