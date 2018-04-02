package com.vm.heroes.controller;

import com.vm.heroes.model.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HeroesController {

    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public List<Hero> getHeroes() {
        Hero h1 = new Hero(1, "Victor");
        Hero h2 = new Hero(2, "Dubraska");
        Hero h3 = new Hero(3, "Juan");

        List<Hero> heroesList = new ArrayList<>();

        heroesList.addAll(Arrays.asList(
                new Hero(1, "Victor"),
                new Hero(2, "Dubraska"),
                new Hero(3, "Juan")
        ));

        return heroesList;
    }

}
