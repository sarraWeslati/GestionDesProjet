package com.project.project_management.controller;

import com.project.project_management.dto.AffectationDTO;
import com.project.project_management.service.AffectationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Spring transforme automatiquement les objets Java en JSON.
@RequestMapping("/api/affectations")
@CrossOrigin("*") //Autorise le frontend à accéder au backend. Le "*" signifie que toutes les origines sont autorisées, ce qui est pratique pour le développement local, mais en production, il est recommandé de restreindre cela à des domaines spécifiques pour des raisons de sécurité.
public class AffectationController {

    private final AffectationService service;

    @Autowired
    public AffectationController(AffectationService service) {
        this.service = service;
    }

    @GetMapping
    public List<AffectationDTO> getAll() {
        return service.getAll();
    }//Retourne toutes les affectations dans une liste d’objets. 

    @GetMapping("/{id}")
    public AffectationDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }//Retourne une affectation spécifique en fonction de son ID. Le @PathVariable indique que l’ID est extrait de l’URL.

    @PostMapping
    public AffectationDTO create(@Valid @RequestBody AffectationDTO affectation) {
        return service.save(affectation);//Le service save() est appelé pour enregistrer la nouvelle affectation dans la base de données. Le DTO est validé avant d’être traité grâce à l’annotation @Valid, qui vérifie les contraintes définies dans le DTO (comme les champs obligatoires, les formats, etc.). Si la validation échoue, une réponse d’erreur appropriée sera renvoyée au client.
    }//Crée une nouvelle affectation. Le @RequestBody indique que les données de l’affectation sont envoyées dans le corps de la requête HTTP, et le @Valid déclenche la validation des données selon les annotations présentes dans le DTO.

    @PutMapping("/{id}")//Le @PutMapping indique que cette méthode gère les requêtes HTTP PUT, qui sont généralement utilisées pour mettre à jour des ressources existantes. L’URL inclut un paramètre {id} qui représente l’identifiant de l’affectation à mettre à jour.
    public AffectationDTO update(@PathVariable Long id, @Valid @RequestBody AffectationDTO affectation) {
        return service.update(id, affectation);
    }//Met à jour une affectation existante. Le @PathVariable extrait l’ID de l’affectation à mettre à jour depuis l’URL, tandis que le @RequestBody contient les nouvelles données de l’affectation. La validation est également appliquée pour s’assurer que les données sont correctes avant la mise à jour.

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
