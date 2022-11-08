package com.study.netflix.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Profile extends BaseModel {
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private ProfileType profileType;
}
