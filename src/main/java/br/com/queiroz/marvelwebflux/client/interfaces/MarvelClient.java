package br.com.queiroz.marvelwebflux.client.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarvelClient {

    Flux<String> findAllHeroes();
    Mono<String> findHeroesById(String id);
    Mono<String> findHeroesByName(String name);

}
