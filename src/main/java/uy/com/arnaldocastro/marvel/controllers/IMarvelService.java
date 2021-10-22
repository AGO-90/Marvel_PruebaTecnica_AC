package uy.com.arnaldocastro.marvel.controllers;

import uy.com.arnaldocastro.marvel.logic.Hero;
import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;
import uy.com.arnaldocastro.marvel.logic.exceptions.InvalidIDException;
import uy.com.arnaldocastro.marvel.logic.exceptions.NotFoundException;
import uy.com.arnaldocastro.marvel.logic.exceptions.UnauthorizedException;

import java.io.IOException;

public interface IMarvelService {

    Hero getHeroByID(String id) throws IOException, InternalServerError, InvalidIDException, NotFoundException, UnauthorizedException;

    Hero buildHeroWithResponse(String response);
}
