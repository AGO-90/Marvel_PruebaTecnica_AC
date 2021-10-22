package uy.com.arnaldocastro.marvel.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import uy.com.arnaldocastro.marvel.controllers.responses.HeroResponse;
import uy.com.arnaldocastro.marvel.logic.Hero;
import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;
import uy.com.arnaldocastro.marvel.logic.exceptions.InvalidIDException;
import uy.com.arnaldocastro.marvel.logic.exceptions.NotFoundException;
import uy.com.arnaldocastro.marvel.logic.exceptions.UnauthorizedException;
import uy.com.arnaldocastro.marvel.logic.helpers.HeroHelper;
import uy.com.arnaldocastro.marvel.logic.providers.ApiCommunicationProvider;
import uy.com.arnaldocastro.marvel.logic.providers.PathObject;
import uy.com.arnaldocastro.marvel.logic.utils.Security;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Service
@PropertySource(value={"/marvelConfiguration.properties"})
public class MarvelServiceImpl implements IMarvelService{
    private ApiCommunicationProvider<String, PathObject> apiCommunicationProvider;
    @Value("${server}")
    private String server;
    @Value("${port}")
    private String port;
    @Value("${publicKey}")
    private String publicKey;
    @Value("${privateKey}")
    private String privateKey;

    @Autowired
    public MarvelServiceImpl(ApiCommunicationProvider apiCommunicationProvider){
        this.apiCommunicationProvider = apiCommunicationProvider;
    }

    @Override
    public Hero getHeroByID(String id) throws IOException, InternalServerError, InvalidIDException, NotFoundException, UnauthorizedException {
        validateHeroID(id);
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/urls.properties"));
        String hash = Security.obtainMD5Key(String.format("%s%s%s", 1, privateKey, publicKey));
        String urn = String.format(properties.getProperty("getHeroByID"), id, 1, publicKey, hash);
        String uri = String.format("%s%s%s%s", server, ":", port, urn);
        PathObject pathObject = PathObject.builder()
                .url(uri)
                .verbo("GET")
                .build();
        String response = apiCommunicationProvider.communicate(pathObject);
        return buildHeroWithResponse(response);
    }

    private void validateHeroID(String id) throws InvalidIDException {
        try{
            Integer.parseInt(id);
        } catch (Exception e){
            throw new InvalidIDException();
        }
    }

    @Override
    public Hero buildHeroWithResponse(String response) {
        Gson gson = new Gson();
        HeroResponse hero = gson.fromJson(response, HeroResponse.class);
        return HeroHelper.buildHero(hero, 0);
    }
}
