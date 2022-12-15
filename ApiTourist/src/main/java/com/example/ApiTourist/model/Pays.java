package com.example.ApiTourist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpays;

    private String nompays;
    private String description;
    private String photopays;

   // @OneToMany(mappedBy = "pays")
    //List<Region> regionList;



}
