package br.com.queiroz.marvelwebflux.configurations;

import br.com.queiroz.marvelwebflux.utils.ConstantsUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static br.com.queiroz.marvelwebflux.utils.ConstantsUtils.*;

@Configuration
@Getter
public class MarvelConfig {

    @Value("${marvel.client.url}")
    private String url;

    @Value("${marvel.client.ts}")
    private String ts;

    @Value("${marvel.client.apikey}")
    private String apiKey;

    @Value("${marvel.client.hash}")
    private String hash;

    public String getPathAndToken(){
        return TS + EQUAL_SEPARATOR + this.getTs() + AND_COMMERCIAL + API_KEY + EQUAL_SEPARATOR + this.getApiKey() + AND_COMMERCIAL +
                HASH + EQUAL_SEPARATOR + this.getHash();
    }
}
