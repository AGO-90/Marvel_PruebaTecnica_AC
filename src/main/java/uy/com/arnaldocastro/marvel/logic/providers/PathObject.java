package uy.com.arnaldocastro.marvel.logic.providers;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PathObject {
    private String url, verbo;
}
