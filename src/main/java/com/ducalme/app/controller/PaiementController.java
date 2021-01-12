package com.ducalme.app.controller;

import com.ducalme.app.models.Locataire;
import com.ducalme.app.models.Paiement;
import com.ducalme.app.services.LocataireService;
import com.ducalme.app.services.PaiementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/paiement/")
public class PaiementController {

    private final PaiementService paiementService;
    private final LocataireService locataireService;

    public PaiementController(PaiementService paiementService, LocataireService locataireService) {
        this.paiementService = paiementService;
        this.locataireService = locataireService;
    }

    @GetMapping("index")
    public String getPaiements(Model model)
    {
        List<Paiement> paiements = paiementService.findAll();
        model.addAttribute("paiements", paiements);
        return "paiement/index";
    }

    @GetMapping("{id}")
    public String findPaiementById(@PathVariable(value = "id") Integer id, Model model) {
        Optional paie = paiementService.findById(id);
        if(paie.isPresent()) {
            Paiement paiement = (Paiement) paie.get();
            model.addAttribute("paiement", paiement);
            return "paiement/detail";
        }
        else {
            List<Paiement> paiements = paiementService.findAll();
            model.addAttribute("paiements", paiements);
            List<Locataire> locataires = locataireService.findAll();
            model.addAttribute("locataires",locataires);
            model.addAttribute("paiements", paiements);
            model.addAttribute("message", "Pas de paiement avec cet Id");
            return "redirect:";
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deletePaiement(@PathVariable(value = "id") Integer id, Model model) throws ModelNotFoundException {
        Optional paiement = paiementService.findById(id);
        if(paiement.isPresent()) {
            Paiement paie = (Paiement) paiement.get();
            paiementService.deleteById(paie.getId());
        }
        else {
            throw new ModelNotFoundException("Le paiement avec l'id " + id + " n'a pas été trouvé.");
        }
        List<Paiement> paiements = paiementService.findAll();
        model.addAttribute("paiements", paiements);
        List<Locataire> locataires = locataireService.findAll();
        model.addAttribute("locataires",locataires);
        return "paiement/index";
    }

    @GetMapping(path = {"ajout", "update/{id}"})
    private String addForm(@PathVariable("id") Optional<Integer> id, Model model){
        if(id.isPresent()){
            Optional paiementOptional = paiementService.findById(id.get());
            if(paiementOptional.isPresent()) {
                Paiement paiement = (Paiement) paiementOptional.get();
                model.addAttribute("paiement", paiement);
            }
        } else {
            Paiement paiement = new Paiement();
            model.addAttribute("paiement", paiement);
        }
        List<Paiement> paiements = paiementService.findAll();
        model.addAttribute("paiements", paiements);
        List<Locataire> locataires = locataireService.findAll();
        model.addAttribute("locataires",locataires);
        return "paiement/create";
    }

    @PostMapping("addEdit")
    private String insertOrUpdate(Paiement paiement, Model model){
        if(paiement.getId() == null){
            paiementService.save(paiement);
        }else{
            Optional<Paiement> paiementOptional = paiementService.findById(paiement.getId());
            if(paiementOptional.isPresent()){
                Paiement temp = paiementOptional.get();
                temp.setDatePaiement(paiement.getDatePaiement());
                temp.setLocataire(paiement.getLocataire());
                temp.setMontant(paiement.getMontant());
                paiementService.save(temp);
            }
        }
        List<Paiement> paiements = paiementService.findAll();
        model.addAttribute("paiements", paiements);
        return "paiement/index";
    }
}
