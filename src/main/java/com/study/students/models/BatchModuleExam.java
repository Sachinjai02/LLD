package com.study.students.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
@Getter
@Setter
public class BatchModuleExam extends BaseModel {
    @ManyToOne
    private BatchModule batchModule;
    @ManyToOne
    private Exam exam;
    private Date startDate;
}
