package com.study.netflix.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class UserProfileVideo extends BaseModel {
    @ManyToOne
    private UserProfile userProfile;
    @ManyToOne
    private Video video;
    private Long lastTimestamp;
    @Enumerated(EnumType.ORDINAL)
    private VideoWatchedStatus videoWatchedStatus;


}
