package uy.com.arnaldocastro.marvel.logic.providers;

import org.springframework.stereotype.Component;
import uy.com.arnaldocastro.marvel.logic.exceptions.InternalServerError;
import uy.com.arnaldocastro.marvel.logic.exceptions.NotFoundException;
import uy.com.arnaldocastro.marvel.logic.exceptions.UnauthorizedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ApiConnectionHelper implements ApiCommunicationProvider<String, PathObject> {

    @Override
    public String communicate(PathObject object) throws IOException, InternalServerError, NotFoundException, UnauthorizedException {
        URL url = new URL(object.getUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(object.getVerbo());
        httpURLConnection.setRequestProperty("Accept", "application/json");
        if (httpURLConnection.getResponseCode() != 200) {
            System.out.println(httpURLConnection.getResponseCode());
            throwExceptionByResponseCode(httpURLConnection.getResponseCode());
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String output = bufferedReader.readLine();
        httpURLConnection.disconnect();
        return output;
    }

    private void throwExceptionByResponseCode(int responseCode) throws InternalServerError, NotFoundException, UnauthorizedException {
        switch (responseCode){
            case 404:
                throw new NotFoundException();
            case 401:
                throw new UnauthorizedException();
            default:
                throw new InternalServerError();
        }
    }
}
