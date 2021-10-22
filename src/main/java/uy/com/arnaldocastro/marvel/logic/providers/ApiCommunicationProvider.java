package uy.com.arnaldocastro.marvel.logic.providers;

import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;

import java.io.IOException;

public interface ApiCommunicationProvider<T, D> {
    T communicate(D object) throws IOException, InternalServerError;
}
