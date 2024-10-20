package com.iyzico.challenge.seat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractEntityBase {

    //@Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();
    //@Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private Boolean deleteFlag = false;

    @PreUpdate
    private void onUpdate(){
        setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
    }
}