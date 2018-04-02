package com.vm.heroes.controller;

import com.vm.heroes.model.Hero;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@Data
public class HeroesController {

    private List<Hero> heroesList = new ArrayList<>();

    public HeroesController() {
        buildHeroes();
    }

    @CrossOrigin
    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public List<Hero> getHeroes(@RequestParam(value="name", required=false) String name) {

        if (name == null) {
            return getHeroesList();
        } else {
            ArrayList<Hero> resList = new ArrayList<>();

            for (Hero curVal : getHeroesList()){
                if (curVal.getName().toLowerCase().contains(name.toLowerCase())){
                    resList.add(curVal);
                }
            }
            return resList;
        }
    }

    @CrossOrigin
    @GetMapping("/heroes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hero getHero(@PathVariable int id) {

        return getHeroesList().stream().filter(
                hero -> hero.getId() == id
        ).findFirst().orElse(null);
    }

    @CrossOrigin
    @PostMapping("/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero createHero(@RequestBody Hero hero) {
        int id = getHeroesList().get(getHeroesList().size()-1).getId() + 1;
        hero.setId(id);

        getHeroesList().add(hero);
        return hero;
    }

    @CrossOrigin
    @PutMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    public Hero updateHero(@RequestBody Hero hero) {
        int index = IntStream.range(0, getHeroesList().size()).filter(
                heroInd-> getHeroesList().get(heroInd).getId() == hero.getId()
        ).findFirst().getAsInt();

        getHeroesList().get(index).setName(hero.getName());

        return hero;
    }

    @CrossOrigin
    @DeleteMapping("/heroes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hero deleteHero(@PathVariable int id) {
        Hero hero = getHeroesList().stream().filter(
                heroToFind -> heroToFind.getId() == id
        ).findFirst().orElse(null);

        getHeroesList().remove(hero);

        return hero;
    }

    private void buildHeroes() {
        heroesList.addAll(Arrays.asList(
                new Hero(1, "Victor"),
                new Hero(2, "Dubraska"),
                new Hero(3, "Luis"),
                new Hero(4, "Juan"),
                new Hero(5, "Laura"),
                new Hero(6, "Simon")
        ));
    }

}
