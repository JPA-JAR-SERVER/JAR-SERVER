package com.app.projectjar.audit;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@ToString
public abstract class TimeDate {

    private LocalDate createdDate;
    private LocalDate updatedDate;

    @PrePersist
    public void create(){
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    @PreUpdate
    public void update(){
        this.updatedDate = LocalDate.now();
    }
}
