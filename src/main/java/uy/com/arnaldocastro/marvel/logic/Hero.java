package uy.com.arnaldocastro.marvel.logic;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class Hero {
    private String id, name, description;
    private List<String> comics;
}
