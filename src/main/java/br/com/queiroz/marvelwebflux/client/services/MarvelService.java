package br.com.queiroz.marvelwebflux.client.services;

import br.com.queiroz.marvelwebflux.client.interfaces.MarvelClient;
import br.com.queiroz.marvelwebflux.configurations.MarvelConfig;
import br.com.queiroz.marvelwebflux.utils.ConstantsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MarvelService implements MarvelClient {

    private WebClient webClient;
    private MarvelConfig marvelConfig;

    @Autowired
    public MarvelService(MarvelConfig marvelConfig, WebClient.Builder builder){
        this.marvelConfig = marvelConfig;
        this.webClient = builder.baseUrl(marvelConfig.getUrl()).build();
    }

    @Override
    public Flux<String> findAllHeroes() {
        log.info("Searching for character with id [{}], id");
        return webClient
                .get()
                .uri(ConstantsUtils.BASE_URL_CLIENT)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Check the information")))
                .bodyToFlux(String.class);
    }

    @Override
    public Mono<String> findHeroesById(String id) {
        log.info("Searching for character with id [{}]", id);
        return webClient
                .get()
                .uri(ConstantsUtils.BASE_URL_CLIENT + ConstantsUtils.PATH_SEPARATOR + id + ConstantsUtils.PATH_SEPARATOR_INTERROGATION + marvelConfig.getPathAndToken())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                    error -> Mono.error(new RuntimeException("Check the information")))
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> findHeroesByName(String name) {
        log.info("Searching for character with name [{}]", name);
        return webClient
                .get()
                .uri(ConstantsUtils.BASE_URL_CLIENT + ConstantsUtils.PATH_SEPARATOR_INTERROGATION + ConstantsUtils.NAME + name + ConstantsUtils.AND_COMMERCIAL +
                        marvelConfig.getPathAndToken())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Check the information")))
                .bodyToMono(String.class);
    }
}
