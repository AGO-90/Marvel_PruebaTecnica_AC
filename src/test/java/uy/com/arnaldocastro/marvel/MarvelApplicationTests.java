package uy.com.arnaldocastro.marvel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import uy.com.arnaldocastro.marvel.controllers.IMarvelService;
import uy.com.arnaldocastro.marvel.logic.Hero;
import uy.com.arnaldocastro.marvel.logic.exceptions.InvalidIDException;
import uy.com.arnaldocastro.marvel.logic.exceptions.NotFoundException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@PropertySource( value = {"classpath:marvelConfiguration.properties"})
class MarvelApplicationTests {

    private final IMarvelService marvelService;

    @Autowired
    public MarvelApplicationTests(IMarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @Test
    void heroSearch() {
        try {
            Hero hero = marvelService.getHeroByID("1009610");
            assertTrue(hero.equals(Hero.builder()
                            .id("1009610")
                            .name("Spider-Man (Peter Parker)")
                            .description("Bitten by a radioactive spider, high school student " +
                                    "Peter Parker gained the speed, strength and powers of a spider. " +
                                    "Adopting the name Spider-Man, Peter hoped to start a career using his " +
                                    "new abilities. Taught that with great power comes great responsibility, " +
                                    "Spidey has vowed to use his powers to help people.")
                            .comics(Arrays.asList("Spider-Man: 101 Ways to End the Clone Saga (1997) #1",
                                    "2099 Alpha (2019) #1",
                                    "A YEAR OF MARVELS TPB (Trade Paperback)",
                                    "A Year of Marvels: April Infinite Comic (2016) #1",
                                    "A Year of Marvels: February Infinite Comic (2016) #1",
                                    "A+X (2012) #4",
                                    "Absolute Carnage (2019) #1",
                                    "Absolute Carnage (2019) #2",
                                    "Absolute Carnage (2019) #5",
                                    "Absolute Carnage: Symbiote Spider-Man (2019) #1",
                                    "Actor Presents Spider-Man and the Incredible Hulk (2003) #1",
                                    "Adventures of Spider-Man (1996) #1",
                                    "Adventures of Spider-Man (1996) #2",
                                    "Adventures of Spider-Man (1996) #3",
                                    "Adventures of Spider-Man (1996) #4",
                                    "Adventures of Spider-Man (1996) #5",
                                    "Adventures of Spider-Man (1996) #6",
                                    "Adventures of Spider-Man (1996) #7",
                                    "Adventures of Spider-Man (1996) #8",
                                    "Adventures of Spider-Man (1996) #9")).build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void heroSearchNotFound() {
            try {
                Hero hero = marvelService.getHeroByID("100961099");
            } catch (Exception e) {
                assertTrue(e instanceof NotFoundException);
            }
    }

    @Test
    void heroSearchInvalidID() {
        try {
            Hero hero = marvelService.getHeroByID("1009610EFD");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidIDException);
        }
    }
}
