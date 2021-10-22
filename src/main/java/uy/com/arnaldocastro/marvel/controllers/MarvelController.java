package uy.com.arnaldocastro.marvel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.com.arnaldocastro.marvel.logic.Hero;
import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;
import uy.com.arnaldocastro.marvel.logic.exceptions.InvalidIDException;
import uy.com.arnaldocastro.marvel.logic.exceptions.NotFoundException;
import uy.com.arnaldocastro.marvel.logic.exceptions.UnauthorizedException;

import java.io.IOException;

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
        try {
            Hero hero = marvelService.getHeroByID(heroId);
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } catch (IOException | InternalServerError e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidIDException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
