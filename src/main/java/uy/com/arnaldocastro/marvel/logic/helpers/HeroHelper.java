package uy.com.arnaldocastro.marvel.logic.helpers;

import uy.com.arnaldocastro.marvel.controllers.responses.HeroResponse;
import uy.com.arnaldocastro.marvel.controllers.responses.ResponseItem;
import uy.com.arnaldocastro.marvel.controllers.responses.ResponseResult;
import uy.com.arnaldocastro.marvel.logic.Hero;

import java.util.List;
import java.util.stream.Collectors;

public class HeroHelper {
    public static Hero buildHero(HeroResponse response, int index){
        ResponseResult hero = response.getData().getResults().get(index);
        List<ResponseItem> items = response.getData().getResults().get(index).getComics().getItems();
        List<String> comics = items.stream().
                map(comic -> comic.getName())
                .collect(Collectors.toList());
        return Hero.builder()
                .id(hero.getId())
                .name(hero.getName())
                .description(hero.getDescription())
                .comics(comics).build();
    }
}
