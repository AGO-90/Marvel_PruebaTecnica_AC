package uy.com.arnaldocastro.marvel.controllers;

import uy.com.arnaldocastro.marvel.logic.Hero;

public interface IMarvelService {

    Hero getHeroByID(String id);

}
