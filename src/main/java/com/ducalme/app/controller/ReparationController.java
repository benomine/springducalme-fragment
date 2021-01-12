package com.ducalme.app.controller;

import com.ducalme.app.models.Reparation;
import com.ducalme.app.services.ReparationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/reparation/")
public class ReparationController {

    private final ReparationService reparationService;

    public ReparationController(ReparationService reparationService) {
        this.reparationService = reparationService;
    }

    @PostMapping("addEdit")
    private String insertOrUpdate(Reparation reparation, Model model){
        if(reparation.getId() == null){
            reparationService.save(reparation);
        }else{
            Optional<Reparation> reparationOptional = reparationService.findById(reparation.getId());
            if(reparationOptional.isPresent()){
                Reparation temp = reparationOptional.get();
                temp.setDescription(reparation.getDescription());
                temp.setDateCreation(reparation.getDateCreation());
                temp.setDateFinalisation(reparation.getDateFinalisation());
                temp.setStatus(reparation.getStatus());
                reparationService.save(temp);
            }
        }
        List<Reparation> reparations = reparationService.findAll();
        model.addAttribute("reparations", reparations);
        return "reparation/index";
    }

    @GetMapping("index")
    public String getReparations(Model model)
    {
        List<Reparation> reparations = reparationService.findAll();
        model.addAttribute("reparations", reparations);
        return "reparation/index";
    }

    @GetMapping(path = {"ajout", "update/{id}"})
    private String addForm(@PathVariable("id") Optional<Integer> id, Model model){
        if(id.isPresent()){
            Optional repar = reparationService.findById(id.get());
            if (repar.isPresent())
            {
                Reparation reparation = (Reparation) repar.get();
                model.addAttribute("reparation", reparation);
            }
        } else {
            Reparation reparation = new Reparation();
            model.addAttribute("reparation", reparation);
        }
        List<Reparation> reparations = reparationService.findAll();
        model.addAttribute("reparations", reparations);
        return "reparation/create";
    }

    @GetMapping("{id}")
    public String findAppartementById(@PathVariable(value = "id") Integer id, Model model) {
        Optional repar = reparationService.findById(id);
        if(repar.isPresent()) {
            Reparation reparation =(Reparation) repar.get();
            model.addAttribute("reparation", reparation);
            return "reparation/detail";
        }
        else {
            List<Reparation> reparations = reparationService.findAll();
            model.addAttribute("reparations", reparations);
            model.addAttribute("message", "Pas de réparation avec cet Id");
            return "redirect:";
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteLocataire(@PathVariable(value = "id") Integer id, Model model) throws ModelNotFoundException {
        Optional repar = reparationService.findById(id);
        if(repar.isPresent()) {
            Reparation reparation = (Reparation) repar.get();
            reparationService.deleteById(reparation.getId());
        }
        else {
            throw new ModelNotFoundException("La réparation avec l'id " + id + " n'a pas été trouvée.");
        }
        List<Reparation> reparations = reparationService.findAll();
        model.addAttribute("reparations", reparations);
        return "reparation/index";
    }
}
