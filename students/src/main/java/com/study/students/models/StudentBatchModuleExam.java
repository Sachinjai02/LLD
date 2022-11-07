package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class StudentBatchModuleExam extends BaseModel {
    @ManyToOne
    private Student student;
    @ManyToOne
    private BatchModuleExam batchModuleExam;
    private int marksScored;
    private boolean isAttempted;
    private String passStatus;
}
