package tn.esprit.spring.Controller;

import tn.esprit.spring.Service.UniversiteServiceImpl;
import tn.esprit.spring.entity.Universite;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universite")
public class UniversiteController {
    @Autowired
    UniversiteServiceImpl universiteService;

    @PostMapping("/adduniversite")
    Universite addUniversite(@RequestBody Universite universite)
    {
        return universiteService.addUniversite(universite);
    }

    @PutMapping("/updateUniversite")
    Universite updateUniversite(@RequestBody Universite Universite)
    {
        return universiteService.updateUniversite(Universite);
    }

    @GetMapping("/afficherUniversites")
    List<Universite> getAllUniversite()
    {
        return universiteService.getAllUniversites();
    }

    @DeleteMapping("/deleteUniversite/{id}")
    void deleteUniversite(@PathVariable Long id)
    {
        universiteService.deleteUniversite(id);
    }

    @GetMapping("/getUniversiteBychambreId")
    Universite GetUniversiteByChambreId(@PathParam("chambreId") Long chambreId)
    {
        return universiteService.findUniversitesByChambreId(chambreId);
    }

    @GetMapping("/getUniversiteByEtudiantId")
    Universite getUniversiteByEtudiantId(@PathParam("EtudiantId") Long EtudiantId)
    {
        return universiteService.findUniversitesByIdEtudiant(EtudiantId);
    }


}
