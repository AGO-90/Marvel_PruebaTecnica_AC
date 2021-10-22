package uy.com.arnaldocastro.marvel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.com.arnaldocastro.marvel.logic.Hero;

@RestController
@RequestMapping("api/v1/marvel")
public class MarvelController {

    private final IMarvelService marvelService;

    @Autowired
    public MarvelController(IMarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping("/getHeroByID/{heroId}")
    public ResponseEntity<Hero> getHeroByID(@PathVariable String heroId){
        return null;
    }
}
