package br.com.queiroz.marvelwebflux.controller;

import br.com.queiroz.marvelwebflux.client.interfaces.MarvelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/marvel")
public class MarvelController {

    private MarvelClient marvelClient;

    public MarvelController(MarvelClient marvelClient){
        this.marvelClient = marvelClient;
    }

    @GetMapping("/heroes/id/{id}")
    public Mono<String> findById(@PathVariable String id){
        return marvelClient.findHeroesById(id);
    }

    @GetMapping("/heroes/name/{name}")
    public Mono<String> findByName(@PathVariable String name){
        return marvelClient.findHeroesByName(name);
    }

    @GetMapping("/heroes")
    public Flux<String> findAllHeroes(){
        return marvelClient.findAllHeroes();
    }
}
