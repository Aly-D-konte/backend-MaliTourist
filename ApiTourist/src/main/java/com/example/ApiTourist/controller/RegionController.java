package com.example.ApiTourist.controller;


import com.example.ApiTourist.img.ConfigImage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.model.Region;
import com.example.ApiTourist.services.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
/*RestController est utilisé pour créer des services
Web restful à l’aide de l’annotation,
il permet de gérer toutes les API REST telles que les requêtes GET, POST, Delete, PUT.*/

@RequestMapping("/ApiTourist/region")
/*Elle est utilisée pour traiter les requêtes HTTP avec des modèles d’URL spécifiés.
Il est utilisé dans et avec les @Controller et les @RestController.*/
@AllArgsConstructor

@Api(value = "hello", description = "Methodes sur Region")
public class RegionController {
    @Autowired
    RegionService regionService;


    @ApiOperation(value = "Ajouter une region")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    //Methode d'ajout de pays avec l'image
    public Region ajout(@RequestParam("file") MultipartFile file, @Param("nom") String nomregion, @Param("activité") String activité,  @Param("langue") String langue , @Param("Superficie") Long Superficie, @Param("photoregion") String photoregion) throws IOException {

        Region region = new Region();

        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        region.setNomregion(nomregion);
        region.setActivité(activité);
        region.setLangue(langue);
        region.setPhotoregion(nomfile);


        String url="C:\\Users\\adkonte\\Desktop\\backend-MaliTourist\\ApiTourist\\src\\main\\resources\\Image";

        ConfigImage.saveimgpays(url, nomfile, file);

        return  regionService.ajout(region);


    }


    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylist")
    public List<Object[]> l(){

        return regionService.lister();
    }
    @ApiOperation(value = "afficher la liste des regions ")
    @GetMapping("/mylistwithoutp")
    public Iterable<Object[]> regionIterable(){
        return  regionService.listersanspays();
    }

    @ApiOperation(value = "Modifier une region par Id")
    @PutMapping("/update/{id}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Region region,@PathVariable Long id){
        this.regionService.Modifier(region,id);
                return"mise à jour valider";
    }
    @ApiOperation(value = "Supprimer une region par Id")
    @DeleteMapping("/delete/{id}")
    public String supp(@PathVariable Long id){
        this.regionService.SupprimerbyId(id);
        return"region supprimer avec Succès";

    }


}
