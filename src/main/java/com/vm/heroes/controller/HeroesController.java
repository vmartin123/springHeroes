package com.vm.heroes.controller;

import com.vm.heroes.model.Hero;
import com.vm.heroes.service.HeroesService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class HeroesController {

    HeroesService heroesService;

    @Autowired
    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public List<Hero> getHeroes(@RequestParam(value="name", required=false) String name) {
        if (name == null) {
            return heroesService.getHeroesList();
        } else {
            return heroesService.getHeroesFilteredByName(name);
        }
    }

    @GetMapping("/heroes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hero getHeroById(@PathVariable int id) {

        return heroesService.getHeroById(id);
    }

    @PostMapping("/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero createHero(@RequestBody Hero hero) {

        return heroesService.createHero(hero);
    }

    @PutMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public Hero updateHero(@RequestBody Hero hero) {

        return heroesService.updateHero(hero);
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hero deleteHero(@PathVariable int id) {

        return heroesService.deleteHero(id);
    }

}
