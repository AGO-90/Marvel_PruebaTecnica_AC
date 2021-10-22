package uy.com.arnaldocastro.marvel.controllers;

import uy.com.arnaldocastro.marvel.logic.Hero;
import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;

import java.io.IOException;

public interface IMarvelService {

    Hero getHeroByID(String id) throws IOException, InternalServerError;

    Hero buildHeroWithResponse(String response);
}
