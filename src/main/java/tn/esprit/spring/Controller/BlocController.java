package tn.esprit.spring.Controller;

import tn.esprit.spring.Service.BlocServiceImpl;
import tn.esprit.spring.entity.Bloc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bloc")
public class BlocController {
    @Autowired
    BlocServiceImpl blocService;

    @PostMapping("/addBloc")
    Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/updateBloc")
    Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.updateBloc(bloc);
    }

    @GetMapping("/getAllBlocs")
    List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @DeleteMapping("/deleteBloc/{id}")
    void deleteBloc(@PathVariable Long id) {
        blocService.deleteBloc(id);
    }

    //worked
    @GetMapping ("/getBlocsByCapacite/{capacite}")
    List<Bloc> getBlocByCapacite(@PathVariable Long capacite )
    {
        return blocService.getBlocByCapacite(capacite);
    }

    @GetMapping("/listBy30s")
    void afficherListeBlocsBy30() {
         blocService.afficherListeBlocs();
    }

}