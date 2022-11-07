package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Exam extends BaseModel {
    private String name;
    private int duration;
}
