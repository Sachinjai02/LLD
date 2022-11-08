package com.study.netflix.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
@Getter
@Setter
@Entity
public class Video extends BaseModel {
    private String title;
    private String description;
    @ManyToMany
    private List<Moviecast> casts;
}
