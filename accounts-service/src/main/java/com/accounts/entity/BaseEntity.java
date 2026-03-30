package com.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class BaseEntity {
    @CreatedDate
    @Column(name = "creation_time",nullable = false,updatable = false)
    private LocalDateTime creationTime;

    @LastModifiedDate
    @Column(name = "last_update_time",nullable = false,updatable = false)
    private LocalDateTime lastUpdateTime;

    @CreatedBy
    @Column(name="created_by",nullable = false,updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
