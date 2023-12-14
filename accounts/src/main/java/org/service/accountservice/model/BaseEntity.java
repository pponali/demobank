package org.service.accountservice.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */


@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @CreatedBy
    @Column(updatable = false)
    String created_by;

    @LastModifiedBy
    @Column(updatable = false)
    String last_modified_by;

    @LastModifiedDate
    @Column(updatable = false)
    LocalDateTime last_modified_date;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime created_date;


}
