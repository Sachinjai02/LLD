package com.study.netflix.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class UserProfile extends BaseModel {
    @OneToOne //UserProfile -> Profile  1:1
    private Profile profile;
    @ManyToOne //UserProfile -> User m:1
    private NUser user;
}
