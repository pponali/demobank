package org.service.loans.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 12/12/23
 * @Description
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedBy
    @Column(updatable = false)
    String created_by;


    @CreatedDate
    @Column(updatable = false)
    LocalDateTime created_at;

    @LastModifiedBy
    @Column(updatable = false)
    String updated_by;

    @LastModifiedDate
    @Column(updatable = false)
    LocalDateTime updated_at;

}
