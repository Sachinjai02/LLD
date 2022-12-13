package com.study.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Movie extends BaseModel {
    private String name;
    @ManyToMany
    private List<Cast> castList;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> featureList;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Genre> genreList;
}
