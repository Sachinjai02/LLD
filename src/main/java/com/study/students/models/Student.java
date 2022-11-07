package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends BaseModel {
    private String name;
    private String email;
    private String password;
    private String univName;
    @ManyToMany
    private List<Batch> batches;
}
