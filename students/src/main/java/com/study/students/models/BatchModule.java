package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class BatchModule extends BaseModel {
    @ManyToOne
    private Batch batch;
    @ManyToOne
    private Module module;
}
