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

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Hero){
            Hero hero = (Hero) obj;
            if(hero.getId().equalsIgnoreCase(this.id)
                    && hero.getName().equalsIgnoreCase(this.getName())
                    && hero.getDescription().equalsIgnoreCase(this.description)
                    && hero.getComics().equals(this.getComics()))
                return true;
            else
                return false;
        }
        return false;
    }
}
