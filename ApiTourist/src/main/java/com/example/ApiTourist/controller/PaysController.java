package com.example.ApiTourist.controller;

import com.example.ApiTourist.img.ConfigImage;
import com.example.ApiTourist.model.Pays;
import com.example.ApiTourist.repository.PaysRepository;
import com.example.ApiTourist.services.PaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/pays")
@AllArgsConstructor

@Api(value = "hello", description = "Methodes pour  Pays")
public class PaysController {
    @Autowired
    PaysService paysService;
    PaysRepository paysRepository;

    @ApiOperation(value = "Ajouter un Pays")
    @PostMapping("/add")
    /*pour que spring envoie les données de l'objet region envoyé au niveau du body we use RequestBody*/
    //Methode d'ajout de pays avec l'image
    public String ajout(@RequestParam("file") MultipartFile file, @Param("nom") String nompays, @Param("description") String description, @Param("photo") String photopays) throws IOException {

        Pays pays = new Pays();
        if (paysRepository.findByNompays(nompays) == null) {


            String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

            pays.setNompays(nompays);
            pays.setDescription(description);
            pays.setPhotopays(nomfile);

            String url = "C:\\Users\\adkonte\\Desktop\\backend-MaliTourist\\ApiTourist\\src\\main\\resources\\Image";

            ConfigImage.saveimgpays(url, nomfile, file);

            paysService.Ajoutpays(pays);
            return "Pays ajouter avec succes";


        } else {
            return  "cet pays existe déjà";
        }
    }


    @ApiOperation(value = "Lister les pays")
    @GetMapping("mylist")
    public List<Pays> l(){
        return this.paysService.liste();
    }

    @ApiOperation(value = "Modifier un pays par Id")
    @PutMapping("/update/{id_pays}")
    /*on envoie la variable ID*/
    public String  update(@RequestBody Pays pays,@PathVariable Long id_pays){
        this.paysService.Modifier(pays,id_pays);
        return"mise à jour valider";
    }

    @ApiOperation(value = "Supprimer un pays pa ID")
    @DeleteMapping("/delete/{id_pays}")
    public String supp(@PathVariable Long id_pays){
        this.paysService.SupprimerbyId(id_pays);
        return"Pays supprimer avec Succès";

    }
//    @PostMapping("/uploadImage")
//    public String uploadImage(@RequestParam("file")MultipartFile file) throws Exception{
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getContentType());
//        System.out.println(file.getSize());
//        String Path_Directory="C:\\Users\\adkonte\\Desktop\\backend-MaliTourist\\ApiTourist\\src\\main\\resources\\Image";
//      // String Path_Directory= new ClassPathResource("Image").getFile().getAbsolutePath();
//        Files.copy(file.getInputStream(), Paths.get(Path_Directory +File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
//
//        return "Importer avec succéss";
//    }
}
