package com.ducalme.app.controller;

import com.ducalme.app.models.Locataire;
import com.ducalme.app.services.LocataireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/locataire/")
public class LocataireController {

    private final LocataireService locataireService;

    public LocataireController(LocataireService locataireService) {
        this.locataireService = locataireService;
    }

    @GetMapping("index")
    public String getLocataires(Model model)
    {
        List<Locataire> locataires = locataireService.findAll();
        model.addAttribute("locataires", locataires);
        return "locataire/index";
    }

    @GetMapping("{id}")
    public String findLocataireById(@PathVariable(value = "id") Integer id, Model model) {
        Optional locataireOptional = locataireService.findById(id);
        if(locataireOptional.isPresent()) {
            Locataire locataire =(Locataire) locataireOptional.get();
            model.addAttribute("locataire", locataire);
            return "locataire/detail";
        }
        else {
            List<Locataire> locataires = locataireService.findAll();
            model.addAttribute("locataires", locataires);
            model.addAttribute("message", "Pas de locataire avec cet Id");
            return "redirect:";
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteLocataire(@PathVariable(value = "id") Integer id, Model model) throws ModelNotFoundException {
        Optional locataireOptional = locataireService.findById(id);
        if(locataireOptional.isPresent()) {
            Locataire locataire = (Locataire)locataireOptional.get();
            locataireService.deleteById(locataire.getId());
        }
        else {
            throw new ModelNotFoundException("Le locataire avec l'id " + id + " n'a pas été trouvé.");
        }
        List<Locataire> locataires = locataireService.findAll();
        model.addAttribute("locataires", locataires);
        return "locataire/index";
    }

    @GetMapping(path = {"ajout", "update/{id}"})
    private String addForm(@PathVariable("id") Optional<Integer> id, Model model){
        if(id.isPresent()) {
            Optional locataireOptional = locataireService.findById(id.get());
            if(locataireOptional.isPresent()) {
                Locataire locataire = (Locataire) locataireOptional.get();
                model.addAttribute("locataire", locataire);
            }
        } else {
            Locataire locataire = new Locataire();
            model.addAttribute("locataire", locataire);
        }
        return "locataire/create";
    }

    @PostMapping("addEdit")
    private String insertOrUpdate(Locataire locataire, Model model){
        if(locataire.getId() == null){
            locataireService.save(locataire);
        }else{
            Optional<Locataire> locataireOptional = locataireService.findById(locataire.getId());
            if(locataireOptional.isPresent()){
                Locataire temp = locataireOptional.get();
                temp.setNom(locataire.getNom());
                temp.setPrenom(locataire.getPrenom());
                temp.setTelephone(locataire.getTelephone());
                locataireService.save(temp);
            }
        }
        List<Locataire> locataires = locataireService.findAll();
        model.addAttribute("locataires", locataires);
        return "locataire/index";
    }

}
