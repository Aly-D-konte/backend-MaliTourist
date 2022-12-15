package com.example.ApiTourist.repository;

import com.example.ApiTourist.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Long>{
    Pays findByNompays(String nompays);



    @Modifying
    @Transactional
    @Query(value = " INSERT INTO pays (nompays, description, photopays) VALUES (\"Mali\", \"Berceau des grands empires et royaumes d’Afrique de l’Ouest, le Mali riche de son passé glorieux constitue une des destinations touristiques \", \"mali.png\");" ,nativeQuery = true)
    void ajoutpays();
}
