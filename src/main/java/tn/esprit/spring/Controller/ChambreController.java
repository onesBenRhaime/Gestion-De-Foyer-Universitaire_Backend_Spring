package tn.esprit.spring.Controller;


import tn.esprit.spring.Service.ChambreServiceImpl;
import tn.esprit.spring.entity.Chambre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
public class ChambreController {
    @Autowired
    ChambreServiceImpl chambreService;

    @PostMapping("/addChambre")
    Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/updateChambre")
    Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.updateChambres(chambre);
    }

    @GetMapping("/getAllChambres")
    List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @DeleteMapping("/deleteChambre/{id}")
    void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }

    //valid
    @GetMapping("/chambreEtud/{idEtuidant}")
    Chambre getChambreByEtudiant(@PathVariable Long idEtuidant) {
        return chambreService.GetChambreByEtudiant(idEtuidant);
    }
}

