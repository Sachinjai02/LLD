package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Batch extends BaseModel {
    private String name;
    /* mappedBy tells JPA to not create another relationship table
     * It will just create the table student_batches and populate this
     * field students
     */
    @ManyToMany(mappedBy = "batches")
    private List<Student> students;
}
