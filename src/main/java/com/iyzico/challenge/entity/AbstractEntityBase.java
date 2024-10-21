package com.iyzico.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractEntityBase {

    @CreationTimestamp
    private Date createDate = new Date();
    @UpdateTimestamp
    private Date updateDate;
    private Boolean deleteFlag = false;

    @PreUpdate
    private void onUpdate(){
        setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}